package co.blog.service.user.impl;

import co.blog.config.BlogAppConstants;
import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.uDTO.UserResponseDTO;
import co.blog.repository.UserRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetAllUser implements BlogService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_ALL_USER;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: GetAllUser:: Inside ExecuteService Method :===");

        /*----Find All The Record Of User----*/
        List<User> allUser = userRepo.findAll();

        /*----If Record Is Empty Then Simply Throw Exception----*/
        if (allUser.isEmpty()) {
            throw new GeneralException("No Record Found!");
        }

        /*----Fetch One User At A Time, Collect Into ArrayList----*/
        List<UserResponseDTO> listOfUser = allUser.stream().map(user -> modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());

        /*----Now Simply Return Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Fetch All The Record");
        response.setData(listOfUser);

        return response;

    }
}
