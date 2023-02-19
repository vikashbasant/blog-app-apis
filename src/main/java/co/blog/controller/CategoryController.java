package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.cDTO.CategoryDTO;

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
@RequestMapping(value = "/api/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private BlogServiceFactory factory;

    /**
     * For Create Category API Here @Valid Annotation used for Enable the Validation:
     * @param request CategoryDTO
     * @return CategoryResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @PostMapping("/create")
    public ResponseEntity<Response> createCategory (@RequestBody @Valid CategoryDTO request) throws GeneralException {
        log.info("===: CategoryController:: Inside createCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.CREATE_CATEGORY);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * For UpdateCategory API
     * @param request CategoryDTO
     * @return CategoryResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Response> updateCategory (@RequestBody @Valid CategoryDTO request, @PathVariable @Valid Integer categoryId) throws GeneralException {
        log.info("===: CategoryController:: Inside updateCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.UPDATE_CATEGORY);
        Response response = service.executeService(request, categoryId);
        return ResponseEntity.ok(response);
    }

    /**
     * For Get Single Category
     * @param categoryId Pass The categoryId
     * @return categoryResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @GetMapping("/get-category/{categoryId}")
    public ResponseEntity<Response> getCategory (@PathVariable @Valid Integer categoryId) throws GeneralException {
        log.info("===: CategoryController:: Inside getCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_CATEGORY);
        Response response = service.executeService(categoryId, "");
        return ResponseEntity.ok(response);
    }

    /**
     * For Get All Category
     * @return list of CategoryResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @GetMapping("/get-all-category")
    public ResponseEntity<Response> getAllUsers () throws GeneralException {
        log.info("===: CategoryController:: Inside getAllUsers Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_ALL_CATEGORY);
        Response response = service.executeService("", "");
        return ResponseEntity.ok(response);

    }


    /**
     * @purpose For Delete Category With CategoryId
     * @param cId
     * @return CategoryResponseDTO
     * @throws GeneralException If AnyThing goes wrong then gives the Exception
     */
    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<Response> deleteUser (@PathVariable("categoryId") @Valid Integer cId) throws GeneralException {
        log.info("===: CategoryController:: Inside deleteUser Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_CATEGORY);
        Response response = service.executeService(cId, "");
        return ResponseEntity.ok(response);
    }
}
