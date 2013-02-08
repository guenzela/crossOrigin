package ch.chiodoni.web.crossorigin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SetHttpHeaderInterceptor extends HandlerInterceptorAdapter {
	
	 private final static Logger LOGGER = LoggerFactory.getLogger(SetHttpHeaderInterceptor.class);
	
	@Autowired
	private HeadersHolder headersHolder;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		LOGGER.info("setting headers");
		for (Header header : headersHolder.getHeaders()) {
			((HttpServletResponse) response).setHeader(header.getName(), header.getValue());
		}
		
	
		return true;
	}

}