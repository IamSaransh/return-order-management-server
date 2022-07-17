package me.saransh13.authorizationserver.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpResponse {
    private int status;
    private HttpStatus httpStatus;
    private String reason;
    private String message;
}
