package co.blog.payloads.cDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDTO {

    private Integer categoryId;

    @NotNull(message = "categoryTitle can't be Null")
    @NotEmpty(message = "categoryTitle can't be Empty")
    @Size(min = 3, max = 300, message = "categoryTitle minimum 3 & maximum 300 character are allowed")
    private String categoryTitle;


    @NotNull(message = "categoryDescription can't be Null")
    @NotEmpty(message = "categoryDescription can't be Empty")
    @Size(min = 10, max = 800, message = "categoryDescription minimum 10 & maximum 800 character are allowed")
    private String categoryDescription;
}
