package co.blog.repository;

import co.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PostRepo extends JpaRepository<Post, Integer> {
}
