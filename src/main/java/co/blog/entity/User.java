package co.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private int id;

    @Column(name = "userName", nullable = false, length = 100)
    private String name;

    @Column(name = "userEmail")
    private String email;

    @Column(name = "userPassword")
    private String password;

    @Column(name = "userAbout")
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    private Set<Post> posts = new TreeSet<>();

}
