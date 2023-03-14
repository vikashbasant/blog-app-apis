package co.blog.controller;

import co.blog.constants.BlogAppConstants;
import co.blog.exception.GeneralException;
import co.blog.payloads.PaginationDTO;
import co.blog.payloads.Response;
import co.blog.payloads.postdto.PostDTO;
import co.blog.payloads.postdto.PostResponseDTO;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceFactory;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
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
     * This API are used to createPost.
     * @param postDTO passing postDTO as an argument of RequestBody.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PostMapping("/create")
    public ResponseEntity<Response> createPost(@RequestBody @Valid PostDTO postDTO) throws GeneralException {
        log.info("===: PostController:: Inside CreatePost Method :===");
        BlogService service = factory.getService(BlogServiceType.CREATE_POST);
        Response response = service.executeService(postDTO, "");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * This API are used to getPost.
     * @param postId passing postId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @GetMapping("/get-post/{postId}")
    public ResponseEntity<Response> getPost(@PathVariable @Valid Integer postId) throws GeneralException {
        log.info("===: PostController:: Inside GetPost Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_POST);
        Response response = service.executeService(postId, "");
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to getAllPosts.
     * @param pageNumber passing pageNumber as an argument of RequestParam.
     * @param pageSize passing pageSize as an argument of RequestParam.
     * @param sortBy passing sortBy as an argument of RequestParam.
     * @param sortDir passing sortDir as an argument of RequestParam.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
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
     * This API are used to getPostsByCategory.
     * @param pageNumber passing pageNumber as an argument of RequestParam.
     * @param pageSize passing pageSize as an argument of RequestParam.
     * @param sortBy passing sortBy as an argument of RequestParam.
     * @param sortDir passing sortDir as an argument of RequestParam.
     * @param categoryId passing categoryId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
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
     * This API are used to getPostsByUser.
     * @param pageNumber passing pageNumber as an argument of RequestParam.
     * @param pageSize passing pageSize as an argument of RequestParam.
     * @param sortBy passing sortBy as an argument of RequestParam.
     * @param sortDir passing sortDir as an argument of RequestParam.
     * @param userId passing userId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
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
     * This API are used to updatePost.
     * @param postDTO passing postDTO as an argument of RequestBody.
     * @param postId passing postId as an argument of RequestBody.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
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
     * This API are used to deletePost.
     * @param postId passing postId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<Response> deletePost (@PathVariable @Valid Integer postId) throws GeneralException {
        log.info("===: PostController:: Inside DeletePost Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_POST);
        Response response = service.executeService(postId, "");
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to searchPost.
     * @param pageNumber passing pageNumber as an argument of RequestParam.
     * @param pageSize passing pageSize as an argument of RequestParam.
     * @param sortBy passing sortBy as an argument of RequestParam.
     * @param sortDir passing sortDir as an argument of RequestParam.
     * @param keyword passing keyword as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @GetMapping("/search-by-title/{keyword}")
    public ResponseEntity<Response> searchPost(
            @RequestParam(value = "pageNumber", defaultValue = BlogAppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = BlogAppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = BlogAppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = BlogAppConstants.SORT_DIR, required = false) String sortDir,
            @PathVariable @Valid String keyword) throws GeneralException {

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
     * This API are used to uploadPostImage.
     * @param image passing image as an argument of RequestParam.
     * @param postId passing postId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<Response> uploadPostImage (
            @RequestParam("image") MultipartFile image,
            @PathVariable @Valid Integer postId) throws GeneralException {

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
     * This API are used to deletePostImage.
     * @param postId passing postId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @DeleteMapping("/image/delete/{postId}")
    public ResponseEntity<Response> deletePostImage (
            @PathVariable @Valid Integer postId) throws GeneralException {

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
     * This API are used to updatePostImage.
     * @param image passing image as an argument of RequestParam.
     * @param postId passing postId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PutMapping("/image/update/{postId}")
    public ResponseEntity<Response> updatePostImage (
            @RequestParam("image") MultipartFile image,
            @PathVariable @Valid Integer postId) throws GeneralException {

        log.info("===: PostController:: Inside updatePostImage Method :===");

        // First Delete The Post Image With postId:
        deletePostImage(postId);

        // Now Simply Upload The Post Image:
        return uploadPostImage(image, postId);
    }

    /**
     * This API are used to downloadImage.
     * @param postId passing postId as an argument of PathVariable.
     * @param response passing response as an argument of HttpServletResponse.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     * @throws IOException something wrong with your input or output then this IOException will generate.
     */
    @GetMapping(value = "/profile/{postId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("postId") Integer postId, HttpServletResponse response) throws GeneralException, IOException {


        // Fetch Post With PostId:
        PostResponseDTO getPostWithPostId =
                (PostResponseDTO) Objects.requireNonNull(getPost(postId).getBody()).getData();

        String imageName = getPostWithPostId.getImageName();

        log.info("===: PostController:: Inside downloadImage Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_RESOURCE);
        Response resource = service.executeService(path, imageName);

        // Fetch the InputStream from the resource:
        InputStream iStream = (InputStream) resource.getData();

        // set the ContentType to HttpServletResponse
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(iStream, response.getOutputStream());

    }
}
