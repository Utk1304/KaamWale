package help.service.kaamwale.Plumbingservice;

public class PlumbinguserDetails {

        String duration;
        String address;
        String email;
        String name;
        String number;
        String Type;
    String Id;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

        public PlumbinguserDetails(String address, String duration,String email,String name,String number, String Type) {

            this.address = address;
            this.duration = duration;
            this.email = email;
            this.name = name;
            this.number = number;
            this.Type = Type;
        }

        public String getType() {
        return Type;
    }
        public void setType(String type) {
        Type = type;
    }
        public String getDuration() {return duration;}
        public void setEmail(String email) { this.email = email; }
        public void setName(String name) { this.name = name; }
        public void setNumber(String number) { this.number = number; }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getAddress() {
            return address;
        }
        public String getNumber() { return number; }
        public String getName() { return name; }
        public String getEmail() { return email; }

        public void setAddress(String address) {
            this.address = address;
        }
}
