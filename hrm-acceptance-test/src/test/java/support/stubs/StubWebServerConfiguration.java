package support.stubs;

import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContext;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@Configuration
public class StubWebServerConfiguration {

	private final static int listenPort = 8081;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server wsMockServer() throws Exception {
		final Server server = new Server(listenPort);
		final ServletContextHandler servletCtx = servletContextHandler();

		servletCtx.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				springWebApplicationContext(servletCtx.getServletContext()));

		servletCtx.addServlet(
				servletHolder(new MessageDispatcherServlet(), "employee-ws"),
				"/hrmservice/*");

		server.setHandler(servletCtx);

		return server;
	}

	private ServletContextHandler servletContextHandler() throws Exception {
		final ServletContextHandler result = new ServletContextHandler(SESSIONS);

		result.setContextPath("/hrm-ws-service");
		result.setAliases(true);
		URL url = new URL("file://"
				+ new File("src/test/resources").getAbsolutePath());
		result.setBaseResource(new FileResource(url));

		return result;
	}

	private Object springWebApplicationContext(ServletContext servletCtx) {
		final ConfigurableWebApplicationContext result = new StaticWebApplicationContext();
		result.setParent(applicationContext);
		result.setServletContext(servletCtx);
		result.refresh();
		return result;
	}

	private ServletHolder servletHolder(FrameworkServlet servlet, String name) {
		final ServletHolder result = new ServletHolder();
			
		servlet.setContextConfigLocation(name + "-servlet.xml");

		result.setName(name);
		result.setServlet(servlet);

		return result;
	}
}
