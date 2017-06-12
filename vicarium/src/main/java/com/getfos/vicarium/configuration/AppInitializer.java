package com.getfos.vicarium.configuration;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.getfos.vicarium.job.DeputyJob;


public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ctx.setServletContext(container);

		ServletRegistration.Dynamic servlet = container.addServlet(
				"dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
	}

}
