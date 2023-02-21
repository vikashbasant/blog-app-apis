package co.blog.service.post.impl;

import co.blog.entity.Post;
import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import co.blog.payloads.pDTO.PostResponseDTO;
import co.blog.repository.PostRepo;
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
public class GetAllPost implements BlogService {

    @Autowired
    private PostRepo pRepo;

    @Autowired
    private Response response;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BlogServiceType getServiceType () {
        return BlogServiceType.GET_ALL_POST;
    }

    @Override
    public <T, U> Response executeService (T t, U u) throws GeneralException {
        log.info("===: GetAllPost:: Inside executeService Method :===");

        /*----Fetch All The Post----*/
        List<Post> allPost = pRepo.findAll();

        /*----If Record Is Empty Then Simply Throw Exception----*/
        if (allPost.isEmpty()) {
            throw new GeneralException("No Record Found!");
        }

        /*----Convert listOfPost into PostResponseDTO----*/
        List<PostResponseDTO> listOfPost = allPost.stream().map((post) -> modelMapper.map(post, PostResponseDTO.class)).collect(Collectors.toList());

        /*----Now Simply Return Response----*/
        response.setStatus("SUCCESS");
        response.setStatusCode("200");
        response.setMessage("Successfully Fetch All The Record");
        response.setData(listOfPost);

        return response;
    }
}
