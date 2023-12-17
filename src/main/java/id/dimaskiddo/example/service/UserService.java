package id.dimaskiddo.example.service;

import id.dimaskiddo.example.dao.UserDao;
import id.dimaskiddo.example.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(@Qualifier("DBDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.getUserById(id);
    }

    public int updateUserById(UUID id, User user) {
        return userDao.updateUserById(id, user);
    }

    public int deleteUserById(UUID id) {
        return userDao.deleteUserById(id);
    }
}
