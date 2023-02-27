package co.blog.service.fileService.impl;

import co.blog.config.BlogAppConstants;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class DeleteImage implements BlogService {

    @Autowired
    private Response response;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.DELETE_IMAGE;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException, IOException {

        log.info("===: DeleteImage:: Inside executeService Method :===");

        String path = (String) t;
        String imageName = (String) u;


        // Full Path
        String filePath = path + File.separator + imageName;

        // Create Folder if not created
        File f = new File(path);
        if (f.exists()) {
            Files.delete(Paths.get(filePath));
        }


        // Simply return the response:
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Delete the Image = " + imageName);
        response.setData(imageName);
        return response;
    }
}
