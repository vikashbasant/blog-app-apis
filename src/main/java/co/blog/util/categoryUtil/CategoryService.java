package co.blog.util.categoryUtil;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;



public interface CategoryService {
    CategoryServiceType getServiceType ();

    <T> Response executeService (T t) throws GeneralException;
}
