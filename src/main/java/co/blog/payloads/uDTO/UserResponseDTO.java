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
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
