package co.blog.service.category.impl;

import co.blog.entity.Category;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.cDTO.CategoryResponseDTO;
import co.blog.payloads.uDTO.UserResponseDTO;
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
public class DeleteCategory implements CategoryService {

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
        return CategoryServiceType.DELETE_CATEGORY;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: DeleteCategory:: Inside ExecuteService Method :===");

        Integer  categoryId = (Integer) t;

        /*----Before delete the Category, Fetch the Category:----*/
        Optional<Category> byCategoryId =
                Optional.ofNullable(cRepo.findById(categoryId).orElseThrow(() -> new GeneralException("Category Not " +
                "Found With CategoryId = " + categoryId)));

        /*----Now Simply Delete the Category with CategoryId:----*/
        cRepo.deleteById(categoryId);

        /*----Now Convert the Category to CategoryResponseDTO:----*/
        cResponseDTO = this.modelMapper.map(byCategoryId.get(), CategoryResponseDTO.class);

        /*----Now Simply Return Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Delete The Category With CategoryId = " + categoryId);
        response.setData(cResponseDTO);

        return response;
    }
}
