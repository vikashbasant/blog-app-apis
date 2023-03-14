package co.blog.service.post.impl;

import co.blog.constants.BlogAppConstants;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class UploadPostImage implements BlogService {

    @Autowired
    private Response response;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.UPLOAD_IMAGE;
    }

    @SneakyThrows
    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: UploadImage:: Inside executeService Method :===");

        String path = (String) t;
        MultipartFile file = (MultipartFile) u;

        /*----File Name:----*/
        String fName = file.getOriginalFilename(); // abc.png

        /*----Random Name Generate Of File:----*/
        String randomId = UUID.randomUUID().toString();
        assert fName != null;
        String fileName = randomId.concat(fName.substring(fName.lastIndexOf(".")));

        /*----Full Path----*/
        String filePath = path + File.separator + fileName;

        /*----Create Folder if not created----*/
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        /*----File Copy----*/
        Files.copy(file.getInputStream(), Paths.get(filePath));

        /*----Simply return the response:----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Upload the Image = " + fileName);
        response.setData(fileName);

        return response;
    }
}
