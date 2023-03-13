package co.blog.service.fileService.impl;

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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class GetResource implements BlogService {

    @Autowired
    private Response response;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_RESOURCE;
    }

    @SneakyThrows
    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: GetResource:: Inside executeService Method :===");

        String path = (String) t;
        String fileName = (String) u;

        /*----Generating the fullPath:----*/
        String fullPath = path + File.separator + fileName;

        /*----db logic to return inputStream----*/
        InputStream is = Files.newInputStream(Paths.get(fullPath));


        /*----Simply return the response:----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Get the Resource = " + is);
        response.setData(is);

        return response;
    }
}
