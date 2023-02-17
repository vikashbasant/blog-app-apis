package co.blog.service.category.impl;


import co.blog.entity.Category;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.cDTO.CategoryResponseDTO;
import co.blog.repository.CategoryRepo;
import co.blog.util.categoryUtil.CategoryService;
import co.blog.util.categoryUtil.CategoryServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetCategory implements CategoryService {

    @Autowired
    private CategoryRepo cRepo;

    @Autowired
    private Response response;

    @Autowired
    private CategoryResponseDTO cResponseDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryServiceType getServiceType () {
        return CategoryServiceType.GET_CATEGORY;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("====: GetCategory:: Inside executeService Method :====");

        Integer categoryId = (Integer) t;

        /*----Fetch Category With CategoryId----*/
        Optional<Category> category = Optional.ofNullable(cRepo.findById(categoryId).orElseThrow(() -> new GeneralException("Category Not " +
                "Found With This CategoryId = " + categoryId)));

        /*----Convert Category into CategoryResponseDTO:----*/
        cResponseDTO = this.modelMapper.map(category.get(), CategoryResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Fetch The Category With CategoryId = " + category.get().getCategoryId());
        response.setData(cResponseDTO);


        return response;



    }
}
