package com.ind.resource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ind.Parser;

public class RedisListner implements ServletContextListener {
	 @Override
	  public void contextDestroyed(ServletContextEvent arg0) {
	    //Notification that the servlet context is about to be shut down.   
	  }

	  @Override
	  public void contextInitialized(ServletContextEvent arg0) {
		  Parser.parse(arg0.getServletContext().getRealPath("/WEB-INF/redis-unstable/"));
	  }
}
