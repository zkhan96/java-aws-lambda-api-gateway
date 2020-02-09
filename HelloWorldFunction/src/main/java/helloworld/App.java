package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import helloworld.domain.GatewayResponse;
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
public class App implements RequestHandler<JSONObject, GatewayResponse> {

    /**
     * @param request This takes a JSONObject request (example in test resources).
     *                The body in the request can be parsed into an object
     * @return GatewayResponse pojo which follows the convention for how a response from a Lambda should look like.
     */
    public GatewayResponse handleRequest(final JSONObject request, final Context context) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("X-Custom-Header", "application/json");
            ObjectMapper om = new ObjectMapper();
            NameRequest nameRequest = om.readValue(request.get("body").toString(), NameRequest.class);
            ResponseGreeting responseGreeting = new ResponseGreeting(nameRequest);
            return new GatewayResponse(om.writeValueAsString(responseGreeting), headers, 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
