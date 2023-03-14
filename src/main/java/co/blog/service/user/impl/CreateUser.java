package co.blog.service.user.impl;

import co.blog.constants.BlogAppConstants;
import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.userdto.UserDTO;
import co.blog.payloads.userdto.UserResponseDTO;
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
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("User Created Successfully");
        response.setData(uResponseDTO);

        return response;


    }


    public User userDtoToUser(UserDTO uDTO) {
        return this.modelMapper.map(uDTO, User.class);

    }

    public UserResponseDTO userToUserResponseDTO(User user) {
        return this.modelMapper.map(user, UserResponseDTO.class);
    }


}
