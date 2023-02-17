package co.blog.service.category.impl;

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
public class UpdateCategory implements CategoryService {

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
        return CategoryServiceType.UPDATE_CATEGORY;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: UpdateCategory:: Inside ExecuteService Method :===");

        CategoryDTO cDTO = (CategoryDTO) t;

        /*----First Find The Category With CategoryId----*/
        cRepo.findById(cDTO.getCategoryId()).orElseThrow(() -> new GeneralException(
                "Category Not Found With CategoryId = " + cDTO.getCategoryId()));

        /*----Then Simply Update The User----*/
        cRepo.updateId(cDTO.getCategoryId(), cDTO.getCategoryDescription(), cDTO.getCategoryTitle());

        /*----Convert cDTO to CategoryResponseDTO----*/
        cResponseDTO = this.modelMapper.map(cDTO, CategoryResponseDTO.class);

        /*----Simply Return The Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Update The Category With CategoryId = " + cDTO.getCategoryId());
        response.setData(cResponseDTO);


        return response;
    }
}
