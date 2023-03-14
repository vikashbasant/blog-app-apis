package co.blog.payloads.commentdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentDTO {

    private int commentId;

    @NotNull(message = "commentContent can't be Null")
    @NotEmpty(message = "commentContent can't be Empty")
    @Size(min = 3, max = 300, message = "commentContent minimum 3 & maximum 300 character are allowed")
    private String commentContent;

    private Integer postId;

    private Integer userId;

}
