package co.blog.payloads.pDTO;


import co.blog.entity.Comment;
import co.blog.payloads.cDTO.CategoryDTO;
import co.blog.payloads.commentDTO.CommentDTO;
import co.blog.payloads.uDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class PostResponseDTO {

    private Integer postId;

    private String postTitle;

    private String postContent;

    private String imageName;

    private Date addedDate;

    private CategoryDTO category;

    private UserDTO user;


    // When ever we want to fetch the post then comment will automatically Fetch:
    private Set<CommentDTO> comments = new TreeSet<>();


}
