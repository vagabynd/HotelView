package com.evgen.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(WebConfig.class);
    context.setServletContext(servletContext);

    ServletRegistration.Dynamic dispathcer = servletContext.addServlet("dispathcer", new DispatcherServlet(context));
    dispathcer.setLoadOnStartup(1);
    dispathcer.addMapping("/");
  }
}