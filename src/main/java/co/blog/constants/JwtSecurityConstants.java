package co.blog.constants;

public class JwtSecurityConstants {

    /*----Adding private constructor to hide the implicit public one ----*/
    private JwtSecurityConstants() {

    }
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * (long)60;

    public static final String SECRET_KEY = "jwtTokenKey";
}
