package ch.chiodoni.web.crossorigin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import ch.chiodoni.web.crossorigin.web.HeadersHolder;
import ch.chiodoni.web.crossorigin.web.SetHttpHeaderInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	

	private static final String RESOURCES_HANDLER = "/resources/";
	private static final String RESOURCES_LOCATION = RESOURCES_HANDLER + "**";

	@Autowired
	private SetHttpHeaderInterceptor setHttpHeaderInterceptor;
	

	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
		requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
		requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
		return requestMappingHandlerMapping;
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(setHttpHeaderInterceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.html").addResourceLocations(RESOURCES_LOCATION);
        registry.addResourceHandler("/css/").addResourceLocations(RESOURCES_LOCATION);
        registry.addResourceHandler("/js/").addResourceLocations(RESOURCES_LOCATION);
        registry.addResourceHandler("/lib/").addResourceLocations(RESOURCES_LOCATION);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
//	@Scope(WebApplicationContext.SCOPE_SESSION)
	public static HeadersHolder headersHolder() {
		return new HeadersHolder();
	}


}
