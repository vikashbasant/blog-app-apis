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

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GetAllCategory implements CategoryService {

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
        return CategoryServiceType.GET_ALL_CATEGORY;
    }

    @Override
    public <T> Response executeService (T t) throws GeneralException {

        log.info("===: GetAllCategory:: Inside ExecuteService Method :===");

        /*----Find All The Record Of User----*/
        List<Category> allCategory = cRepo.findAll();

        /*----If Record Is Empty Then Simply Throw Exception----*/
        if (allCategory.isEmpty()) {
            throw new GeneralException("No Record Found!");
        }

        /*----Create ArrayList Of CategoryResponseDTO----*/
        List<CategoryResponseDTO> listOfCategory = new ArrayList<>();

        /*----Fetch One User At A Time, Put Into ArrayList----*/
        allCategory.forEach(category -> {
            /*----Convert User into CategoryResponseDTO:----*/
            cResponseDTO = this.modelMapper.map(category,CategoryResponseDTO.class);
            listOfCategory.add(cResponseDTO);
        });

        /*----Now Simply Return Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Fetch All The Record");
        response.setData(listOfCategory);

        return response;
    }
}
