package co.blog.service.category.impl;

import co.blog.entity.Category;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.cDTO.CategoryDTO;
import co.blog.payloads.cDTO.CategoryResponseDTO;
import co.blog.repository.CategoryRepo;
import co.blog.util.categoryUtil.CategoryService;
import co.blog.util.categoryUtil.CategoryServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CreateCategory implements CategoryService {

    @Autowired
    private CategoryRepo cRepo;

    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryResponseDTO cResponseDTO;

    @Override
    public CategoryServiceType getServiceType () {
        return CategoryServiceType.CREATE_CATEGORY;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: CreateUser:: Inside ExecuteService Method :===");

        CategoryDTO cDTO = (CategoryDTO) t;

        /*----Convert CategoryDTO into Category Object----*/
        Category category = modelMapper.map(cDTO, Category.class);

        /*----Now Save the Category Object into DB----*/
        Category sCategory = cRepo.save(category);

        /*----Convert Category into CategoryResponseDTO Object----*/
        cResponseDTO = modelMapper.map(sCategory, CategoryResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("User Created Successfully");
        response.setData(cResponseDTO);

        return response;
    }
}
