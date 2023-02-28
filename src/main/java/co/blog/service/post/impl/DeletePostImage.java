package co.blog.service.post.impl;

import co.blog.config.BlogAppConstants;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class DeletePostImage implements BlogService {

    @Autowired
    private Response response;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.DELETE_IMAGE;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException, IOException {

        log.info("===: DeletePostImage:: Inside executeService Method :===");

        String path = (String) u;
        String imageName = (String) t;


        // Full Path
        String filePath = path + File.separator + imageName;

        // Delete File From Local:
        File f = new File(filePath);
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
