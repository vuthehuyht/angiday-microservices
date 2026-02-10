package com.vuthehuyht.apigateway.repositories;

import com.vuthehuyht.apigateway.requests.IntrospectRequest;
import com.vuthehuyht.apigateway.responses.ApiResponse;
import com.vuthehuyht.apigateway.responses.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface AuthWebClient {
    @PostExchange(url = "/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request);
}
