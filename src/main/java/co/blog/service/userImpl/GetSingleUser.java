package co.blog.service.userImpl;

import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.UserDTO;
import co.blog.payloads.UserResponseDTO;
import co.blog.repository.UserRepo;
import co.blog.util.userUtil.UserService;
import co.blog.util.userUtil.UserServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetSingleUser implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Response response;

    @Autowired
    private UserResponseDTO userResponseDTO;

    @Override
    public UserServiceType getServiceType () {
        return UserServiceType.GET_USER_BY_ID;
    }

    @Override
    public <T> Response executeService (T t) {

        log.info("===: GetSingleUser:: Inside ExecuteService Method :===");

        Integer userId = (Integer) t;

        try {

            Optional<User> byId = Optional.ofNullable(userRepo.findById(userId).orElseThrow(() -> new GeneralException("USER_500", "User is " +
                    "not Present for this UserId = " + userId, "User Not Found")));

            userResponseDTO.setId(byId.get().getId());
            userResponseDTO.setName(byId.get().getName());
            userResponseDTO.setEmail(byId.get().getEmail());
            userResponseDTO.setPassword(byId.get().getPassword());
            userResponseDTO.setAbout(byId.get().getAbout());

            response.setStatus("SUCCESS");
            response.setStatusCode("200");
            response.setMessage("Successfully Fetch The User With UserId = " + byId.get().getId());
            response.setData(userResponseDTO);

        } catch (GeneralException e) {

            if(e instanceof GeneralException){

                response.setStatus("FAILURE");
                response.setStatusCode(e.getStatusCode());
                response.setMessage(e.getMessage());
                response.setErrorMessage(e.getErrorMessages());

            }
        }

        return response;
    }
}
