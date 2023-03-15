package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.userdto.UserDTO;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceFactory;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@Slf4j
public class UserController {

    @Autowired
    private BlogServiceFactory factory;


    /**
     * This API are used to createUser.
     * @param request passing UserDTO as an argument of RequestBody.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PostMapping("/create")
    @ApiIgnore
    public ResponseEntity<Response> createUser (@RequestBody @Valid UserDTO request) throws GeneralException {
        log.info("===: UserController:: Inside createUser Method :===");
        BlogService service = factory.getService(BlogServiceType.CREATE_USER);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * This API are used to updateUser.
     * @param request passing UserDTO as an argument of RequestBody.
     * @param userId passing userId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<Response> updateUser (@RequestBody @Valid UserDTO request, @PathVariable @Valid Integer userId) throws GeneralException {
        log.info("===: UserController:: Inside updateUser Method :===");
        BlogService service = factory.getService(BlogServiceType.UPDATE_USER);
        Response response = service.executeService(request, userId);
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to getUser.
     * @param userId passing userId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Response> getUser (@PathVariable @Valid Integer userId) throws GeneralException {
        log.info("===: UserController:: Inside getUser Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_USER);
        Response response = service.executeService(userId, "");
        return ResponseEntity.ok(response);
    }

    /**
     * This API are used to getAllUsers.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all-user")
    public ResponseEntity<Response> getAllUsers () throws GeneralException {
        log.info("===: UserController:: Inside getAllUsers Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_ALL_USER);
        Response response = service.executeService("", "");
        return ResponseEntity.ok(response);

    }


    /**
     * This API are used to deleteUser.
     * @param uId passing uId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    /*---- Only Admin Can Delete The User ----*/
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Response> deleteUser (@PathVariable("userId") @Valid Integer uId) throws GeneralException {
        log.info("===: UserController:: Inside deleteUser Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_USER);
        Response response = service.executeService(uId, "");
        return ResponseEntity.ok(response);
    }


}
