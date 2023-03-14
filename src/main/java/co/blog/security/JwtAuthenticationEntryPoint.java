package co.blog.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * If JwtAuthentication Failed at any point then this commences method will execute.
     * @param request passing request as an argument of HttpServletRequest.
     * @param response passing response as an argument of HttpServletResponse.
     * @param authException If anything goes wrong then this AuthException will generate.
     * @throws IOException something wrong with your input or output then this IOException will generate.
     */
    @Override
    public void commence (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        log.info("===: JwtAuthenticationEntryPoint:: Inside commence Method :===");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!!!");
    }
}
