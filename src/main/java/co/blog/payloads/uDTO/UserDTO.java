package co.blog.payloads.uDTO;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDTO {
    private int id;

    @NotNull(message = "Name can't be Null")
    @NotEmpty(message = "Name can't be Empty")
    @Size(min = 2, max = 20, message = "Character Of Name Must be Length of 2 to 20")
    @Pattern(regexp = "^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$", message = "Please Enter Valid Name!")
    private String name;

    @NotNull(message = "Email can't be Null")
    @NotEmpty(message = "Email can't be Empty")
    @Email
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", message = "Please Enter Valid Email!")
    private String email;

    @NotNull(message = "password can't be Null")
    @NotEmpty(message = "password can't be Empty")
    @Size(min = 5, max = 15, message = "Password must be min of 5 characters and max of 15 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{5,15}$")
    private String password;

    @NotNull(message = "About can't be Null")
    @NotEmpty(message = "About can't be Empty")
    @Size(max = 500, message = "About maximum 500 character are allowed")
    private String about;

    private int userId;
}
