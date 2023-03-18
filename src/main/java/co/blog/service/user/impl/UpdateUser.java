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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private PasswordEncoder passwordEncoder;


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
        User user = userRepo.findById(userId).orElseThrow(() -> new GeneralException(
                "User Not Found With UserId = " + userId));

        /*----Then Simply Update The User----*/
        user.setName(uDTO.getName());
        user.setUserPassword(this.passwordEncoder.encode(uDTO.getUserPassword()));
        user.setUserEmail(uDTO.getUserEmail());
        user.setUserAbout(uDTO.getUserAbout());


        User sUser = null;
        try {
            /*----Then Simply Save Updated User Into DB----*/
            sUser = this.userRepo.save(user);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                throw new DataIntegrityViolationException("Please Enter Unique EmailId");
            }
        }


        /*----Convert The uDTO to UserResponseDTO----*/
        uResponseDTO = this.modelMapper.map(sUser, UserResponseDTO.class);


        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Update The User With UserId = " + userId);
        response.setData(uResponseDTO);

        return response;

    }
}
