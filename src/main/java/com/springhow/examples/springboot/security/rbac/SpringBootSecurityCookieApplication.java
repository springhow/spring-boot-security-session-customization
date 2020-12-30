package com.springhow.examples.springboot.security.rbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import java.util.concurrent.ConcurrentHashMap;

@EnableSpringHttpSession
@SpringBootApplication
public class SpringBootSecurityCookieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityCookieApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CookieSerializer cookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookieName("CUSTOMSESSIONID");
        defaultCookieSerializer.setUseHttpOnlyCookie(false);
        defaultCookieSerializer.setCookiePath("/");
        defaultCookieSerializer.setDomainName("somedomain.com");
        defaultCookieSerializer.setUseSecureCookie(true);
        defaultCookieSerializer.setSameSite("none");
        return defaultCookieSerializer;
    }

    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }
}
