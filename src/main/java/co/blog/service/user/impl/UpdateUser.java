package co.blog.service.user.impl;

import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.uDTO.UserDTO;
import co.blog.payloads.uDTO.UserResponseDTO;
import co.blog.repository.UserRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UpdateUser implements BlogService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Response response;

    @Autowired
    private UserResponseDTO uResponseDTO;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.UPDATE_USER;
    }


    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: UpdateUser:: Inside ExecuteService Method :===");

        UserDTO uDTO = (UserDTO) t;
        Integer userId = (Integer) u;

        /*----First Find The User With userId----*/
        Optional.ofNullable(userRepo.findById(userId).orElseThrow(() -> new GeneralException(
                "User Not Found With UserId = " + userId)));

        /*----Then Simply Update The User----*/
        userRepo.updateId(userId, uDTO.getName(), uDTO.getEmail(), uDTO.getPassword(),
                uDTO.getAbout());

        Optional<User> updatedUser = userRepo.findById(userId);

        /*----Convert The uDTO to UserResponseDTO----*/
        uResponseDTO = this.modelMapper.map(updatedUser, UserResponseDTO.class);


        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Update The User With UserId = " + userId);
        response.setData(uResponseDTO);


        return response;

    }
}
