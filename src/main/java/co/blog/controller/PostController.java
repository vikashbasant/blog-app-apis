package co.blog.controller;

import co.blog.config.BlogAppConstants;
import co.blog.exception.GeneralException;
import co.blog.payloads.PaginationDTO;
import co.blog.payloads.Response;
import co.blog.payloads.pDTO.PostDTO;
import co.blog.payloads.pDTO.PostResponseDTO;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceFactory;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;


@RestController
@RequestMapping(value = "/posts")
@Slf4j
public class PostController {

    @Value("${project.image}")
    private String path;

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
    public ResponseEntity<Response> createPost(@RequestBody @Valid PostDTO postDTO) throws GeneralException, IOException {
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
    public ResponseEntity<Response> getPost(@PathVariable @Valid Integer postId) throws GeneralException, IOException {
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
            @RequestParam(value = "sortDir", defaultValue = BlogAppConstants.SORT_DIR, required = false) String sortDir) throws GeneralException, IOException {

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
            @PathVariable @Valid Integer categoryId) throws GeneralException, IOException {

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
            @PathVariable @Valid Integer userId) throws GeneralException, IOException {

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
            @PathVariable @Valid Integer postId) throws GeneralException, IOException {
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
    public ResponseEntity<Response> deletePost (@PathVariable @Valid Integer postId) throws GeneralException, IOException {
        log.info("===: PostController:: Inside DeletePost Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_POST);
        Response response = service.executeService(postId, "");
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to searchPost
     * @param pageNumber passing pageNumber as an RequestParam
     * @param pageSize passing pageSize as an RequestParam
     * @param sortBy passing sortBy as an RequestParam
     * @param sortDir passing sortDir as an RequestParam
     * @param keyword passing keyword as an PathVariable
     * @return return postResponse
     * @throws GeneralException If AnyThing goes wrong then give this Exception
     * @throws IOException In any situation where input/output operations are involved, and there is an error or * failure in the operation.
     */
    @GetMapping("/search-by-title/{keyword}")
    public ResponseEntity<Response> searchPost(
            @RequestParam(value = "pageNumber", defaultValue = BlogAppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = BlogAppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = BlogAppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = BlogAppConstants.SORT_DIR, required = false) String sortDir,
            @PathVariable @Valid String keyword) throws GeneralException, IOException {

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


    /**
     * This API are used to uploadPostImage
     * @param image passing image as an RequestParam argument.
     * @param postId passing postId as an PathVariable argument.
     * @return return response
     * @throws GeneralException If AnyThing goes wrong then give this Exception.
     * @throws IOException In any situation where input/output operations are involved, and there is an error or * failure in the operation.
     */
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<Response> uploadPostImage (
            @RequestParam("image") MultipartFile image,
            @PathVariable @Valid Integer postId) throws GeneralException, IOException {

        log.info("===: PostController:: Inside uploadPostImage Method :===");

        // Fetch Post With PostId:
        PostResponseDTO getPostWithPostId =
                (PostResponseDTO) Objects.requireNonNull(getPost(postId).getBody()).getData();

        BlogService service = factory.getService(BlogServiceType.UPLOAD_IMAGE);
        Response response = service.executeService(path, image);

        // Fetch The Image File Name From Response:
        String imageFileName = (String) response.getData();

        // Now Fetch Data from getPostWithPostId and set into PostDTO:
        PostDTO pDTO = new PostDTO();
        pDTO.setPostId(getPostWithPostId.getPostId());
        pDTO.setPostTitle(getPostWithPostId.getPostTitle());
        pDTO.setPostContent(getPostWithPostId.getPostContent());
        pDTO.setPostImageName(imageFileName);
        pDTO.setCategoryId(getPostWithPostId.getCategory().getCategoryId());
        pDTO.setUserId(getPostWithPostId.getUser().getUserId());

        // Now Simply update the post:
        ResponseEntity<Response> updatedPost = updatePost(pDTO, postId);

        return updatedPost;

    }


    /**
     * This API are used to deletePostImage
     * @param postId passing postId as an argument within PathVariable
     * @return return PostResponseDTO
     * @throws GeneralException If anything goes wrong then this exception will generate.
     * @throws IOException In any situation where input/output operations are involved, and there is an error or * failure in the operation.
     */
    @DeleteMapping("/image/delete/{postId}")
    public ResponseEntity<Response> deletePostImage (
            @PathVariable @Valid Integer postId) throws GeneralException, IOException {

        log.info("===: PostController:: Inside deletePostImage Method :===");

        // Fetch Post With PostId:
        PostResponseDTO getPostWithPostId =
                (PostResponseDTO) Objects.requireNonNull(getPost(postId).getBody()).getData();

        // Fetch The ImageName from postId:
        String imageName = getPostWithPostId.getImageName();

        BlogService service = factory.getService(BlogServiceType.DELETE_IMAGE);
        Response response = service.executeService(imageName, path);


        // Now Fetch Data from getPostWithPostId and set into PostDTO:
        PostDTO pDTO = new PostDTO();
        pDTO.setPostId(getPostWithPostId.getPostId());
        pDTO.setPostTitle(getPostWithPostId.getPostTitle());
        pDTO.setPostContent(getPostWithPostId.getPostContent());
        pDTO.setPostImageName(BlogAppConstants.DEFAULT_IMAGE_NAME);
        pDTO.setCategoryId(getPostWithPostId.getCategory().getCategoryId());
        pDTO.setUserId(getPostWithPostId.getUser().getUserId());

        // Now Simply update the post:
        return updatePost(pDTO, postId);

    }

    /**
     * This API are used to updatePostImage
     * @param image passing image as an Multipart argument.
     * @param postId passing postId as an PathVariable argument.
     * @return return PostResponseDTO
     * @throws GeneralException If anything goes wrong then this exception will generate.
     * @throws IOException In any situation where input/output operations are involved, and there is an error or * failure in the operation.
     */


    @PutMapping("/image/update/{postId}")
    public ResponseEntity<Response> updatePostImage (
            @RequestParam("image") MultipartFile image,
            @PathVariable @Valid Integer postId) throws GeneralException, IOException {

        log.info("===: PostController:: Inside updatePostImage Method :===");

        // First Delete The Post Image With postId:
        deletePostImage(postId);

        // Now Simply Upload The Post Image:
        return uploadPostImage(image, postId);
    }
}
