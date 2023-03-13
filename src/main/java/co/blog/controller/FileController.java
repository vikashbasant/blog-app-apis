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
import javax.validation.Valid;
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
     * This API are used to ImageUpload.
     * @param image passing image as an argument of RequestParam.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PostMapping("/upload-image")
    public ResponseEntity<Response> fileUpload(@RequestParam("image") MultipartFile image) throws GeneralException {
        log.info("===: FileController:: Inside fileUpload Method :===");
        BlogService service = factory.getService(BlogServiceType.UPLOAD_IMAGE);
        Response response = service.executeService(path, image);
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to deleteImage.
     * @param imageName passing imageName as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @DeleteMapping("/delete-image/{imageName}")
    public ResponseEntity<Response> deleteImage (@PathVariable @Valid String imageName) throws GeneralException {
        log.info("===: FileController:: Inside deleteImage Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_IMAGE);
        Response response = service.executeService(path, imageName);
        return ResponseEntity.ok(response);

    }


    /**
     * This API are used to downloadImage.
     * @param imageName passing imageName as an argument of PathVariable.
     * @param response return HttpServletResponse.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     * @throws IOException something wrong with your input or output then this IOException will generate.
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
