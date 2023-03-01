package co.blog.payloads.commentDTO;

import co.blog.payloads.pDTO.PostDTO;
import co.blog.payloads.uDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class CommentResponseDTO {
    private int commentId;
    private String commentContent;
    private PostDTO post;
    private UserDTO user;
}
