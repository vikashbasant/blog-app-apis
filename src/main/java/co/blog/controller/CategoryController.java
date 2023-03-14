package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.categorydto.CategoryDTO;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceFactory;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private BlogServiceFactory factory;


    /**
     * This API are used to Create Category.
     * @param request Passing CategoryDTO as an argument of RequestBody.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PostMapping("/create")
    public ResponseEntity<Response> createCategory (@RequestBody @Valid CategoryDTO request) throws GeneralException {
        log.info("===: CategoryController:: Inside createCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.CREATE_CATEGORY);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * This API are used to Update Category.
     * @param request passing CategoryDTO as an argument of RequestBody.
     * @param categoryId passing categoryId as an argument of PathVariable.
     * @return  return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Response> updateCategory (@RequestBody @Valid CategoryDTO request, @PathVariable @Valid Integer categoryId) throws GeneralException {
        log.info("===: CategoryController:: Inside updateCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.UPDATE_CATEGORY);
        Response response = service.executeService(request, categoryId);
        return ResponseEntity.ok(response);
    }


    /**
     * This API are used to get Category by CategoryId:
     * @param categoryId passing categoryId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @GetMapping("/get-category/{categoryId}")
    public ResponseEntity<Response> getCategory (@PathVariable @Valid Integer categoryId) throws GeneralException {
        log.info("===: CategoryController:: Inside getCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_CATEGORY);
        Response response = service.executeService(categoryId, "");
        return ResponseEntity.ok(response);
    }

    /**
     * This API are used to get all the Category.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @GetMapping("/get-all-category")
    public ResponseEntity<Response> getAllCategory () throws GeneralException{
        log.info("===: CategoryController:: Inside getAllCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_ALL_CATEGORY);
        Response response = service.executeService("", "");
        return ResponseEntity.ok(response);

    }


    /**
     * This API are used to delete Category with categoryId:
     * @param cId Passing categoryId as an argument of PathVariable.
     * @return return response.
     * @throws GeneralException If anything goes wrong then this GeneralException will generate.
     */
    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<Response> deleteCategory (@PathVariable("categoryId") @Valid Integer cId) throws GeneralException {
        log.info("===: CategoryController:: Inside deleteCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_CATEGORY);
        Response response = service.executeService(cId, "");
        return ResponseEntity.ok(response);
    }
}
