package co.blog.payloads.postdto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTOForUser {
    private Integer postId;

    private String postTitle;

    private String postContent;

    private String imageName;

    private Date addedDate;
}
