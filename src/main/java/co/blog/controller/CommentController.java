package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.commentDTO.CommentDTO;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceFactory;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/comments")
@Slf4j
public class CommentController {

    @Autowired
    private BlogServiceFactory factory;

    /**
     * This API are used to CreateComment
     * @param request passing request as an RequestBody argument.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this exception will generate.
     * @throws IOException In any situation where input/output operations are involved, and there is an error or failure in the operation.
     */
    @PostMapping("/create")
    public ResponseEntity<Response> createComment (@RequestBody @Valid CommentDTO request) throws GeneralException,
            IOException {
        log.info("===: CommentController:: Inside createComment Method :===");
        BlogService service = factory.getService(BlogServiceType.CREATE_COMMENT);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * This API are used to DeleteComment
     * @param commentId passing commentId as an PathVariable argument.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this exception will generate.
     * @throws IOException In any situation where input/output operations are involved, and there is an error or failure in the operation.
     */

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<Response> deleteComment (@PathVariable @Valid Integer commentId) throws GeneralException,
            IOException {
        log.info("===: CommentController:: Inside deleteComment Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_COMMENT);
        Response response = service.executeService(commentId, "");
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to UpdateComment
     * @param request passing request as an RequestBody argument.
     * @param commentId passing commentId as an PathVariable argument.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this exception will generate.
     * @throws IOException In any situation where input/output operations are involved, and there is an error or failure in the operation.
     */
    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<Response> updateComment (@RequestBody @Valid CommentDTO request, @PathVariable @Valid Integer commentId) throws GeneralException,
            IOException {
        log.info("===: CommentController:: Inside updateComment Method :===");
        BlogService service = factory.getService(BlogServiceType.UPDATE_COMMENT);
        Response response = service.executeService(request, commentId);
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to GetComment
     * @param commentId passing commentId as an PathVariable argument.
     * @return return response
     * @throws GeneralException If anything goes wrong then this exception will generate.
     * @throws IOException In any situation where input/output operations are involved, and there is an error or failure in the operation.
     */
    @GetMapping("/get-comment/{commentId}")
    public ResponseEntity<Response> getComment (@PathVariable @Valid Integer commentId) throws GeneralException,
            IOException {
        log.info("===: CommentController:: Inside getComment Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_COMMENT);
        Response response = service.executeService(commentId, "");
        return ResponseEntity.ok(response);
    }
}
