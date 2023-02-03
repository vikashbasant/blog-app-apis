package co.blog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "userName", nullable = false, length = 100)
    private String name;
    private String email;
    private String password;
    private String about;

}
