package co.blog.payloads.cDTO;


import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class CategoryResponseDTO {

    private Integer categoryId;

    private String categoryTitle;

    private String categoryDescription;

}
