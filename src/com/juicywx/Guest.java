package com.juicywx;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Guest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 resp.setContentType("text/plain");
	     resp.getWriter().println("Hello, world");
	     String guestbookName = "linuxKernel";
	     Key guestBookKey=KeyFactory.createKey("Guestbook",guestbookName);
	     
	     Entity entity=new Entity("Greeting",guestBookKey);
	     entity.setProperty("date", new Date());
	     entity.setProperty("name", guestbookName);
	     DatastoreService datastore =DatastoreServiceFactory.getDatastoreService();
	     datastore.put(entity);
	     
	     resp.sendRedirect("guestbook.jsp?guestbookName="+guestbookName);
	     
	}

}
