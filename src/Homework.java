import java.util.Date;

public class Homework {
    private String hno;
    private String hcontent;
    private String hdir;
    private String hfile;
    private String subInfo;
    private String issuer;
    private Date beginTime;
    private Date endTime;


    public Homework() {

    }

    public Homework(String hno, String hcontent, String hdir, String hfile, String subInfo, String issuer, Date beginTime, Date endTime) {
        this.hno = hno;
        this.hcontent = hcontent;
        this.hdir = hdir;
        this.hfile = hfile;
        this.subInfo = subInfo;
        this.issuer = issuer;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public String getHno() {
        return hno;
    }

    public void setHno(String hno) {
        this.hno = hno;
    }

    public String getHcontent() {
        return hcontent;
    }

    public void setHcontent(String hcontent) {
        this.hcontent = hcontent;
    }

    public String getHdir() {
        return hdir;
    }

    public void setHdir(String hdir) {
        this.hdir = hdir;
    }

    public String getHfile() {
        return hfile;
    }

    public void setHfile(String hfile) {
        this.hfile = hfile;
    }

    public String getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(String subInfo) {
        this.subInfo = subInfo;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
