package co.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = " posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Integer postId;

    @Column(name = "postTitle", nullable = false, length = 100)
    private String postTitle;

    @Column(name = "postContent", nullable = false, length = 1000)
    private String postContent;

    @Column(name = "postImageName")
    private String postImageName;

    @Column(name = "postAddedDate")
    private Date postAddedDate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;



}
