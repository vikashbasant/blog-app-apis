package co.blog.payloads.userdto;

import co.blog.payloads.commentdto.CommentDTO;
import co.blog.payloads.postdto.PostDTOForUser;
import co.blog.payloads.roledto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class UserResponseDTO {
    private int userId;
    private String name;
    private String userEmail;

    private String userPassword;
    private String userAbout;

    private Set<RoleDTO> roles = new HashSet<>();

    private Set<CommentDTO> comments = new HashSet<>();

    private Set<PostDTOForUser> posts = new HashSet<>();

    @JsonIgnore
    public String getUserPassword() {
        return this.userPassword;
    }

    @JsonProperty
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
