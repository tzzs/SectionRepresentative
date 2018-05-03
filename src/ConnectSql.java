import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSql {
  public static void main(String[] args) {
	
  }
  public ConnectSql(){
  }
  @SuppressWarnings("unused")
public Connection getConnection(){
		String url="jdbc:mysql://localhost:3306/std?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		String sqlName="root";
		String password="dr178512";
		try{Class.forName("com.mysql.jdbc.Driver");}catch(Exception e){System.out.println("连接"+e.toString());}
		try {
			Connection con1=(Connection) DriverManager.getConnection(url, sqlName, password);
			return con1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
