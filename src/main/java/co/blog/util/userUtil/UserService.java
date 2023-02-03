package co.blog.util.userUtil;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;

public interface UserService {

    UserServiceType getServiceType ();

    <T> Response executeService (T t) throws GeneralException;

}