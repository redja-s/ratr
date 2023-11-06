package com.ratr.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericJsonResponse {
    private String message;
}
