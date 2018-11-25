package test;

public class creatDatabase {
    private String dbname = "sectionRepresentative";


    public creatDatabase() {

    }

    public void create(){
        String url = "db.com.zt.sr.dao.jdbc:mysql://localhost:3306";
        String sql = "create test.database " + dbname;
        database db = new database(url);
        db.setConnection();
        db.setPreparedStatement(sql);
        db.executeUpdate();

        sql = "use "+dbname;
        db.setPreparedStatement(sql);
        db.executeUpdate();

//        ScriptRunner runner = new ScriptRunner();
    }

//    public static void main(String[] args) {
//        String url = "db.com.zt.sr.dao.jdbc:mysql://localhost:3306?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
//        String sql = "create test.database sectionRepresentative";
//        test.database db = new test.database(url);
//        db.setConnection();
//        db.setPreparedStatement(sql);
//        db.executeUpdate();
//    }
}
