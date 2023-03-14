package co.blog.service.category.impl;

import co.blog.constants.BlogAppConstants;
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
public class CreateCategory implements BlogService {

    @Autowired
    private CategoryRepo cRepo;

    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryResponseDTO cResponseDTO;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.CREATE_CATEGORY;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: CreateUser:: Inside ExecuteService Method :===");

        CategoryDTO cDTO = (CategoryDTO) t;

        /*----Convert CategoryDTO into Category Object----*/
        Category category = modelMapper.map(cDTO, Category.class);

        /*----Now Save the Category Object into DB----*/
        Category sCategory = cRepo.save(category);

        /*----Convert Category into CategoryResponseDTO Object----*/
        cResponseDTO = modelMapper.map(sCategory, CategoryResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("User Created Successfully");
        response.setData(cResponseDTO);

        return response;
    }
}
