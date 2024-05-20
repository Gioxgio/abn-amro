package com.bank.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class CustomerLogonRequest {

    @Schema(description = "Customer username")
    @NotEmpty
    String username;

    @Schema(description = "Customer password")
    @NotEmpty
    String password;
}
