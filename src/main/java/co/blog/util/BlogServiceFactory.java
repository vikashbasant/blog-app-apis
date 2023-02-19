package co.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class BlogServiceFactory {

    private final Map<BlogServiceType, BlogService> serviceMap = new EnumMap<>(BlogServiceType.class);

    @Autowired
    public BlogServiceFactory (Set<BlogService> actionSet) {
        actionSet.forEach(this::createAction);
    }

    private void createAction(BlogService service) {
        serviceMap.put(service.getServiceType(), service);
    }

    public BlogService getService(BlogServiceType type) {
        return Optional.ofNullable(serviceMap.get(type)).orElseThrow(() -> new RuntimeException("Action (" + type +
                ") is not implemented yet"));
    }
}
