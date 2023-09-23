package com.abnamro.api.response;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class ErrorResponse {

    String message;
}
