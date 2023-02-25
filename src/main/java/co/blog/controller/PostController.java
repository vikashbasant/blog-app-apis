package co.blog.controller;

import co.blog.config.BlogAppConstants;
import co.blog.exception.GeneralException;
import co.blog.payloads.PaginationDTO;
import co.blog.payloads.Response;
import co.blog.payloads.pDTO.PostDTO;
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
@RequestMapping(value = "/posts")
@Slf4j
public class PostController {

    @Autowired
    private BlogServiceFactory factory;

    @Autowired
    private PaginationDTO paginationDTO;


    /**
     * Purpose of this api is CreatePost:
     * @param postDTO Passing the postDTO object as argument
     * @return PostResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @PostMapping("/create")
    public ResponseEntity<Response> createPost(@RequestBody @Valid PostDTO postDTO) throws GeneralException {
        log.info("===: PostController:: Inside CreatePost Method :===");
        BlogService service = factory.getService(BlogServiceType.CREATE_POST);
        Response response = service.executeService(postDTO, "");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * Purpose of this api is GetPost
     * @param postId passing as argument
     * @return PostResponseDTO
     * @throws GeneralException If AnyThing goes wrong then give the Exception
     */
    @GetMapping("/get-post/{postId}")
    public ResponseEntity<Response> getPost(@PathVariable @Valid Integer postId) throws GeneralException {
        log.info("===: PostController:: Inside GetPost Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_POST);
        Response response = service.executeService(postId, "");
        return ResponseEntity.ok(response);
    }


    /**
     * Purpose of this api is GetAllPost
     * @return list of PostResponseDTO
     * @throws GeneralException If AnyThing goes wrong then give the Exception
     */
    @GetMapping("/get-all-post")
    public ResponseEntity<Response> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = BlogAppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = BlogAppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = BlogAppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = BlogAppConstants.SORT_DIR, required = false) String sortDir) throws GeneralException {

        log.info("===: PostController:: Inside GetAllPosts Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_ALL_POST);

        /*----Set the pageNumber and pageSize into paginationDTO----*/
        paginationDTO.setPageNumber(pageNumber);
        paginationDTO.setPageSize(pageSize);
        paginationDTO.setSortBy(sortBy);
        paginationDTO.setSortDir(sortDir);

        Response response = service.executeService(paginationDTO, "");
        return ResponseEntity.ok( response);
    }


    /**
     * Purpose of this api is GetPostsByCategoryId
     * @param categoryId passing as argument
     * @return PostResponseDTO
     * @throws GeneralException If AnyThing goes wrong then give this Exception
     */
    @GetMapping("/get-posts-by-category/{categoryId}")
    public ResponseEntity<Response> getPostsByCategory(
            @RequestParam(value = "pageNumber", defaultValue = BlogAppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = BlogAppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = BlogAppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = BlogAppConstants.SORT_DIR, required = false) String sortDir,
            @PathVariable @Valid Integer categoryId) throws GeneralException {

        log.info("===: PostController:: Inside GetPostsByCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_POST_BY_CATEGORY_ID);

        /*----Set the pageNumber and pageSize into paginationDTO----*/
        paginationDTO.setPageNumber(pageNumber);
        paginationDTO.setPageSize(pageSize);
        paginationDTO.setSortBy(sortBy);
        paginationDTO.setSortDir(sortDir);

        Response response = service.executeService(categoryId, paginationDTO);
        return ResponseEntity.ok(response);
    }


    /**
     * Purpose of this api is GetPostsByUserId
     * @param userId passing as argument
     * @return PostResponseDTO
     * @throws GeneralException If AnyThing goes wrong then give this Exception
     */
    @GetMapping("/get-posts-by-user/{userId}")
    public ResponseEntity<Response> getPostsByUser(
            @RequestParam(value = "pageNumber", defaultValue = BlogAppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = BlogAppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = BlogAppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = BlogAppConstants.SORT_DIR, required = false) String sortDir,
            @PathVariable @Valid Integer userId) throws GeneralException {

        log.info("===: PostController:: Inside GetPostsByUser Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_POST_BY_USER_ID);

        /*----Set the pageNumber and pageSize into paginationDTO----*/
        paginationDTO.setPageNumber(pageNumber);
        paginationDTO.setPageSize(pageSize);
        paginationDTO.setSortBy(sortBy);
        paginationDTO.setSortDir(sortDir);


        Response response = service.executeService(userId, paginationDTO);
        return ResponseEntity.ok(response);
    }


    /**
     * Purpose of this api is UpdatePost
     * @param postDTO passing as argument
     * @param postId passing as argument
     * @return PostResponseDTO
     * @throws GeneralException If AnyThing goes wrong then give this Exception
     */
    @PutMapping("/update-post/{postId}")
    public ResponseEntity<Response> updatePost (
            @RequestBody @Valid PostDTO postDTO,
            @PathVariable @Valid Integer postId) throws GeneralException {
        log.info("===: PostController:: Inside UpdatePost Method :===");
        BlogService service = factory.getService(BlogServiceType.UPDATE_POST);
        Response response = service.executeService(postDTO, postId);
        return ResponseEntity.ok(response);
    }

    /**
     * Purpose of this api DeletePost
     * @param postId passing as argument
     * @return PostResponseDTO
     * @throws GeneralException If AnyThing goes wrong then give this Exception
     */
    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<Response> deletePost (@PathVariable @Valid Integer postId) throws GeneralException {
        log.info("===: PostController:: Inside DeletePost Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_POST);
        Response response = service.executeService(postId, "");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/search-by-title/{keyword}")
    public ResponseEntity<Response> searchPost(
            @RequestParam(value = "pageNumber", defaultValue = BlogAppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = BlogAppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = BlogAppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = BlogAppConstants.SORT_DIR, required = false) String sortDir,
            @PathVariable @Valid String keyword) throws GeneralException{

        log.info("===: PostController:: Inside SearchPost Method :===");
        BlogService service = factory.getService(BlogServiceType.SEARCH_POST);

        /*----Set the pageNumber and pageSize into paginationDTO----*/
        paginationDTO.setPageNumber(pageNumber);
        paginationDTO.setPageSize(pageSize);
        paginationDTO.setSortBy(sortBy);
        paginationDTO.setSortDir(sortDir);


        Response response = service.executeService(keyword, paginationDTO);
        return ResponseEntity.ok(response);
    }
}
