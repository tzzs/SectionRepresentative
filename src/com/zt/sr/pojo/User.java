package com.zt.sr.pojo;

public class User {
    private String account;
    private String password;
    private String name;
    private String attention;
    private String email;
    private String school;
    private boolean identity;

    public User() {

    }

    public User(String account, String password, String name, String attention, String email, String school, boolean identity) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.attention = attention;
        this.email = email;
        this.school = school;
        this.identity = identity;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;
    }


    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
