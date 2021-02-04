
package Main;


public class Guest {
    
    static private int firstFreeGuestNr =SQLManagement.getLagestIdFromDb();
    private int guestId;
    private String firstName;
    private String lastName;
    private int numberOfNights;

    public Guest(String firstName, String lastName, int numberOfNights) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfNights=numberOfNights;
        this.guestId=firstFreeGuestNr++;  //TODO: get the last id from SQL och adda den här istället för id++;
    }

    public Guest() {
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

    public int getGuestId() {
        return guestId;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
    
    

    @Override
    public String toString() {
        return "Guest{" + "firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    
    
}
