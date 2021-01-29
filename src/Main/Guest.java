
package Main;


public class Guest {
    
    static private int id =1;
    private String firstName;
    private String lastName;

    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id=id++;  //TODO: get the last id from SQL och adda den här istället för id++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Guest{" + "firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    
    
}
