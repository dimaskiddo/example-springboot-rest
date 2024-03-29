package id.dimaskiddo.example.dao;

import id.dimaskiddo.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("DBDao")
public class UserDBDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDBDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        final String sql = "SELECT id, name FROM user";

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new User(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name")
            );
        });
    }

    @Override
    public int addUser(UUID id, User user) {
        final String sql = "INSERT INTO user (id, name) VALUES (?, ?)";

        jdbcTemplate.update(sql, id.toString(), user.getName());

        return 1;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        final String sql = "SELECT id, name FROM user WHERE id = ?";

        User user = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            return new User(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name")
            );
        }, new Object[]{id.toString()});

        return Optional.ofNullable(user);
    }

    @Override
    public int updateUserById(UUID id, User user) {
        Optional<User> userMayBe = getUserById(id);
        if (userMayBe.isEmpty()) {
            return 0;
        }

        final String sql = "UPDATE user SET name = ? WHERE id = ? LIMIT 1";

        jdbcTemplate.update(sql, user.getName(), id.toString());

        return 1;
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> userMayBe = getUserById(id);
        if (userMayBe.isEmpty()) {
            return 0;
        }

        final String sql = "DELETE FROM user WHERE id = ? LIMIT 1";

        jdbcTemplate.update(sql, id.toString());

        return 1;
    }
}
