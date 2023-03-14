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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetAllComment implements BlogService {

    @Autowired
    private CommentRepo cRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Response response;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_ALL_COMMENT;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {
        log.info("===: GetAllComment:: Inside executeService Method :===");

        /*----Find the All Comments----*/
        List<Comment> allComments = cRepo.findAll();

        /*----Iterate Over List of Comment, converted into CommentResponseDTO----*/
        List<CommentResponseDTO> listOfComment = allComments.stream().map(comment -> this.modelMapper.map(comment, CommentResponseDTO.class)).collect(Collectors.toList());

        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Fetch All The Comment");
        response.setData(listOfComment);

        return response;

    }
}
