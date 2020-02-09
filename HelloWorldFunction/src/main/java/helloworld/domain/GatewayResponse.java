package helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * POJO containing response object for API Gateway.
 */
@AllArgsConstructor
@Data
public class GatewayResponse<T> {

    private final T body;
    private final Map<String, String> headers;
    private final int statusCode;
}
