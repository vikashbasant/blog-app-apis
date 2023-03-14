package co.blog.service.post.impl;

import co.blog.constants.BlogAppConstants;
import co.blog.entity.Category;
import co.blog.entity.Post;
import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.postdto.PostDTO;
import co.blog.payloads.postdto.PostResponseDTO;
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
public class UpdatePost implements BlogService {

    @Autowired
    private PostRepo pRepo;

    @Autowired
    private CategoryRepo cRepo;

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
        return BlogServiceType.UPDATE_POST;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: UpdatePost:: Inside executeService Method :===");

        PostDTO pDTO = (PostDTO) t;
        Integer postId = (Integer) u;

        /*----Fetch Post with postId----*/
        Post post = pRepo.findById(postId).orElseThrow(() -> new GeneralException("Post Not Found With postId = " + postId));

        /*----Fetch Category with categoryId----*/
        Category category = cRepo.findById(pDTO.getCategoryId()).orElseThrow(() -> new GeneralException("Category Not " +
                "Found With CategoryId = " + pDTO.getCategoryId()));

        /*----Fetch User with userId----*/
        User user = uRepo.findById(pDTO.getUserId()).orElseThrow(() -> new GeneralException("User Not " +
                "Found With UserId = " + pDTO.getUserId()));

        /*----Update the Post:----*/
        post.setPostTitle(pDTO.getPostTitle());
        post.setPostContent(pDTO.getPostContent());
        post.setPostImageName(pDTO.getPostImageName());
        post.setUser(user);
        post.setCategory(category);

        /*----Save post into DB:----*/
        Post sPost = pRepo.save(post);

        /*----Convert sPost into PostResponseDTO----*/
        pResponseDTO = this.modelMapper.map(sPost, PostResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Update The Post With postId = " + postId);
        response.setData(pResponseDTO);

        return response;
    }
}
