package co.blog.security;

import co.blog.exception.GeneralException;
import co.blog.repository.UserRepo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo uRepo;

    /**
     * This API are used loadUserByUsername.
     * @param userName the username identifying the user whose data is required.
     * @return return UserDetails.
     * @throws UsernameNotFoundException If anything goes wrong then this UsernameNotFoundException will generate.
     */
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {

        log.info("===: CustomUserDetailService:: Inside loadUserByUsername Method :===");


        log.info("userName = " + userName);

        /*----loading user from database by username----*/
        return this.uRepo.findByUserEmail(userName).orElseThrow(() -> new GeneralException("User not " +
                "found With userEmail = " + userName));


    }
}
