package co.blog.service.post.impl;

import co.blog.entity.Category;
import co.blog.entity.Post;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.cDTO.CategoryResponseDTO;
import co.blog.payloads.pDTO.PostResponseDTO;
import co.blog.repository.CategoryRepo;
import co.blog.repository.PostRepo;
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
public class GetPostByCategoryId implements BlogService {

    @Autowired
    private PostRepo pRepo;

    @Autowired
    private CategoryRepo cRepo;

    @Autowired
    private PostResponseDTO pResponseDTO;

    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_POST_BY_CATEGORY_ID;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: GetPostByCategoryId:: Inside executeService Method :===");

        Integer categoryId = (Integer) t;

        /*----Find Category with CategoryId----*/
        Category category = cRepo.findById(categoryId).orElseThrow(() -> new GeneralException(
                "Category Not Found With CategoryId = " + categoryId));

        /*----Now Find the post with category----*/
        List<Post> posts = pRepo.findByCategory(category);

        /*----Now Iterate with listOfPost and convert PostResponseDTO----*/
        List<PostResponseDTO> listOfPost =
                posts.stream().map(post -> modelMapper.map(post, PostResponseDTO.class)).collect(Collectors.toList());

        /*----Now Simply Return Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Fetch All The Posts With CategoryId = " + categoryId);
        response.setData(listOfPost);

        return response;
    }
}
