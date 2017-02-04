package com.org.weixin.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.weixin.client.bean.advanced.paymch.MchNotifyXml;
import com.org.weixin.client.bean.advanced.paymch.MchPayNotify;
import com.org.weixin.util.ExpireSet;
import com.org.weixin.util.SignatureUtil;
import com.org.weixin.util.XMLConverUtil;

/**
 * 支付回调通知
 */
public class PayMchNotifyServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String key;	//mch key

	//重复通知过滤  时效60秒
    private static ExpireSet<String> expireSet = new ExpireSet<String>(60);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求数据
		MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class,request.getInputStream());
		//已处理 去重
		if(expireSet.contains(payNotify.getTransaction_id())){
			return;
		}
		//签名验证
		if(SignatureUtil.validateAppSignature(payNotify,key)){
			expireSet.add(payNotify.getTransaction_id());
			MchNotifyXml baseResult = new MchNotifyXml();
			baseResult.setReturn_code("SUCCESS");
			baseResult.setReturn_msg("OK");
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}else{
			MchNotifyXml baseResult = new MchNotifyXml();
			baseResult.setReturn_code("FAIL");
			baseResult.setReturn_msg("ERROR");
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
		}
	}

}
