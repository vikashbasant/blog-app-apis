package co.blog.payloads.auth;

import co.blog.payloads.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JwtAuthResponse extends Response {
    private String token;
}
