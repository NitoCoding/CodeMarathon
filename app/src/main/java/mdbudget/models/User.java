package mdbudget.models;

public class User extends BaseModel{
    int userId;
    String userNama;
    String userPassword;
    String userRole;

    public User(String userNama, String userPassword) {
        this.userNama = userNama;
        this.userPassword = userPassword;
    }

    public User(){}

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

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + getId());
        System.out.println("Nama: " + getUserNama());
        System.out.println("Password: " + getUserPassword());
    }
}
