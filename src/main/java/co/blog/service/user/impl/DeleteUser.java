package co.blog.service.user.impl;

import co.blog.constants.BlogAppConstants;
import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
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
public class DeleteUser implements BlogService {

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
        return BlogServiceType.DELETE_USER;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: DeleteUser:: Inside ExecuteService Method :===");

        Integer userId = (Integer) t;

        /*----Before delete the User, Fetch the User:----*/
        User byId = userRepo.findById(userId).orElseThrow(() -> new GeneralException("User Not " +
                "Found With UserId = " + userId));

        /*----Now Simply Delete the User with userId:----*/
        userRepo.deleteById(userId);

        /*----Now Convert the User to UserResponseDTO:----*/
        uResponseDTO = this.modelMapper.map(byId, UserResponseDTO.class);

        /*----Now Simply Return Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Delete The User With UserId = " + userId);
        response.setData(uResponseDTO);

        return response;

    }
}
