package com.websystique.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springsecurity")
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		//viewResolver.setPrefix("/WEB-INF/views/");
                viewResolver.setPrefix("/static/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	/*
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/static/**").addResourceLocations("/static/"); 
        registry.addResourceHandler("client/node_modules/**").addResourceLocations("/static/node_modules/");
        registry.addResourceHandler("client/app/**").addResourceLocations("/static/app/"); 
        registry.addResourceHandler("client/css/**").addResourceLocations("/static/css/"); 
        registry.addResourceHandler("client/js/**").addResourceLocations("/static/js/"); 
        registry.addResourceHandler("client/images/**").addResourceLocations("/static/images/");
        registry.addResourceHandler("node_modules/**").addResourceLocations("/static/node_modules/");
        registry.addResourceHandler("app/**").addResourceLocations("/static/app/"); 
        registry.addResourceHandler("css/**").addResourceLocations("/static/css/"); 
        registry.addResourceHandler("js/**").addResourceLocations("/static/js/"); 
        registry.addResourceHandler("images/**").addResourceLocations("/static/images/");
    }
    
    /*
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }
    
    
}