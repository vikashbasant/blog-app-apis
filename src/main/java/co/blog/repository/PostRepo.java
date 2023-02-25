package co.blog.repository;

import co.blog.entity.Category;
import co.blog.entity.Post;
import co.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface PostRepo extends JpaRepository<Post, Integer> {
    Page<Post> findByCategory (Category category, Pageable pageable);
    Page<Post> findByUser (User user, Pageable pageable);
    @Query("select p from Post p where lower(p.postTitle) like lower(concat('%',:keyword,'%'))")
    Page<Post> searchByTitle(@Param("keyword") String  title, Pageable pageable);
}
