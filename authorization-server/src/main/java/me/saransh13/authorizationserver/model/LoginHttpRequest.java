package me.saransh13.authorizationserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginHttpRequest {
    @Email(message = "Invalid email provided")
    private String email;
    @Min(value = 6, message = "Invalid Password was sent in the request")
    private String password;
}
