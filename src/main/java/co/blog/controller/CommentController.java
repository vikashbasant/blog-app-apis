package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.commentdto.CommentDTO;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceFactory;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/comments")
@Slf4j
public class CommentController {

    @Autowired
    private BlogServiceFactory factory;


    /**
     * This API are used to createComment.
     * @param request passing CommentDTO as an argument of RequestBody.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PostMapping("/create")
    public ResponseEntity<Response> createComment (@RequestBody @Valid CommentDTO request) throws GeneralException {
        log.info("===: CommentController:: Inside createComment Method :===");
        BlogService service = factory.getService(BlogServiceType.CREATE_COMMENT);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * This API are used to deleteComment.
     * @param commentId passing commentId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<Response> deleteComment (@PathVariable @Valid Integer commentId) throws GeneralException {
        log.info("===: CommentController:: Inside deleteComment Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_COMMENT);
        Response response = service.executeService(commentId, "");
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to updateComment.
     * @param request passing CommentDTO as an argument of RequestBody.
     * @param commentId passing commentId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<Response> updateComment (
            @RequestBody @Valid CommentDTO request,
            @PathVariable @Valid Integer commentId) throws GeneralException {
        log.info("===: CommentController:: Inside updateComment Method :===");
        BlogService service = factory.getService(BlogServiceType.UPDATE_COMMENT);
        Response response = service.executeService(request, commentId);
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to getComment.
     * @param commentId passing commentId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @GetMapping("/get-comment/{commentId}")
    public ResponseEntity<Response> getComment (@PathVariable @Valid Integer commentId) throws GeneralException {
        log.info("===: CommentController:: Inside getComment Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_COMMENT);
        Response response = service.executeService(commentId, "");
        return ResponseEntity.ok(response);
    }

    /**
     * This API are used to getAllComment.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @GetMapping("/get-all-comment")
    public ResponseEntity<Response> getAllComment() throws GeneralException {
        log.info("===: CommentController:: Inside getComment Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_ALL_COMMENT);
        Response response = service.executeService("", "");
        return ResponseEntity.ok(response);
    }
}
