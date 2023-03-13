package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.auth.JwtAuthRequest;
import co.blog.payloads.udto.UserDTO;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceFactory;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/auth/")
@Slf4j
public class AuthController {

    @Autowired
    private BlogServiceFactory factory;

    /**
     * This API are used to Login the User.
     * @param request Passing JwtAuthRequest as an argument of RequestBody.
     * @return return The Response i.e token.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PostMapping("/login")
    public ResponseEntity<Response> createToken(@RequestBody @Valid JwtAuthRequest request) throws GeneralException {
        log.info("===: AuthController:: Inside createToken Method :===");
        BlogService service = factory.getService(BlogServiceType.LOGIN);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * This API are used to Register the User.
     * @param request Passing UserDTO as an argument of RequestBody.
     * @return return Response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid UserDTO request) throws GeneralException {
        log.info("===: AuthController:: Inside registerUser Method :===");
        BlogService service = factory.getService(BlogServiceType.REGISTER_USER);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
