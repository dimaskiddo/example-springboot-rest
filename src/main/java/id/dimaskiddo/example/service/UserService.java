package id.dimaskiddo.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import id.dimaskiddo.example.dao.UserDao;
import id.dimaskiddo.example.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("fakeDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
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
