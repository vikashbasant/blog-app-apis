package co.blog.service.userImpl;

import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.UserResponseDTO;
import co.blog.repository.UserRepo;
import co.blog.util.userUtil.UserService;
import co.blog.util.userUtil.UserServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private UserResponseDTO uResponseDTO;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserServiceType getServiceType () {
        return UserServiceType.DELETE_USER;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: DeleteUser:: Inside ExecuteService Method :===");

        Integer userId = (Integer) t;

        /*----Before delete the User, Fetch the User:----*/
        Optional<User> byId = Optional.ofNullable(userRepo.findById(userId).orElseThrow(() -> new GeneralException("User Not " +
                "Found With UserId = " + userId)));

        /*----Now Simply Delete the User with userId:----*/
        userRepo.deleteById(userId);

        /*----Now Convert the User to UserResponseDTO:----*/
        uResponseDTO = this.modelMapper.map(byId.get(), UserResponseDTO.class);

        /*----Now Simply Return Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Delete The User With UserId = " + userId);
        response.setData(uResponseDTO);

        return response;

    }
}
