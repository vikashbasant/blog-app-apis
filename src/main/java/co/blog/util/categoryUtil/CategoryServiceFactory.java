package co.blog.util.categoryUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceFactory {

    private final Map<CategoryServiceType, CategoryService> serviceMap = new EnumMap<>(CategoryServiceType.class);

    @Autowired
    public CategoryServiceFactory (Set<CategoryService> actionSet) {
        actionSet.forEach(this::createAction);
    }

    private void createAction (CategoryService service) {
        serviceMap.put(service.getServiceType(), service);
    }

    public CategoryService getService (CategoryServiceType type) {
        return Optional.ofNullable(serviceMap.get(type))
                .orElseThrow(() -> new RuntimeException("Action (" + type + ") is not implemented yet"));
    }

}
