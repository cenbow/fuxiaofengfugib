package com.org.tld;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cqliving.tool.common.util.StringUtil;


@SuppressWarnings("serial")
public class CutStr extends TagSupport {

	private static final Logger logger = LoggerFactory.getLogger(CutStr.class);

	private int length;// length为汉字数，转换为字节是 length*2 ，根据字节判断截取多少字
	private String value;

	@Override
	public int doEndTag() throws JspException {
		try {
			if(StringUtil.isEmpty(value)){
				value = "";
			}
			if(value.length()>length){
//				value = StringUtil.cutString(value, length)+"...";
				value = StringUtil.cutString(value, length, true)+"...";
			}
			pageContext.getOut().print(value);
		} catch (Exception e) {
			logger.error("自定义 CutStr标签 --->截字报错");
		}
		return EVAL_PAGE;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SplitStrTag [length=" + length + ", value=" + value + "]";
	}

}
