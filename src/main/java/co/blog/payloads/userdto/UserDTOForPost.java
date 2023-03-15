package co.blog.payloads.userdto;

import co.blog.payloads.roledto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTOForPost {
    private int userId;
    private String name;
    private String userEmail;

    private String userPassword;
    private String userAbout;

    private Set<RoleDTO> roles = new HashSet<>();

    @JsonIgnore
    public String getUserPassword() {
        return this.userPassword;
    }

    @JsonProperty
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
