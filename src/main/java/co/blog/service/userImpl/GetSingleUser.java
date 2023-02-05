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
public class GetSingleUser implements UserService {

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
        return UserServiceType.GET_USER_BY_ID;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: GetSingleUser:: Inside ExecuteService Method :===");

        Integer userId = (Integer) t;

        /*----Fetch User With UserId----*/
        Optional<User> byId =
                Optional.ofNullable(userRepo.findById(userId).orElseThrow(() -> new GeneralException("User Not " +
                        "Found With This UserId = " + userId)));

        /*----Convert User into UserResponseDTO:----*/
        uResponseDTO = this.modelMapper.map(byId.get(), UserResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Fetch The User With UserId = " + byId.get().getId());
        response.setData(uResponseDTO);


        return response;
    }
}
