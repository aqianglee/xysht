package com.aqiang.xysht;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class ManualTest {
	public static void main(String[] args) {
		try {
			Server server = new Server(8080);
			WebAppContext context = new WebAppContext();
			context.setResourceBase("src/main/webapp");
			context.setContextPath("/xysht");
			context.setParentLoaderPriority(true);
			server.setHandler(context);
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
