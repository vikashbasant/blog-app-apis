package co.blog.service.userImpl;

import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
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
public class DeleteUser implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Response response;

    @Autowired
    private UserResponseDTO userResponseDTO;


    @Override
    public UserServiceType getServiceType () {
        return UserServiceType.DELETE_USER;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: DeleteUser:: Inside ExecuteService Method :===");

        Integer userId = (Integer) t;


        Optional<User> byId = Optional.ofNullable(userRepo.findById(userId).orElseThrow(() -> new GeneralException("User Not " +
                "Found With UserId = " + userId)));

        userRepo.deleteById(userId);

        userResponseDTO.setId(byId.get().getId());
        userResponseDTO.setName(byId.get().getName());
        userResponseDTO.setEmail(byId.get().getEmail());
        userResponseDTO.setPassword(byId.get().getPassword());
        userResponseDTO.setAbout(byId.get().getAbout());

        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Delete The User With UserId = " + userId);
        response.setData(userResponseDTO);


        return response;
    }
}
