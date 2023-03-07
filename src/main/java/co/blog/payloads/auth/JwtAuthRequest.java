package co.blog.payloads.auth;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
