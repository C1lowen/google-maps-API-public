package com.codefinity.googlemapsapi.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private String errorMessage;
}
