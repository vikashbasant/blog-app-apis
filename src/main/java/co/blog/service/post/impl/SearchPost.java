package co.blog.service.post.impl;

import co.blog.constants.BlogAppConstants;
import co.blog.entity.Post;
import co.blog.exception.GeneralException;
import co.blog.payloads.PaginationDTO;
import co.blog.payloads.Response;
import co.blog.payloads.postdto.PostResponse;
import co.blog.payloads.postdto.PostResponseDTO;
import co.blog.repository.PostRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchPost implements BlogService {

    @Autowired
    private PostRepo pRepo;

    @Autowired
    private PostResponse postResponse;

    @Autowired
    private PaginationDTO paginationDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.SEARCH_POST;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {

        log.info("===: SearchPost:: Inside executeService Method :===");

        String keyword = (String) t;
        paginationDTO = (PaginationDTO) u;

        /*----Fetching all the details of paginationDtO object:----*/
        int pageNumber = paginationDTO.getPageNumber();
        int pageSize = paginationDTO.getPageSize();
        String sortBy = paginationDTO.getSortBy();
        String sortDir = paginationDTO.getSortDir();

        /*----For Sorting:----*/
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        /*----Create an Object of Pageable----*/
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        /*----Fetch the with respect to keyword and pageable:----*/
        Page<Post> pagePost = pRepo.searchByTitle(keyword, pageable);

        /*----Fetch the content of pagePost:----*/
        List<Post> posts = pagePost.getContent();

        /*----Now Iterate with listOfPost and convert PostResponseDTO----*/
        List<PostResponseDTO> listOfPosts = posts.stream().map(post -> modelMapper.map(post, PostResponseDTO.class)).collect(Collectors.toList());

        /*----Now Simply Return Response----*/
        postResponse.setStatus(BlogAppConstants.STATUS);
        postResponse.setStatusCode(BlogAppConstants.STATUS_CODE);
        postResponse.setMessage("Successfully Fetch The List Of Post With Search Keyword = " + keyword);
        postResponse.setContent(listOfPosts);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalRecords(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }
}
