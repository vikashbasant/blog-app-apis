package co.blog.service.category.impl;

import co.blog.config.BlogAppConstants;
import co.blog.entity.Category;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
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
public class DeleteCategory implements BlogService {

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
        return BlogServiceType.DELETE_CATEGORY;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: DeleteCategory:: Inside ExecuteService Method :===");

        Integer categoryId = (Integer) t;

        /*----Before delete the Category, Fetch the Category:----*/
        Category byCategoryId =
                cRepo.findById(categoryId).orElseThrow(() -> new GeneralException("Category Not " +
                "Found With CategoryId = " + categoryId));

        /*----Now Simply Delete the Category with CategoryId:----*/
        cRepo.deleteById(categoryId);

        /*----Now Convert the Category to CategoryResponseDTO:----*/
        cResponseDTO = this.modelMapper.map(byCategoryId, CategoryResponseDTO.class);

        /*----Now Simply Return Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Delete The Category With CategoryId = " + categoryId);
        response.setData(cResponseDTO);

        return response;
    }
}
