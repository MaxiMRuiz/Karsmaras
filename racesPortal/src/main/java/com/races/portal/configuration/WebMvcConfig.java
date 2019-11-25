package com.races.portal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.races.portal.component.RequestTimeInterceptor;


/**
 * Clase de configuracion donde establecer el interceptor de peticiones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * Añade el interceptor del proyecto
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestTimeInterceptor());
	}

}
