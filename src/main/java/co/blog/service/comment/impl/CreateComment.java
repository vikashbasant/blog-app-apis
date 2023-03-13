package co.blog.service.comment.impl;

import co.blog.config.BlogAppConstants;
import co.blog.entity.Comment;
import co.blog.entity.Post;
import co.blog.entity.User;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.commentdto.CommentDTO;
import co.blog.payloads.commentdto.CommentResponseDTO;
import co.blog.repository.CommentRepo;
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
public class CreateComment implements BlogService {

    @Autowired
    private CommentRepo cRepo;

    @Autowired
    private CommentResponseDTO cResponseDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Response response;

    @Autowired
    private UserRepo uRepo;

    @Autowired
    private PostRepo pRepo;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.CREATE_COMMENT;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: CreateComment:: Inside executeService Method :===");

        CommentDTO cDTO = (CommentDTO) t;

        /*----Fetch userId from cDTO----*/
        Integer userId = cDTO.getUserId();
        /*----Fetch user with userId----*/
        User user = uRepo.findById(userId).orElseThrow(() -> new GeneralException("User Not " +
                "Found With UserId = " + userId));

        /*----Fetch postId from cDTO----*/
        Integer postId = cDTO.getPostId();
        /*----Fetch post with postId----*/
        Post post = pRepo.findById(postId).orElseThrow(() -> new GeneralException("Post Not " +
                "Found With PostId = " + postId));

        /*---- Convert cDTO into Comment----*/
        Comment comment = new Comment();
        comment.setCommentId(cDTO.getCommentId());
        comment.setCommentContent(cDTO.getCommentContent());
        comment.setPost(post);
        comment.setUser(user);

        /*----Save comment into DB:----*/
        Comment sComment= cRepo.save(comment);

        /*----Convert sComment into CommentResponseDTO----*/
        cResponseDTO = this.modelMapper.map(sComment, CommentResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Comment Created Successfully");
        response.setData(cResponseDTO);

        return response;
    }
}
