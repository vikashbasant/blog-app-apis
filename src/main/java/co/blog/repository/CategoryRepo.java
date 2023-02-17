package co.blog.repository;

import co.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;

@EnableJpaRepositories
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update Category c set c.categoryTitle = ?3, c.categoryDescription = ?2 where c.categoryId = ?1")
    void updateId (Integer categoryId, String categoryDescription, String categoryTitle);
}
