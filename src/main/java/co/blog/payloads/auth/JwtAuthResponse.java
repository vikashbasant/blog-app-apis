package co.blog.payloads.auth;

import co.blog.payloads.Response;
import lombok.Data;

@Data
public class JwtAuthResponse extends Response {
    private String token;
}
