package co.blog.util;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;

import java.io.IOException;

public interface BlogService {

    BlogServiceType getServiceType ();

    <T, U> Response executeService (T t, U u) throws GeneralException, IOException;

}
