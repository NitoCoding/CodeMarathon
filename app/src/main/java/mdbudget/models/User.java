package mdbudget.models;

public class User {
    int userId;
    String userNama;
    String userPassword;

    public User(String userNama, String userPassword) {
        this.userNama = userNama;
        this.userPassword = userPassword;
    }

    public User(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNama() {
        return userNama;
    }

    public void setUserNama(String userNama) {
        this.userNama = userNama;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
