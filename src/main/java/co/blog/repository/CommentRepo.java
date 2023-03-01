package co.blog.repository;

import co.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
