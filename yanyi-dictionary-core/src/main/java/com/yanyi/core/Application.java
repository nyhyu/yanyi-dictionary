package com.yanyi.core;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String [] args) {

        LOGGER.info("Start yanyi dictionary system...");
        LOGGER.info("Begin to startup restful service ...");
        SpringApplication application;
        try {
            application = new SpringApplication("classpath:spring/application.xml");
            application.run();
            LOGGER.info("Startup restful service success ...");
        } catch (Exception e) {
            LOGGER.error("Startup spring application failed", e);
            System.exit(1);
        }
    }

    /**
     * 配置一个TomcatEmbeddedServletContainerFactory bean
     * @return
     */
    //@Bean
    //public EmbeddedServletContainerFactory servletContainer() {

    //    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {

    //        @Override
    //        protected void postProcessContext(Context context) {

    //            SecurityConstraint securityConstraint = new SecurityConstraint();
    //            securityConstraint.setUserConstraint("CONFIDENTIAL");
    //            SecurityCollection collection = new SecurityCollection();
    //            collection.addPattern("/*");
    //            securityConstraint.addCollection(collection);
    //            context.addConstraint(securityConstraint);
    //        }
    //    };
    //    tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
    //    return tomcat;
    //}

    ///**
    // * 让我们的应用支持HTTP是个好想法，但是需要重定向到HTTPS，
    // * 但是不能同时在application.properties中同时配置两个connector，
    // * 所以要以编程的方式配置HTTP connector，然后重定向到HTTPS connector
    // * @return Connector
    // */
    //private Connector initiateHttpConnector() {
    //    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    //    connector.setScheme("http");
    //    // http端口
    //    connector.setPort(8080);
    //    connector.setSecure(false);
    //    // application.properties中配置的https端口
    //    connector.setRedirectPort(8443);
    //    return connector;
    //}
}
