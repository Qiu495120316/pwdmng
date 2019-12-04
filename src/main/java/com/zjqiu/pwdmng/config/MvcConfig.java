package com.zjqiu.pwdmng.config;

import com.zjqiu.pwdmng.resolver.ThymeleafResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class MvcConfig {

    @Bean
    public LocaleResolver localeResolver(){
        return new ThymeleafResolver();
    }
}
