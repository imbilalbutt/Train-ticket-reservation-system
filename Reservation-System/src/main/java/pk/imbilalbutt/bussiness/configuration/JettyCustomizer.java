//package pk.imbilalbutt.bussiness.configuration;
//
//import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JettyCustomizer implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {
//
//    @Override
//    public void customize(JettyServletWebServerFactory factory) {
//
//        factory.setPort(4000); // Port inside  DOCKER container, should match with Dockerfile EXPOSE and docker-compose SERVER PORT
//        factory.setContextPath("/reservation-system");
//        // Add any additional Jetty configurations if needed
//    }
//}