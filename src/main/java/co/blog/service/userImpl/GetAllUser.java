package co.blog.service.userImpl;

import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.UserResponseDTO;
import co.blog.repository.UserRepo;
import co.blog.util.userUtil.UserService;
import co.blog.util.userUtil.UserServiceType;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public UserServiceType getServiceType () {
        return UserServiceType.GET_ALL_USER;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: GetAllUser:: Inside ExecuteService Method :===");


        List<User> allUser = userRepo.findAll();

        if (allUser.isEmpty()) {
            throw new GeneralException("No Record Found!");
        }

        List<UserResponseDTO> listOfUser = new ArrayList<>();

        allUser.forEach(user -> {
            UserResponseDTO uDTO = new UserResponseDTO();
            uDTO.setId(user.getId());
            uDTO.setName(user.getName());
            uDTO.setEmail(user.getEmail());
            uDTO.setPassword(user.getPassword());
            uDTO.setAbout(user.getAbout());
            listOfUser.add(uDTO);
        });

        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Fetch All The Record");
        response.setData(listOfUser);

        return response;

    }
}
