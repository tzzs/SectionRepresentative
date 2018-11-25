package com.zt.sr.pojo;

public class myFile {
    private String account;
    private String hno;
    private String file;

    public myFile() {

    }

    public myFile(String account, String hno, String file) {
        this.account = account;
        this.hno = hno;
        this.file = file;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHno() {
        return hno;
    }

    public void setHno(String hno) {
        this.hno = hno;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}

