package co.blog.payloads.postdto;

import co.blog.constants.BlogAppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostDTO {

    private Integer postId;

    @NotNull(message = "postTitle can't be Null")
    @NotEmpty(message = "postTitle can't be Empty")
    @Size(min = 3, max = 300, message = "postTitle minimum 3 & maximum 300 character are allowed")
    private String postTitle;

    @NotNull(message = "postContent can't be Null")
    @NotEmpty(message = "postContent can't be Empty")
    @Size(min = 10, max = 800, message = "postContent minimum 10 & maximum 800 character are allowed")
    private String postContent;

    private String postImageName = BlogAppConstants.DEFAULT_IMAGE_NAME;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date postAddedDate = new Date();

    private Integer categoryId;

    private Integer userId;

}
