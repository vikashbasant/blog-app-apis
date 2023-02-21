package co.blog.payloads.pDTO;

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

    private Integer PostId;

    @NotNull(message = "postTitle can't be Null")
    @NotEmpty(message = "postTitle can't be Empty")
    @Size(min = 3, max = 300, message = "postTitle minimum 3 & maximum 300 character are allowed")
    private String postTitle;

    @NotNull(message = "postContent can't be Null")
    @NotEmpty(message = "postContent can't be Empty")
    @Size(min = 10, max = 800, message = "postContent minimum 10 & maximum 800 character are allowed")
    private String postContent;

    private String imageName = "default.png";

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date addedDate = new Date();

    private Integer categoryId;

    private Integer userId;


}
