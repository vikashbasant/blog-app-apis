package co.blog.service.post.impl;

import co.blog.entity.Post;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.pDTO.PostResponse;
import co.blog.payloads.pDTO.PostResponseDTO;
import co.blog.repository.PostRepo;
import co.blog.util.BlogService;
import co.blog.util.BlogServiceType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetAllPost implements BlogService {

    @Autowired
    private PostRepo pRepo;

    @Autowired
    private PostResponse postResponse;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_ALL_POST;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {
        log.info("===: GetAllPost:: Inside executeService Method :===");

        Integer pageNumber = (Integer) t;
        Integer pageSize = (Integer) u;

        /*----Create an Object of Pageable----*/
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        /*----Fetch All The Post With Respect To Pageable Object----*/
        Page<Post> pagePost = pRepo.findAll(pageable);

        /*----Now Page of Post converted into List of Page----*/
        List<Post> allPost = pagePost.getContent();

        /*----If Record Is Empty Then Simply Throw Exception----*/
        if (allPost.isEmpty()) {
            throw new GeneralException("No Record Found!");
        }

        /*----Convert listOfPost into PostResponseDTO----*/
        List<PostResponseDTO> listOfPost = allPost.stream().map((post) -> modelMapper.map(post, PostResponseDTO.class)).collect(Collectors.toList());

        /*----Now Simply Return Response----*/
        postResponse.setStatus("SUCCESS");
        postResponse.setStatusCode("200");
        postResponse.setMessage("Successfully Fetch All The Record");
        postResponse.setContent(listOfPost);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalRecords(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }
}
