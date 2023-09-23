package com.abnamro.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CustomerRegisterRequest {

    @Schema(description = "Customer name")
    String name;

    @Schema(description = "Customer address")
    String address;

    @Schema(description = "Customer date of birth")
    @NotNull
    LocalDate dob;

    @Schema(description = "Customer document id")
    String idDocument;

    @Schema(description = "Customer username")
    @NotEmpty
    String username;

    @Schema(description = "Customer country of residence")
    @NotEmpty
    String country;
}
