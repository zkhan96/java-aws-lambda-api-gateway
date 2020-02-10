package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import helloworld.domain.NameRequest;
import helloworld.domain.ResponseGreeting;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 *
 * @author Zohaib Khan
 */
public class App implements RequestHandler<JSONObject, APIGatewayV2ProxyResponseEvent> {

    /**
     * @param request This takes a JSONObject request (example in test resources).
     *                The body in the request can be parsed into an object
     * @return GatewayResponse pojo which follows the convention for how a response from a Lambda should look like.
     */
    public APIGatewayV2ProxyResponseEvent handleRequest(final JSONObject request, final Context context) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            NameRequest nameRequest = objectMapper.readValue(request.get("body").toString(), NameRequest.class);

            ResponseGreeting responseGreeting = new ResponseGreeting(nameRequest);
            return generateResponseObject(objectMapper.writeValueAsString(responseGreeting));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private APIGatewayV2ProxyResponseEvent generateResponseObject(String responseGreeting) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        APIGatewayV2ProxyResponseEvent response = new APIGatewayV2ProxyResponseEvent();
        response.setBody(responseGreeting);
        response.setHeaders(headers);
        response.setStatusCode(200);
        return response;
    }
}
