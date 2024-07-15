package com.vuthehuyht.apigateway.config;

import com.vuthehuyht.apigateway.repositories.AuthWebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Value("${auth-service.url}") private String authServiceUrl;

    @Bean
    WebClient webClient() {
        return WebClient.builder().baseUrl(authServiceUrl).build();
    }

    @Bean
    AuthWebClient authWebClient(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient)).build();
        return httpServiceProxyFactory.createClient(AuthWebClient.class);
    }
}
