package com.example.soapservice.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soapservice/*");
    }

    @Bean(name = "roles")
    public DefaultWsdl11Definition rolesWsdl11Definition(XsdSchema rolesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("RolesPort");
        wsdl11Definition.setLocationUri("/soapservice");
        wsdl11Definition.setTargetNamespace("http://www.example.com/soapservice/roles");
        wsdl11Definition.setSchema(rolesSchema);
        return wsdl11Definition;
    }

    @Bean(name = "users")
    public DefaultWsdl11Definition usersWsdl11Definition(XsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UsersPort");
        wsdl11Definition.setLocationUri("/soapservice");
        wsdl11Definition.setTargetNamespace("http://www.example.com/soapservice/users");
        wsdl11Definition.setSchema(usersSchema);
        return wsdl11Definition;
    }

    @Bean(name = "userswr")
    public DefaultWsdl11Definition userswrWsdl11Definition(XsdSchema userswrSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserswrPort");
        wsdl11Definition.setLocationUri("/soapservice");
        wsdl11Definition.setTargetNamespace("http://www.example.com/soapservice/users");
        wsdl11Definition.setSchema(userswrSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema rolesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("roles.xsd"));
    }

    @Bean
    public XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("users.xsd"));
    }

    @Bean
    public XsdSchema userswrSchema() {
        return new SimpleXsdSchema(new ClassPathResource("userswr.xsd"));
    }

}
