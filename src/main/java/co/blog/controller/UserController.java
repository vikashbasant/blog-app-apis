package co.blog.controller;

import co.blog.payloads.Response;
import co.blog.payloads.UserDTO;
import co.blog.util.userUtil.UserService;
import co.blog.util.userUtil.UserServiceFactory;
import co.blog.util.userUtil.UserServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceFactory factory;

    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody @Valid UserDTO request) {
        log.info("===: UserController:: Inside createUser Method :===");
        UserService service = factory.getService(UserServiceType.CREATE_USER);
        Response response = service.executeService(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody @Valid UserDTO request) {
        log.info("===: UserController:: Inside updateUser Method :===");
        UserService service = factory.getService(UserServiceType.UPDATE_USER);
        Response response = service.executeService(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Response> getUser(@PathVariable @Valid Integer userId) {
        log.info("===: UserController:: Inside getUser Method :===");
        UserService service = factory.getService(UserServiceType.GET_USER_BY_ID);
        Response response = service.executeService(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-user")
    public ResponseEntity<Response> getAllUsers() {
        log.info("===: UserController:: Inside getAllUsers Method :===");
        UserService service = factory.getService(UserServiceType.GET_ALL_USER);
        Response response = service.executeService("");
        return ResponseEntity.ok(response);

    }


    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable("userId") @Valid Integer uId) {
        log.info("===: UserController:: Inside deleteUser Method :===");
        UserService service = factory.getService(UserServiceType.DELETE_USER);
        Response response = service.executeService(uId);
        return ResponseEntity.ok(response);
    }


}
