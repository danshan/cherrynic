package com.shanhh.genie.cherrynic.daocloud.config;

import com.shanhh.genie.cherrynic.daocloud.repo.client.DaocloudClient;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shanhonghao
 * @since 2018-04-15 11:55
 */
@Configuration
@Setter
@Getter
public class DaocloudConfig {

    @Value("${cn.daocloud.token:}")
    private String token;

    @Bean
    public DaocloudClient daocloudClient() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(new AuthorizationInterceptor())
                .target(DaocloudClient.class, "https://openapi.daocloud.io");
    }

    private class AuthorizationInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate template) {
            template.header("Authorization", String.format("token %s", token));
        }
    }
}
