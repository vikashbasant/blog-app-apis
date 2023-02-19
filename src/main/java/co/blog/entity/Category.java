package co.blog.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Integer id;

    @Column(name = "categoryTitle", length = 300, nullable = false)
    private String categoryTitle;

    @Column(name = "categoryDescription")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> posts = new TreeSet<>();

}
