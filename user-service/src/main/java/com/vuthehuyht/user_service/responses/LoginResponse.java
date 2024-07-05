package com.vuthehuyht.user_service.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
        @JsonProperty("access_token") String accessToken
) {
}
