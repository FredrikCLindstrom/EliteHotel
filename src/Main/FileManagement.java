package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManagement {

    private String path;
    private boolean append_to_file = false; //skriver overgamla vid false, fortsätter på dokumentet vid true

    public FileManagement(String filePath) {
        this.path = filePath;
    }

    public static void printToTextDoc(Guest guest) throws IOException {

        String firstName = guest.getFirstName();
        String lastName = guest.getLastName();
        String fullName = firstName + "_" + lastName;

        //TODO: get amount of nights and price. 
        String testString1 = "\n\n\tReceipt for Guest \n\n\tNAME: getFirstNames() + getLastName() \n\tRoom:getRoomNr() getRoomType() \n\tFor: getAmountOfNights() Nigths \n"
                + "\tTotal Price: getPrice() \n\n\tWelcome Back";

        String testString2 = "\n\n\tReceipt for Guest \n\n\tNAME: Björn Andersson \n\tRoom: 11 singleDeluxe \n\tFor: 3 Nigths \n"
                + "\tTotal Price: 4500 \n\n\tWelcome Back";

        String testString3 = "\n\n\tReceipt for Guest \n\n\tNAME: " + firstName + " " + lastName + " \n\tRoom: 11 singleDeluxe \n\tFor: 3 Nigths \n"
                + "\tTotal Price: 4500 \n\n\tWelcome Back";

        try {
            FileManagement data = new FileManagement("kvitto" + fullName + ".txt");
            data.writeToFile(testString3);
        } catch (Exception e) {
            System.err.println("Something went wrong trying to print receipt");
        }

        System.out.println("Receipt sent to printer");

    }

    public void writeToFile(String guestInfoString) throws IOException {

        FileWriter write1 = new FileWriter(path, append_to_file);

        PrintWriter print_line = new PrintWriter(write1);

        print_line.printf(guestInfoString);

        print_line.close();

    }

    public static void main(String[] args) throws IOException { //TODO: REMOVE main method in the class, only for testpurposes
        Guest guest1 = new Guest("Arne", "persson");// Test guest för utskrifts formatet bara

        HotelManagementSystem.allRoomsList.stream().filter(e->e.getGuest()!=null).
                forEach(System.out::println);
        
        FileManagement.printToTextDoc(guest1); //this is the method to be called on checkout. 

    }
}
