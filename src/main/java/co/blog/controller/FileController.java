package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceFactory;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private BlogServiceFactory factory;

    @Value("${project.image}")
    private String path;


    /**
     * This API are used to Upload the Image file
     * @param image passing an image as an argument of Multipart
     * @return Response Object
     * @throws GeneralException If anything goes wrong then this exception will generate.
     * @throws IOException In any situation where input/output operations are involved, and there is an error or
     *      * failure in the operation.
     */
    @PostMapping("/upload-image")
    public ResponseEntity<Response> fileUpload(@RequestParam("image") MultipartFile image) throws GeneralException, IOException {
        log.info("===: FileController:: Inside fileUpload Method :===");
        BlogService service = factory.getService(BlogServiceType.UPLOAD_IMAGE);
        Response response = service.executeService(path, image);
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to Serve the image file
     * @param imageName passing an argument as a PathVariable
     * @param response return response as HttpServletResponse
     * @throws GeneralException If anything goes wrong then this exception will generate.
     * @throws IOException  In any situation where input/output operations are involved, and there is an error or
     * failure in the operation.
     */
    @GetMapping(value = "/profile/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws GeneralException, IOException {
        log.info("===: FileController:: Inside downloadImage Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_RESOURCE);
        Response resource = service.executeService(path, imageName);

        // Fetch the InputStream from the resource:
        InputStream iStream = (InputStream) resource.getData();

        // set the ContentType to HttpServletResponse
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(iStream, response.getOutputStream());
    }
}
