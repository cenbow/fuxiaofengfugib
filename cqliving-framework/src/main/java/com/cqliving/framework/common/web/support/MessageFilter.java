package com.cqliving.framework.common.web.support;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 为了信息在redirect时不会消失,BaseController使用session暂存message． 而此filter负责把消息 从session
 * 搬回到 request．
 * 
 * @author calvin
 */
public class MessageFilter implements Filter {
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		List<Object> messages = (List<Object>) request.getSession().getAttribute("messages");

		if (messages != null) {
			request.setAttribute("messages", messages);
			request.getSession().removeAttribute("messages");
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
