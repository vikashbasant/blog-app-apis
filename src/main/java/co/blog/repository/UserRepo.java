package co.blog.repository;

import co.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;
import java.util.Optional;

@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update User u set u.userAbout = ?5, u.userPassword = ?4, u.userEmail = ?3, u.userName = ?2 where u.userId = ?1")
    void updateId (Integer id, String name, String email, String password, String about);


    Optional<User> findByUserEmail (String userEmail);
}
