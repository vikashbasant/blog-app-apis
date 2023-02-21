package co.blog.service.post.impl;

import co.blog.entity.Post;
import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.pDTO.PostResponseDTO;
import co.blog.repository.PostRepo;
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
public class GetPostByUserId implements BlogService {

    @Autowired
    private PostRepo pRepo;

    @Autowired
    private UserRepo uRepo;

    @Autowired
    private Response response;

    @Autowired
    private PostResponseDTO pResponseDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_POST_BY_USER_ID;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: GetPostByUserId:: Inside executeService Method :===");

        Integer userId = (Integer) t;

        /*----Find User with UserId----*/
        User user = uRepo.findById(userId).orElseThrow(() -> new GeneralException(
                "User Not Found With UserId = " + userId));

        /*----Now Find the post with user----*/
        List<Post> posts = pRepo.findByUser(user);

        /*----Now Iterate with listOfPost and convert PostResponseDTO----*/
        List<PostResponseDTO> listOfPost = posts.stream().map(post -> modelMapper.map(post, PostResponseDTO.class)).collect(Collectors.toList());

        /*----Now Simply Return Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Fetch All The Posts With UserId = " + userId);
        response.setData(listOfPost);

        return response;
    }
}
