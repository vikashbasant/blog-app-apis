package co.blog.payloads;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserResponseDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
