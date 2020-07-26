package id.dimaskiddo.example.daos;

import id.dimaskiddo.example.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    List<User> getUsers();

    int addUser(UUID id, User user);

    default int addUser(User user) {
        UUID id = UUID.randomUUID();
        return addUser(id, user);
    }

    Optional<User> getUserById(UUID id);

    int updateUserById(UUID id, User user);

    int deleteUserById(UUID id);
}
