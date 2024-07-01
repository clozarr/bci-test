package com.bci.test.demo.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.net.URI;
import java.util.List;

@Data
@Builder
@ToString
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "400")
    private Integer code;

    @Schema(description = "Exception type", example = "Bad Request/Internal Server Error")
    private String reason;

    @Schema(description = "error message")
    private List<String> messages;

    @Schema(description = "Timestamp of the error", example = "2024-04-10 12:00:00")
    private String date;

    @Schema(description = "Request URI", example = "/users/v1/api")
    private URI path;

}
