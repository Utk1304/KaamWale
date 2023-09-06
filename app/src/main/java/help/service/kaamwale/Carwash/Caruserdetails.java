package help.service.kaamwale.Carwash;

public class Caruserdetails {
    String carName;
    String carNumber;
    String address;
    String duration;
    String email;
    String name;
    String number;
    String Type;
    String Id;

    public  Caruserdetails(){}

    public Caruserdetails(String carName, String carNumber, String address, String duration,String email,String name,String number, String Type) {

        this.carName = carName;
        this.carNumber = carNumber;
        this.address = address;
        this.duration = duration;
        this.email = email;
        this.name = name;
        this.number = number;
        this.Type = Type;
        this.Id = Id;
    }
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
    public void setCarName(String CarName) {
        carName = CarName;
    }
    public void setCarNumber(String CarNumber) {
        carNumber = CarNumber;
    }
    public void setHouseNo(String Address) {address = Address;}
    public void setPincode(String Duration) {
        duration = Duration;
    }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setNumber(String number) { this.number = number; }
    public void setId(String Id) { this.Id = Id; }

    public String getcarName() {
        return carName;
    }
    public String getcarNumber() {
        return carNumber;
    }
    public String getaddress() {
        return address;
    }
    public String getduration() {
        return duration;
    }
    public String getNumber() { return number; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getId() { return Id; }

}