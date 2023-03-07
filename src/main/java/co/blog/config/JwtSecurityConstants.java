package co.blog.config;

public class JwtSecurityConstants {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public static final String SECRET_KEY = "jwtTokenKey";
}
