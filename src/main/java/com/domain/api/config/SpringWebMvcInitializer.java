package com.domain.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@ComponentScan
@EnableSpringDataWebSupport
public class SpringWebMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{ "/" };
	}

}
