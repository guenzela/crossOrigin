package ch.chiodoni.web.crossorigin.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSPFilter implements Filter {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CSPFilter.class);
	
	private FilterConfig filterConfig = null;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		LOGGER.info("do filter");
		((HttpServletResponse)resp).setHeader("X-Content-Security-Policy", "default-src 'self'; img-src 'self'");
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
