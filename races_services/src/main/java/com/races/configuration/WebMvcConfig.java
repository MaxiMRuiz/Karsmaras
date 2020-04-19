package com.races.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.races.component.RequestTimeInterceptor;

/**
 * Clase de configuracion donde establecer el interceptor de peticiones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	public RequestTimeInterceptor interceptor() {
		return new RequestTimeInterceptor();
	}
	
	
	/**
	 * Añade el interceptor del proyecto
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor());
	}

}
