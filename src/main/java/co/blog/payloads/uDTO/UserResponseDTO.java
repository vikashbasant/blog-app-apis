package co.blog.payloads.uDTO;

import co.blog.entity.Role;
import co.blog.payloads.commentDTO.CommentDTO;
import co.blog.payloads.commentDTO.CommentResponseDTO;
import co.blog.payloads.pDTO.PostResponseDTO;
import co.blog.payloads.rDTO.RoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class UserResponseDTO {
    private int userId;
    private String userName;
    private String userEmail;
    @JsonIgnore
    private String userPassword;
    private String userAbout;

    private Set<RoleDTO> roles = new TreeSet<>();

}
