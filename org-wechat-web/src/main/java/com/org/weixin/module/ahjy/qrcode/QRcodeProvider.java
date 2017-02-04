/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.ahjy.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年3月29日
 */
public class QRcodeProvider {
	
	public static String textQrcode(String text,String despath) throws Exception{
		TextQRcode qrcode = new TextQRcode();
		qrcode.setText(text);
		qrcode.executeConnector();
		BitMatrix bitMatrix = qrcode.getMatrix();
		MatrixToImageWriter.writeToFile(bitMatrix,"PNG",new File(despath));
		return despath;
	}
	
	public static void logoQrcode(String text,String qrDesPath,String logoPath) throws Exception{
		
		//QRcodeProvider.textQrcode(text, qrDesPath);
		TextQRcode qrcode = new TextQRcode();
		qrcode.setText(text);
		qrcode.executeConnector();
		BitMatrix bitMatrix = qrcode.getMatrix();
		BufferedImage qrcodeimage = MatrixToImageWriter.toBufferedImage(bitMatrix);
		InputStream inp = Thread.currentThread().getContextClassLoader().getResourceAsStream(logoPath);
		BufferedImage logoimage = ImageIO.read(inp);
		//BufferedImage qrcodeimage = ImageIO.read(new FileInputStream(qrDesPath));
		LogoInQrcode.drawing(logoimage, qrcodeimage);
		ImageIO.write(qrcodeimage, "PNG", new File(qrDesPath));
	}
	
	public static void main(String[] args) {
		
		try {
			QRcodeProvider.logoQrcode("http://www.baidu.com", "D:/workspace/feinno-wechat-manager/src/main/webapp/qrcode/new_111qrcode.png", "ahjy_logo.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
