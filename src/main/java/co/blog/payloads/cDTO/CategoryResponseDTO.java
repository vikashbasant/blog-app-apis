package co.blog.payloads.cDTO;


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
public class CategoryResponseDTO {

    private Integer categoryId;

    private String categoryTitle;

    private String categoryDescription;

}
