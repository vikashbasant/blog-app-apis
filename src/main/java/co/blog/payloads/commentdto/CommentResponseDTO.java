package co.blog.payloads.commentdto;

import co.blog.payloads.pdto.PostDTO;
import co.blog.payloads.udto.UserDTO;
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
