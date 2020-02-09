package helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * POJO containing response object for API Gateway.
 */
@AllArgsConstructor
@Data
public class GatewayResponse {

    /**
     * response body *must* be a String, not a POJO
     */
    private final String body;
    private final Map<String, String> headers;
    private final int statusCode;
}
