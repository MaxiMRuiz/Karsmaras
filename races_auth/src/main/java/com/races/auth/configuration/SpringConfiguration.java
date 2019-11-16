package com.races.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.races.auth.component.RequestInterceptor;

/**
 * SpringConfiguration
 * 
 * @author Maximino Mañanes Ruiz
 */
@Configuration
public class SpringConfiguration implements WebMvcConfigurer{

	@Autowired
	@Qualifier("RequestTimeInterceptor")
	private RequestInterceptor interceptor;
	
	/**
	 * Add interceptor to project configuration
	 */
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
	}
	
}
