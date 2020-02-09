package helloworld;

import com.fasterxml.jackson.databind.ObjectMapper;
import helloworld.domain.GatewayResponse;
import helloworld.domain.NameRequest;
import helloworld.domain.ResponseGreeting;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AppTest {
    private App app;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        app = new App();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void givenUnencodedRequestFromGatewayApiWhenBodyIsZohaibThenReturnHelloZohaib() throws IOException {
        // Given
        JSONObject eventRequest = objectMapper.readValue(getClass().getResource("/test-event-unencoded.json"), JSONObject.class);

        // When
        GatewayResponse result = app.handleRequest(eventRequest, null);

        // Then
        assertEquals(result.getStatusCode(), 200);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        ResponseGreeting expectedResponseGreeting = new ResponseGreeting(new NameRequest("zohaib"));
        ResponseGreeting responseGreeting = objectMapper.readValue(result.getBody(), ResponseGreeting.class);
        assertEquals(expectedResponseGreeting, responseGreeting);
        assertEquals("Hello zohaib", responseGreeting.getMessage());
    }
}
