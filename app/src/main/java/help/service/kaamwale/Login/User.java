package help.service.kaamwale.Login;

public class User {

    public String fullName,number,email;

    public User(){

    }

    public User(String fullName,String number,String email){
        this.fullName = fullName;
        this.number = number;
        this.email = email;
    }
    public void setEmail(String email) { this.email = email; }
    public void setName(String fullName) { this.fullName = fullName; }
    public void setNumber(String number) { this.number = number; }
    public String getNumber() { return number; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

}
