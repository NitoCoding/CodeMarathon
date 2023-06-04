package mdbudget.models;

public class User extends BaseModel{
    private int userId;
    
    private String userNama;
    private String userPassword;
    private String userRole;
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUserRole() {
        return userRole;
    }
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public User(String userNama, String userPassword) {
        this.userNama = userNama;
        this.userPassword = userPassword;
    }

    public User(int userId,String userNama, String userPassword, String userRole){
        this.userId = userId;
        this.userNama = userNama;
        this.userPassword = userPassword;
        this.userRole = userRole;
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

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + getId());
        System.out.println("Nama: " + getUserNama());
        System.out.println("Password: " + getUserPassword());
    }
}
