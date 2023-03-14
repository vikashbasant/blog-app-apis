package co.blog.service.fileservice.impl;

import co.blog.config.BlogAppConstants;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class DeleteImage implements BlogService {

    @Autowired
    private Response response;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.DELETE_IMAGE;
    }

    @SneakyThrows
    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: DeleteImage:: Inside executeService Method :===");

        String path = (String) t;
        String imageName = (String) u;


        /*----Full Path----*/
        String filePath = path + File.separator + imageName;

        /*----Create Folder if not created----*/
        File f = new File(path);
        if (f.exists()) {
            /*----If filePath is present then simply delete it.----*/
            Files.delete(Paths.get(filePath));
        }


        /*----Simply return the response:----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Delete the Image = " + imageName);
        response.setData(imageName);

        return response;
    }
}
