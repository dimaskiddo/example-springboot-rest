package id.dimaskiddo.example.dao;

import id.dimaskiddo.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MockDao")
public class UserMockDataAccessService implements UserDao {

    private static List<User> DB = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        return DB;
    }

    @Override
    public int addUser(UUID id, User user) {
        DB.add(new User(id, user.getName()));
        return 1;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int updateUserById(UUID id, User user) {
        return getUserById(id)
                .map(u -> {
                    int indexOfUserToUpdate = DB.indexOf(u);
                    if (indexOfUserToUpdate >= 0) {
                        DB.set(indexOfUserToUpdate, new User(id, user.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> userMayBe = getUserById(id);
        if (!userMayBe.isPresent()) {
            return 0;
        }

        DB.remove(userMayBe.get());
        return 1;
    }
}
