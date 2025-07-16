package pk.imbilalbutt.bussiness.configuration;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class JettyCustomizer implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {

    @Override
    public void customize(JettyServletWebServerFactory factory) {

        factory.setPort(9090);
        factory.setContextPath("/Reservation-System");
        // Add any additional Jetty configurations if needed
    }
}