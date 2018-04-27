public class user {
    private int id;
    private String password;
    private String name;
    private String identity;
    private String email;

    public user() {

    }

    public user(int id, String password, String name, String identity, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.identity = identity;
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getId() {
        return id;
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
