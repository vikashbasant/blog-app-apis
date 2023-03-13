package co.blog.service.auth.impl;

import co.blog.config.BlogAppConstants;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.auth.JwtAuthRequest;
import co.blog.payloads.auth.JwtAuthResponse;
import co.blog.security.JwtTokenHelper;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Login implements BlogService {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.LOGIN;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: Login:: Inside executeService Method:===");

        JwtAuthRequest request = (JwtAuthRequest) t;

        /*----Now call the authenticate Method for authentication----*/
        this.authenticate(request.getUsername(), request.getPassword());

        /*----Now call the loadUserByUsername Method for Creating UserDetails----*/
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        /*----Now Call the generateToken Method for generate the Token----*/
        String token = this.jwtTokenHelper.generateToken(userDetails);

        /*----Simply Return Response-----*/
        JwtAuthResponse response = new JwtAuthResponse();
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully generated Token.");
        response.setToken(token);

        return response;
    }


    private void authenticate (String username, String password) {

        log.info("===: Login:: Inside authenticate Method :===");
        UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(username,
                password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            log.error("Invalid Details i.e UserName & Password");
            throw new BadCredentialsException("Invalid UserName or Password");
        }

    }
}
