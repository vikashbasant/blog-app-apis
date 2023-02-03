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
public class UpdateUser implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Response response;

    @Autowired
    private UserResponseDTO userResponseDTO;

    @Override
    public UserServiceType getServiceType () {
        return UserServiceType.UPDATE_USER;
    }


    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: UpdateUser:: Inside ExecuteService Method :===");

        UserDTO uDTO = (UserDTO) t;


        Optional.ofNullable(userRepo.findById(uDTO.getUserId()).orElseThrow(() -> new GeneralException(
                "User Not Found With UserId = " + uDTO.getUserId())));

        userRepo.updateId(uDTO.getUserId(), uDTO.getName(), uDTO.getEmail(), uDTO.getPassword(), uDTO.getAbout());


        userResponseDTO.setId(uDTO.getUserId());
        userResponseDTO.setName(uDTO.getName());
        userResponseDTO.setEmail(uDTO.getEmail());
        userResponseDTO.setPassword(uDTO.getPassword());
        userResponseDTO.setAbout(uDTO.getAbout());

        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Update the User with UserId = " + uDTO.getUserId());
        response.setData(userResponseDTO);


        return response;

    }
}
