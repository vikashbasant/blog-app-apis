package co.blog.payloads.uDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class UserResponseDTO {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAbout;
}
