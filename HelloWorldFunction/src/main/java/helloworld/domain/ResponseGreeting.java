package helloworld.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseGreeting {

    private String message;

    public ResponseGreeting(NameRequest nameRequest) {
        message = "Hello " + nameRequest.getName();
    }
}
