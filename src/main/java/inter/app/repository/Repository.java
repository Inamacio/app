package inter.app.repository;

import inter.app.model.User;

import java.util.UUID;

/**
 * Interface Repository
 */
public interface Repository {
    User find(UUID id);

    void delete(UUID id);

    void save(UUID id, User user);
}
