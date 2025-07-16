//package pk.imbilalbutt.bussiness.configuration;
//
//import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
//import org.eclipse.jetty.server.HttpConfiguration;
//import org.eclipse.jetty.server.HttpConnectionFactory;
//import org.eclipse.jetty.server.ServerConnector;
//import org.eclipse.jetty.server.SslConnectionFactory;
//import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class Http2Config {
//
//    @Bean
//    public WebServerFactoryCustomizer<JettyServletWebServerFactory> http2Customizer() {
//        return factory -> factory.addServerCustomizers(server -> {
//            HttpConfiguration config = new HttpConfiguration();
//            config.setSendServerVersion(false);
//
//            ServerConnector connector = new ServerConnector(server,
//                    new SslConnectionFactory(),
//                    new HttpConnectionFactory(config),
//                    new HTTP2ServerConnectionFactory(config));
//
//            connector.setPort(8443);
//            server.addConnector(connector);
//        });
//    }
//}
