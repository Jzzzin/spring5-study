package ch16.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();

        // ServletConfig - WebConfig 구성 클래스에 해당
        appContext.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");

        ServletRegistration.Dynamic dispatcher =
                container.addServlet("appServlet", new DispatcherServlet(appContext));

        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(null, 5000000, 5000000, 0);
        // <=> <multipart-config>
        dispatcher.setMultipartConfig(multipartConfigElement);

        dispatcher.setLoadOnStartup(1);
        // ServletMappings
        dispatcher.addMapping("/");

    }
}
