package co.blog.service.user.impl;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.uDTO.UserDTO;
import co.blog.payloads.uDTO.UserResponseDTO;
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
public class UpdateUser implements UserService {

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
        return UserServiceType.UPDATE_USER;
    }


    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: UpdateUser:: Inside ExecuteService Method :===");

        UserDTO uDTO = (UserDTO) t;

        /*----First Find The User With userId----*/
        Optional.ofNullable(userRepo.findById(uDTO.getUserId()).orElseThrow(() -> new GeneralException(
                "User Not Found With UserId = " + uDTO.getUserId())));

        /*----Then Simply Update The User----*/
        userRepo.updateId(uDTO.getUserId(), uDTO.getName(), uDTO.getEmail(), uDTO.getPassword(), uDTO.getAbout());

        /*----Convert The uDTO to UserResponseDTO----*/
        uResponseDTO = this.modelMapper.map(uDTO, UserResponseDTO.class);
        uResponseDTO.setId(uDTO.getUserId());

        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Update The User With UserId = " + uDTO.getUserId());
        response.setData(uResponseDTO);


        return response;

    }
}
