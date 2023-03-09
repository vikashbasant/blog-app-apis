package co.blog.service.comment.impl;

import co.blog.config.BlogAppConstants;
import co.blog.entity.Comment;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.commentDTO.CommentResponseDTO;
import co.blog.repository.CommentRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class GetComment implements BlogService {

    @Autowired
    private CommentRepo cRepo;

    @Autowired
    private CommentResponseDTO cResponseDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Response response;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_COMMENT;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException, IOException {

        log.info("===: GetComment:: Inside executeService Method :===");

        Integer commentId = (Integer) t;

        /*----Fetch Comment with commentId----*/
        Comment comment = cRepo.findById(commentId).orElseThrow(() -> new GeneralException("Comment Not " +
                "Found With commentId = " + commentId));


        /*----Convert Comment into CommentResponseDTO----*/
        cResponseDTO = this.modelMapper.map(comment, CommentResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Fetch The Comment With commentId = "  + commentId);
        response.setData(cResponseDTO);

        return response;
    }
}
