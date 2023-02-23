package co.blog.payloads.pDTO;


import co.blog.payloads.cDTO.CategoryDTO;
import co.blog.payloads.uDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

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


}
