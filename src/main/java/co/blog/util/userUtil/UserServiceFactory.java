package co.blog.util.userUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceFactory {

    private final Map<UserServiceType, UserService> serviceMap = new EnumMap<>(UserServiceType.class);

    @Autowired
    public UserServiceFactory (Set<UserService> actionSet) {
        actionSet.forEach(this::createAction);
    }

    private void createAction(UserService service) {
        serviceMap.put(service.getServiceType(), service);
    }

    public UserService getService(UserServiceType type) {
        return Optional.ofNullable(serviceMap.get(type))
                .orElseThrow(() -> new RuntimeException("Action (" + type + ") is not implemented yet"));
    }

}
