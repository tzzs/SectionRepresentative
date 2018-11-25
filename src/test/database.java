package test;

import java.sql.*;

public class database {
    private String url;
    private String usr = "root";
    private String pwd = "root";
    private Connection connection;
    private String sql;
    private String[] pram;
    private ResultSet rs;
    private PreparedStatement preparedStatement;


    public database(String url) {
        this.url = url;
    }


    public void setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, usr, pwd);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < pram.length; i++) {
                preparedStatement.setString(i + 1, pram[i]);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery() {
        try {
            rs = preparedStatement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean execute() {
        try {
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean executeUpdate() {
        try {
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
