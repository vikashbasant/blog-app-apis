package co.blog.config;

public class SecurityConfigConstants {
    public static final String[] PUBLIC_URL = {
            "/v1/auth/**",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**"
    };
}
