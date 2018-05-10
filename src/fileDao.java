import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class fileDao {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    public void init() {
        connection = new jdbc("jdbc:mysql://localhost:3306/sectionrepresentative?useUnicode=true&useSSL=false&characterEncoding=UTF-8").getConnection();
    }

    public List<String> select(String hno){
        init();
        String sql = "select * from file where and hno=?";
        myFile file = new myFile();
        List<String> submitted = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,hno);

            rs = ps.executeQuery();

            if (!rs.next()){
                submitted.add(rs.getString(2));
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submitted;
    }
    public void add(myFile file){
        init();
        String sql = "insert into file values(?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,file.getAccount());
            ps.setString(2,file.getHno());
            ps.setString(3,file.getFile());

            ps.executeUpdate();

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (connection != null) {
                            try {
                                connection.commit();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
