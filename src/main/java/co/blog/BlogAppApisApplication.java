package co.blog;

import co.blog.config.ApplicationRunnerForRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationRunnerForRole applicationRunnerForRole;

    public static void main (String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    /*----Now We Can Uses ModelMapper In Our Project----*/
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run (String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("Vikky@333"));

        this.applicationRunnerForRole.createRoles();
    }
}
