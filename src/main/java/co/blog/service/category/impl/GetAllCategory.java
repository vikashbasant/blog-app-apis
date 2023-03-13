package co.blog.service.category.impl;

import co.blog.config.BlogAppConstants;
import co.blog.entity.Category;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.cdto.CategoryResponseDTO;
import co.blog.repository.CategoryRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetAllCategory implements BlogService {

    @Autowired
    private CategoryRepo cRepo;

    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_ALL_CATEGORY;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: GetAllCategory:: Inside ExecuteService Method :===");

        /*----Find All The Record Of User----*/
        List<Category> allCategory = cRepo.findAll();

        /*----If Record Is Empty Then Simply Throw Exception----*/
        if (allCategory.isEmpty()) {
            throw new GeneralException("No Record Found!");
        }

        /*----Fetch One Category At A Time, Collect into list----*/
        List<CategoryResponseDTO> listOfCategory = allCategory.stream().map(category -> modelMapper.map(category,
                CategoryResponseDTO.class)).collect(Collectors.toList());

        /*----Now Simply Return Response----*/
        response.setStatus(BlogAppConstants.STATUS);
        response.setStatusCode(BlogAppConstants.STATUS_CODE);
        response.setMessage("Successfully Fetch All The Record");
        response.setData(listOfCategory);

        return response;
    }
}
