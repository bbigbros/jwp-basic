package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jt = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jt.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jt = new JdbcTemplate();
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        jt.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() throws SQLException {
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        };
        JdbcTemplate jt = new JdbcTemplate();
        String sql = "SELECT * FROM USERS";
        return jt.list(sql, rm);
    }

    public User findByUserId(String userId) throws SQLException {
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        };
        JdbcTemplate jt = new JdbcTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return jt.query(sql, rm, userId);
    }
}
