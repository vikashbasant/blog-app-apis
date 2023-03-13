package co.blog.service.post.impl;

import co.blog.config.BlogAppConstants;
import co.blog.entity.Post;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.pdto.PostResponseDTO;
import co.blog.repository.PostRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeletePost implements BlogService {

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
        return BlogServiceType.DELETE_POST;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: DeletePost:: Inside executeService Method :===");

        Integer postId = (Integer)t;

        /*----Fetch Post With postId----*/
        Post post = pRepo.findById(postId).orElseThrow(() -> new GeneralException("Post Not Found With postId = " + postId));

        /*----Simply Delete the Post----*/
        pRepo.delete(post);

        /*----Convert post into PostResponseDTO----*/
        pResponseDTO = modelMapper.map(post, PostResponseDTO.class);

        /*----Now Simply Return Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Delete The Post With postId = " + postId);
        response.setData(pResponseDTO);

        return response;
    }
}
