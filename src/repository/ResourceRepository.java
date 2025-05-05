package repository;

import java.util.HashMap;
import java.util.Map;

import entity.Resource;

public class ResourceRepository {
private Map<String, Resource> resources = new HashMap<>();

public void save(Resource resource) {
resources.put(resource.getId(), resource);
}

public Resource findById(String id) {
return resources.get(id);
}

public Map<String, Resource> findAll() {
return new HashMap<>(resources);
}

public void delete(String id) {
resources.remove(id);
}
}