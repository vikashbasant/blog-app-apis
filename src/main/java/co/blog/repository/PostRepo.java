package co.blog.repository;

import co.blog.entity.Category;
import co.blog.entity.Post;
import co.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory (Category category);

    List<Post> findByUser (User user);
}
