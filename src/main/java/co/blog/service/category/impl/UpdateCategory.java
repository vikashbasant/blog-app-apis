package co.blog.service.category.impl;

import co.blog.entity.Category;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.cDTO.CategoryDTO;
import co.blog.payloads.cDTO.CategoryResponseDTO;
import co.blog.repository.CategoryRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UpdateCategory implements BlogService {

    @Autowired
    private CategoryRepo cRepo;

    @Autowired
    private Response response;

    @Autowired
    private CategoryResponseDTO cResponseDTO;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.UPDATE_CATEGORY;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: UpdateCategory:: Inside ExecuteService Method :===");

        CategoryDTO cDTO = (CategoryDTO) t;
        Integer categoryId = (Integer) u;

        /*----First Find The Category With CategoryId----*/
        cRepo.findById(categoryId).orElseThrow(() -> new GeneralException(
                "Category Not Found With CategoryId = " + cDTO.getCategoryId()));

        /*----Then Simply Update The User----*/
        cRepo.updateId(categoryId, cDTO.getCategoryDescription(), cDTO.getCategoryTitle());

        Optional<Category> updatedCategory = cRepo.findById(categoryId);

        /*----Convert cDTO to CategoryResponseDTO----*/
        cResponseDTO = this.modelMapper.map(updatedCategory, CategoryResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Update The Category With CategoryId = " + cDTO.getCategoryId());
        response.setData(cResponseDTO);


        return response;
    }
}
