package co.blog.payloads.udto;

import co.blog.payloads.rdto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String name;
    private String userEmail;

    private String userPassword;
    private String userAbout;

    private Set<RoleDTO> roles = new TreeSet<>();

    @JsonIgnore
    public String getUserPassword() {
        return this.userPassword;
    }

    @JsonProperty
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
