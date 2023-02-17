package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.uDTO.UserDTO;
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

    /**
     * For Create User API Here @Valid Annotation used for Enable the Validation:
     * @param request UserDTO
     * @return UserResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @PostMapping("/create")
    public ResponseEntity<Response> createUser (@RequestBody @Valid UserDTO request) throws GeneralException {
        log.info("===: UserController:: Inside createUser Method :===");
        UserService service = factory.getService(UserServiceType.CREATE_USER);
        Response response = service.executeService(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * For UpdateUser API
     * @param request UserDTO
     * @return UserResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @PutMapping("/update")
    public ResponseEntity<Response> updateUser (@RequestBody @Valid UserDTO request) throws GeneralException {
        log.info("===: UserController:: Inside updateUser Method :===");
        UserService service = factory.getService(UserServiceType.UPDATE_USER);
        Response response = service.executeService(request);
        return ResponseEntity.ok(response);
    }

    /**
     * For Get Single User
     * @param userId Pass The userId
     * @return UserResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Response> getUser (@PathVariable @Valid Integer userId) throws GeneralException {
        log.info("===: UserController:: Inside getUser Method :===");
        UserService service = factory.getService(UserServiceType.GET_USER);
        Response response = service.executeService(userId);
        return ResponseEntity.ok(response);
    }

    /**
     * For Get All User
     * @return list of UserResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @GetMapping("/get-all-user")
    public ResponseEntity<Response> getAllUsers () throws GeneralException {
        log.info("===: UserController:: Inside getAllUsers Method :===");
        UserService service = factory.getService(UserServiceType.GET_ALL_USER);
        Response response = service.executeService("");
        return ResponseEntity.ok(response);

    }


    /**
     * @purpose For Delete User With userId
     * @param uId
     * @return UserResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Response> deleteUser (@PathVariable("userId") @Valid Integer uId) throws GeneralException {
        log.info("===: UserController:: Inside deleteUser Method :===");
        UserService service = factory.getService(UserServiceType.DELETE_USER);
        Response response = service.executeService(uId);
        return ResponseEntity.ok(response);
    }


}
