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

@Service
@Slf4j
public class CreateUser implements BlogService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserResponseDTO uResponseDTO;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.CREATE_USER;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {
        log.info("===: CreateUser:: Inside ExecuteService Method :===");

        UserDTO uDTO = (UserDTO) t;

        /*----Convert userDto into user:----*/
        User user = this.userDtoToUser(uDTO);

        /*----Save User into DB:----*/
        User sUser = userRepo.save(user);

        /*----Convert User to UserResponseDTO:----*/
        uResponseDTO = this.userToUserResponseDTO(sUser);

        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("User Created Successfully");
        response.setData(uResponseDTO);

        return response;


    }


    public User userDtoToUser(UserDTO uDTO) {

        /*
        User user = new User();
        user.setId(uDTO.getId());
        user.setName(uDTO.getName());
        user.setEmail(uDTO.getEmail());
        user.setPassword(uDTO.getPassword());
        user.setAbout(uDTO.getAbout());
         */
        User user = this.modelMapper.map(uDTO, User.class);
        return user;
    }

    public UserResponseDTO userToUserResponseDTO(User user) {
        /*
        userResponseDTO.setId(saveUser.getId());
        userResponseDTO.setName(saveUser.getName());
        userResponseDTO.setEmail(saveUser.getEmail());
        userResponseDTO.setPassword(saveUser.getPassword());
        userResponseDTO.setAbout(saveUser.getAbout());
         */
        UserResponseDTO uResponseDTO = this.modelMapper.map(user, UserResponseDTO.class);
        return uResponseDTO;
    }


}
