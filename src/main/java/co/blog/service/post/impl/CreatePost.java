package co.blog.service.post.impl;

import co.blog.entity.Category;
import co.blog.entity.Post;
import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.pDTO.PostDTO;
import co.blog.payloads.pDTO.PostResponseDTO;
import co.blog.repository.CategoryRepo;
import co.blog.repository.PostRepo;
import co.blog.repository.UserRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreatePost implements BlogService {

    @Autowired
    private PostRepo pRepo;

    @Autowired
    private UserRepo uRepo;

    @Autowired
    private CategoryRepo cRepo;

    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PostResponseDTO pResponseDTO;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.CREATE_POST;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: CreatePost:: Inside executeService Method :===");

        PostDTO pDTO = (PostDTO) t;

        /*----Fetch userId from pDTO----*/
        Integer userId = pDTO.getUserId();
        /*----Fetch user with userId----*/
        User user = uRepo.findById(userId).orElseThrow(() -> new GeneralException("User Not " +
                "Found With UserId = " + userId));

        /*----Fetch categoryId from pDTO----*/
        Integer categoryId = pDTO.getCategoryId();
        /*----Fetch category with categoryId----*/
        Category category = cRepo.findById(categoryId).orElseThrow(() -> new GeneralException("Category Not " +
                "Found With CategoryId = " + categoryId));

        /*----Convert pDTO into Post:----*/
        Post post = new Post();
        post.setPostId(pDTO.getPostId());
        post.setPostTitle(pDTO.getPostTitle());
        post.setPostContent(pDTO.getPostContent());
        post.setImageName(pDTO.getImageName());
        post.setAddedDate(pDTO.getAddedDate());
        post.setUser(user);
        post.setCategory(category);

        /*----Save post into DB:----*/
        Post sPost = pRepo.save(post);

        /*----Convert sPost into PostResponseDTO----*/
        pResponseDTO = this.modelMapper.map(sPost, PostResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Post Created Successfully");
        response.setData(pResponseDTO);

        return response;
    }
}
