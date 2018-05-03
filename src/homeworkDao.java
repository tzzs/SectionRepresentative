import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class homeworkDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    public void init() {
        connection = new jdbc("jdbc:mysql://localhost:3306/sectionrepresentative?useUnicode=true&useSSL=false&characterEncoding=UTF-8").getConnection();
    }

    public void add(Homework homework) {
        try {
            init();
            String sql = "insert into homework values(?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            //ps
            ps.setString(1, homework.getHno());
            ps.setString(2, homework.getHcontent());
            ps.setString(3, homework.getHdir());
            ps.setString(4, homework.getHfile());
            ps.setString(5, homework.getSubInfo());
            ps.setString(6, homework.getSubInfo());
            ps.setDate(7, (Date) homework.getBeginTime());
            ps.setDate(8, (Date) homework.getEndTime());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(null, ps, connection);
        }
    }

    public void delete(String hno) {
        try {
            init();
            String sql = "delete from homework where hno=?";
            ps = connection.prepareStatement(sql);
            //
            ps.setString(1, hno);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(null, ps, connection);
        }
    }

    public void update(Homework homework) {
        try {
            init();
            String sql = "update user set hcontent=?,hdir=?,hfile=?,subInfo=?,issuer=?,begintime=?,endtime=? where hno=?";
            ps = connection.prepareStatement(sql);

            ps.setString(1, homework.getHcontent());
            ps.setString(2, homework.getHdir());
            ps.setString(3, homework.getHfile());
            ps.setString(4, homework.getSubInfo());
            ps.setString(5, homework.getSubInfo());
            ps.setDate(6, (Date) homework.getBeginTime());
            ps.setDate(7, (Date) homework.getEndTime());
            ps.setString(8, homework.getHno());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(null, ps, connection);
        }
    }

    public Homework select(String hno) {
        init();
        Homework homework = new Homework();
        String sql = "select * from homework where hno=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, hno);
            rs = ps.executeQuery();
            while (rs.next()) {
                homework.setHno(rs.getString(1));
                homework.setHcontent(rs.getString(2));
                homework.setHdir(rs.getString(3));
                homework.setHfile(rs.getString(4));
                homework.setSubInfo(rs.getString(5));
                homework.setIssuer(rs.getString(6));
                homework.setBeginTime(rs.getDate(7));
                homework.setEndTime(rs.getDate(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(rs, ps, connection);
        }
        return homework;
    }
    public String selectHno(String hno) {
    	String reH="";
        init();
        String sql = "select * from homework where hno=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, hno);
            rs = ps.executeQuery();
            while (rs.next()) {
              String h=rs.getString("hno");
              if(h.equals(hno)){
            	  reH=rs.getString("Hdir");
            	  break;
              }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(rs, ps, connection);
        }
        return reH;
    }
    public List<Homework> selectAll(String account) {
        List<Homework> homeworkList = new ArrayList<>();
        init();
        String sql = "select * from homework where issuer=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, account);
            rs = ps.executeQuery();
            while (rs.next()) {
                Homework homework = new Homework();
                homework.setHno(rs.getString(1));
                homework.setHcontent(rs.getString(2));
                homework.setHdir(rs.getString(3));
                homework.setHfile(rs.getString(4));
                homework.setSubInfo(rs.getString(5));
                homework.setIssuer(rs.getString(6));
                homework.setBeginTime(rs.getDate(7));
                homework.setEndTime(rs.getDate(8));
                homeworkList.add(homework);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close(rs, ps, connection);
        }
        return homeworkList;
    }
}
