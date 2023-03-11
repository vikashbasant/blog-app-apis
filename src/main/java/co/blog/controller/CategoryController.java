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
import java.io.IOException;

@RestController
@RequestMapping(value = "/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private BlogServiceFactory factory;


    @PostMapping("/create")
    public ResponseEntity<Response> createCategory (@RequestBody @Valid CategoryDTO request) throws GeneralException, IOException {
        log.info("===: CategoryController:: Inside createCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.CREATE_CATEGORY);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Response> updateCategory (@RequestBody @Valid CategoryDTO request, @PathVariable @Valid Integer categoryId) throws GeneralException, IOException {
        log.info("===: CategoryController:: Inside updateCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.UPDATE_CATEGORY);
        Response response = service.executeService(request, categoryId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get-category/{categoryId}")
    public ResponseEntity<Response> getCategory (@PathVariable @Valid Integer categoryId) throws GeneralException, IOException {
        log.info("===: CategoryController:: Inside getCategory Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_CATEGORY);
        Response response = service.executeService(categoryId, "");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-category")
    public ResponseEntity<Response> getAllUsers () throws GeneralException, IOException {
        log.info("===: CategoryController:: Inside getAllUsers Method :===");
        BlogService service = factory.getService(BlogServiceType.GET_ALL_CATEGORY);
        Response response = service.executeService("", "");
        return ResponseEntity.ok(response);

    }



    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<Response> deleteUser (@PathVariable("categoryId") @Valid Integer cId) throws GeneralException, IOException {
        log.info("===: CategoryController:: Inside deleteUser Method :===");
        BlogService service = factory.getService(BlogServiceType.DELETE_CATEGORY);
        Response response = service.executeService(cId, "");
        return ResponseEntity.ok(response);
    }
}
