package co.blog.payloads.userdto;

import co.blog.constants.RoleConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO {
    private int userId;

    @NotNull(message = "Name can't be Null")
    @NotEmpty(message = "Name can't be Empty")
    @Size(min = 2, max = 20, message = "Character Of Name Must be Length of 2 to 20")
    @Pattern(regexp = "^[a-zA-Z_0-9.\\s]*$", message = "Please Enter Valid Name!")
    private String name;

    @NotNull(message = "Email can't be Null")
    @NotEmpty(message = "Email can't be Empty")
    @Email
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", message = "Please Enter Valid Email!")
    @Column(unique = true, columnDefinition = "Please Enter Unique Email Id")
    private String userEmail;

    @NotNull(message = "password can't be Null")
    @NotEmpty(message = "password can't be Empty")
    @Size(min = 5, max = 15, message = "Password must be min of 5 characters and max of 15 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^=()])(?=\\S+$).{5,15}$")
    private String userPassword;

    @NotNull(message = "About can't be Null")
    @NotEmpty(message = "About can't be Empty")
    @Size(max = 500, message = "About maximum 500 character are allowed")
    private String userAbout;

    private String userRole = RoleConstants.NORMAL_USER_NAME;


    @JsonIgnore
    public String getUserPassword() {
        return this.userPassword;
    }


    @JsonProperty
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
