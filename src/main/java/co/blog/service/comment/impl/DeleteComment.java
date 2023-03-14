package co.blog.service.comment.impl;

import co.blog.constants.BlogAppConstants;
import co.blog.entity.Comment;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.commentdto.CommentResponseDTO;
import co.blog.repository.CommentRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteComment implements BlogService {

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
        return BlogServiceType.DELETE_COMMENT;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {
        log.info("===: DeleteComment:: Inside executeService Method :===");

        Integer commentId = (Integer) t;

        /*----Before delete the Comment, Fetch the Comment:----*/

        Comment byCommentId =
                cRepo.findById(commentId).orElseThrow(() -> new GeneralException("Comment Not " +
                "Found With commentId = " + commentId));

        /*----Now Simply Delete the Category with CategoryId:----*/
        cRepo.deleteById(commentId);

        /*----Now Convert the Category to CategoryResponseDTO:----*/
        cResponseDTO = this.modelMapper.map(byCommentId, CommentResponseDTO.class);

        /*----Now Simply Return Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Delete The Comment With commentId = " + commentId);
        response.setData(cResponseDTO);

        return response;
    }
}
