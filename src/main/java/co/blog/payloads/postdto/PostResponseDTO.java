package co.blog.payloads.postdto;


import co.blog.payloads.categorydto.CategoryDTO;
import co.blog.payloads.commentdto.CommentDTO;
import co.blog.payloads.userdto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private UserResponseDTO user;


    // When ever we want to fetch the post then comment will automatically Fetch:
    private Set<CommentDTO> comments = new HashSet<>();


}
