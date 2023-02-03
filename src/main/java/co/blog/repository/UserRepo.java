package co.blog.repository;

import co.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;

@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update User u set u.about = ?5, u.password = ?4, u.email = ?3, u.name = ?2 where u.id = ?1")
    void updateId (Integer id, String name, String email, String password, String about);
}
