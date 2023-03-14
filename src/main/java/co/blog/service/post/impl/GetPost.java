package co.blog.service.post.impl;

import co.blog.constants.BlogAppConstants;
import co.blog.entity.Post;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.postdto.PostResponseDTO;
import co.blog.repository.PostRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetPost implements BlogService {
    @Autowired
    private PostRepo pRepo;
    @Autowired
    private Response response;
    @Autowired
    private PostResponseDTO pResponseDTO;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_POST;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: GetPost:: Inside executeService Method :===");

        Integer postId = (Integer) t;

        /*----Fetch Post With postId----*/
        Post post = pRepo.findById(postId).orElseThrow(() -> new GeneralException("Post Not Found With postId = " + postId));

        /*----Convert the post with PostResponseDTO----*/
        pResponseDTO = modelMapper.map(post, PostResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Fetch The Post With postId = " + postId);
        response.setData(pResponseDTO);

        return response;

    }
}
