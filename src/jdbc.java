import java.sql.*;

public class jdbc {
    private String url;
    private Connection connection;
    private Statement ps;
    private ResultSet rs;

    public jdbc(String url) {
        this.url = url;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String usr = "root";
        String pwd = "root";
        try {
            connection = DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



    public static void close(ResultSet rs, Statement ps, Connection connection) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
