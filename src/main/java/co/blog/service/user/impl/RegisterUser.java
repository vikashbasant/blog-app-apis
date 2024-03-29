package co.blog.service.user.impl;

import co.blog.constants.BlogAppConstants;
import co.blog.constants.RoleConstants;
import co.blog.entity.Role;
import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.userdto.UserDTO;
import co.blog.payloads.userdto.UserResponseDTO;
import co.blog.repository.RoleRepo;
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
public class RegisterUser implements BlogService {

    @Autowired
    private UserRepo uRepo;

    @Autowired
    private RoleRepo rRepo;

    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserResponseDTO uResponseDTO;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.REGISTER_USER;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: RegisterUser:: Inside executeService Method :===");

        UserDTO uDTO = (UserDTO) t;

        User user = this.modelMapper.map(uDTO, User.class);

        /*---- Now We have Encoded The Password ----*/
        user.setUserPassword(this.passwordEncoder.encode(user.getUserPassword()));


        // For Roles:
        String userRole = uDTO.getUserRole();
        Integer userRoleId = userRole.equalsIgnoreCase(RoleConstants.ADMIN_USER_NAME) ? RoleConstants.ADMIN_USER :
                RoleConstants.NORMAL_USER;
        /*----If Any One Register through Register API, Then we can assign the NORMAL User----*/
        Role role = this.rRepo.findById(userRoleId).orElseThrow(() -> new GeneralException("Role Not " +
                "Found With RoleId = " + userRoleId));

        /*----Now Simply Add the Role into User----*/
        user.getRoles().add(role);;

        User sUser = null;
        try {
            /*---- Now Simply Save the User----*/
            sUser = this.uRepo.save(user);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                throw new DataIntegrityViolationException("Please Enter Unique EmailId");
            }
        }


        /*----Convert User to UserResponseDTO:----*/
        uResponseDTO = this.modelMapper.map(sUser, UserResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("User Register Successfully");
        response.setData(uResponseDTO);

        return response;

    }
}
