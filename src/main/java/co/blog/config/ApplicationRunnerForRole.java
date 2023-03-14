package co.blog.config;

import co.blog.entity.Role;
import co.blog.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static co.blog.config.RoleConstants.ADMIN_USER_NAME;

@Component
@Slf4j
public class ApplicationRunnerForRole {

    @Autowired
    private RoleRepo rRepo;

    /**
     * Purpose of the API, When you run the application every time createRoles Method for creating role of user.
     * @throws Exception If anything goes wrong then this Exception will generate.
     */
    public void createRoles() throws Exception {

        log.info("===: ApplicationRunnerForRole:: Inside createRoles Method :===");

        try {
            /*----Create Role of normalUser----*/
            Role normalUser = new Role();
            normalUser.setRoleId(RoleConstants.NORMAL_USER);
            normalUser.setRoleName(RoleConstants.NORMAL_USER_NAME);

            /*----Create Role of adminUser----*/
            Role adminUser = new Role();
            adminUser.setRoleId(RoleConstants.ADMIN_USER);
            adminUser.setRoleName(ADMIN_USER_NAME);

            List<Role> roleList = new ArrayList<>();
            roleList.add(normalUser);
            roleList.add(adminUser);

            /*----Now Simply Save The List of RoleList*/
            this.rRepo.saveAll(roleList);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
