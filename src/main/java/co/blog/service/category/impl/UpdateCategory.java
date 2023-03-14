package co.blog.service.category.impl;

import co.blog.config.BlogAppConstants;
import co.blog.entity.Category;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.categorydto.CategoryDTO;
import co.blog.payloads.categorydto.CategoryResponseDTO;
import co.blog.repository.CategoryRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Category category = cRepo.findById(categoryId).orElseThrow(() -> new GeneralException(
                "Category Not Found With CategoryId = " + cDTO.getCategoryId()));

        /*----Then Simply Update The category----*/
        category.setCategoryTitle(cDTO.getCategoryTitle());
        category.setCategoryDescription(cDTO.getCategoryDescription());


        /*----Then Simply Updated The Updated Category----*/
        Category sCategory = cRepo.save(category);


        /*----Convert cDTO to CategoryResponseDTO----*/
        cResponseDTO = this.modelMapper.map(sCategory, CategoryResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Update The Category With CategoryId = " + categoryId);
        response.setData(cResponseDTO);


        return response;
    }
}
