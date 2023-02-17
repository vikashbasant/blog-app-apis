package co.blog.payloads.cDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoryDTO {

    private Integer categoryId;

    @NotNull(message = "categoryTitle can't be Null")
    @NotEmpty(message = "categoryTitle can't be Empty")
    @Size(max = 300, message = "categoryTitle maximum 500 character are allowed")
    private String categoryTitle;


    @NotNull(message = "categoryDescription can't be Null")
    @NotEmpty(message = "categoryDescription can't be Empty")
    @Size(max = 800, message = "categoryDescription maximum 800 character are allowed")
    private String categoryDescription;
}
