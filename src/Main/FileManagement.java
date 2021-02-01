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

    public static void printToTextDoc(int roomNumber) throws IOException {
        String firstName="";
        String lastName = "";
        String typeOfRoomString="";
        int pricePerNight=0;
        int numberOfNights=0;
        for (Room room : HotelManagementSystem.allRoomsList) {
            if (room.roomNr==roomNumber) {
                System.out.println("CheckOut this guest");
                
                typeOfRoomString=room.getName();
                Guest guestToCheckOut=room.getGuest();
                pricePerNight=room.getChargePerDay();
                firstName = guestToCheckOut.getFirstName();
                lastName = guestToCheckOut.getLastName();
                numberOfNights=guestToCheckOut.getNumberOfNights();
            }
        }
        
        
        
        String fullName = firstName + "_" + lastName;

        //TODO: get amount of nights and price. and gångra nights * price per night
        

        String testString3 = "\n\n\tReceipt for Guest \n\n\tNAME: " + firstName + " " + lastName + " \n\tRoom: "+roomNumber+" "+typeOfRoomString+"\n\tFor: "+numberOfNights+" Nights \n"
                + "\tTotal Price: "+pricePerNight*numberOfNights+" \n\n\tWelcome Back";

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

   
}
