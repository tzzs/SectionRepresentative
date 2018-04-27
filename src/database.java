import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    private String url;
    private String usr = "root";
    private String pwd = "root";
    private Connection connection;


    public database(String url, String usr, String pwd) {
        this.url = url;
        this.usr = usr;
        this.pwd = pwd;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
