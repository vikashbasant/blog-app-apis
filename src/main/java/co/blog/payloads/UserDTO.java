package co.blog.payloads;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    private int id;

    @NotNull(message = "Name can't be Null")
    @NotEmpty(message = "Name can't be Empty")
    @Length(min = 2, max = 20)
    private String name;

    @NotNull(message = "Email can't be Null")
    @NotEmpty(message = "Email can't be Empty")
    private String email;

    @NotNull(message = "Name can't be Null")
    @NotEmpty(message = "Name can't be Empty")
    @Length(min = 5, max = 15)
    private String password;
    private String about;

    private int userId;

}
