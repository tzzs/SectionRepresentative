import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    public void init() {
        connection = new jdbc("aaa").getConnection();
    }

    public void add(User user) {
        try {
            init();
            String sql = "insert into user values(?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            //ps
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getAttention());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getSchool());
            ps.setBoolean(7, user.isIdentity());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(null, ps, connection);
        }
    }

    public void delete(String account) {
        try {
            init();
            String sql = "delete from user where account=?";
            ps = connection.prepareStatement(sql);
            //
            ps.setString(1, account);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(null, ps, connection);
        }
    }

    public void update(User user) {
        try {
            init();
            String sql = "update user set password=?,name=?,attention=?,email=?,school=?,identity=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            //ps
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(null, ps, connection);
        }
    }

    public User select(String account) {
//        List<User> userList = new ArrayList<User>();
        init();
        User user = new User();
        String sql = "select * from user where account=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, account);
            rs = ps.executeQuery();
            while (rs.next()) {

                user.setPassword(rs.getString(2));
                user.setName(rs.getString(3));
                user.setAttention(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setSchool(rs.getString(6));
                user.setIdentity(rs.getBoolean(7));
//                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAll(String account) {
        List<User> userList = new ArrayList<User>();
        init();
        String sql = "select * from user where account=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, account);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setPassword(rs.getString(2));
                user.setName(rs.getString(3));
                user.setAttention(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setSchool(rs.getString(6));
                user.setIdentity(rs.getBoolean(7));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
