package co.blog.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "cTitle", length = 300, nullable = false)
    private String categoryTitle;

    @Column(name = "cDescription")
    private String categoryDescription;

}
