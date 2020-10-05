package inter.app.repository;

import inter.app.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UserRepository implements Repository {

    private Map<UUID, User> map = new HashMap<>();

    public void save(UUID id, User user) {
        map.put(id, user);
    }

    public User find(UUID id) {
        return map.getOrDefault(id, null);
    }

    public void delete(UUID id) {
        map.remove(id);
    }
}
