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

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GetAllUser implements UserService {

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
        return UserServiceType.GET_ALL_USER;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: GetAllUser:: Inside ExecuteService Method :===");

        /*----Find All The Record Of User----*/
        List<User> allUser = userRepo.findAll();

        /*----If Record Is Empty Then Simply Throw Exception----*/
        if (allUser.isEmpty()) {
            throw new GeneralException("No Record Found!");
        }

        /*----Create ArrayList Of UserResponseDTO----*/
        List<UserResponseDTO> listOfUser = new ArrayList<>();

        /*----Fetch One User At A Time, Put Into ArrayList----*/
        allUser.forEach(user -> {
            /*----Convert User into UserResponseDTO:----*/
            uResponseDTO = this.modelMapper.map(user,UserResponseDTO.class);
            listOfUser.add(uResponseDTO);
        });

        /*----Now Simply Return Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Fetch All The Record");
        response.setData(listOfUser);

        return response;

    }
}
