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

    public static int printToTextDoc(int roomNumber) throws IOException {
        String firstName="";
        String lastName = "";
        String typeOfRoomString="";
        String foodForRoom="";
        int totalCostOfFood=0;
        int pricePerNight=0;
        int numberOfNights=0;
        for (Room room : HotelManagementSystem.allRoomsList) {
            if (room.roomNr==roomNumber) {
                
                
                typeOfRoomString=room.getName();
                Guest guestToCheckOut=room.getGuest();
                pricePerNight=room.getChargePerDay();
                firstName = guestToCheckOut.getFirstName();
                lastName = guestToCheckOut.getLastName();
                numberOfNights=guestToCheckOut.getNumberOfNights();
            }
        }
        
        for (Food food : HotelManagementSystem.foodList) {
            if(food.getRoomNr()==roomNumber){
                String foodinLoop=food.getName()+" "+food.getCost()+" ";
                foodForRoom=foodForRoom+foodinLoop;
                totalCostOfFood=totalCostOfFood+food.getCost();
            }
        }
        
        
        String fullName = firstName + "_" + lastName;
        
        String extrasToReceipt="";
        
        if (totalCostOfFood<1) {
            extrasToReceipt="None";
        }else{
            extrasToReceipt=foodForRoom;
        }
        int totalCostOverall = (pricePerNight*numberOfNights)+totalCostOfFood;
        
        String testString3 = "\n\n\tReceipt for Guest \n\n\tNAME: " + firstName + " " + lastName + " \n\tRoom: "+roomNumber+" "+typeOfRoomString+"\n\tFor: "+numberOfNights+" Nights ["+pricePerNight+" * "+numberOfNights+"] \n"
                + "\tPrice: "+pricePerNight*numberOfNights+"\n\tExtras: "+extrasToReceipt +"\n\tTotal Cost: "+totalCostOverall+"\n\n\tWelcome Back";

        try {
            FileManagement data = new FileManagement("kvitto_" + fullName + ".txt");
            data.writeToFile(testString3);
        } catch (IOException e) {
            System.err.println("Something went wrong trying to print receipt"+e);
        }

        System.out.println("Receipt sent to printer");
        removeRoomFromFoodList(roomNumber);
        removeGuestFroomRoomArrayList(roomNumber);
        System.out.println(Misc.GREEN+"Guest: "+firstName+" "+ lastName+" has been checked out");

        return totalCostOverall;
    }

    public void writeToFile(String guestInfoString) throws IOException {

        FileWriter write1 = new FileWriter(path, append_to_file);

        PrintWriter print_line = new PrintWriter(write1);

        print_line.printf(guestInfoString);

        print_line.close();

    }
    
    private static void removeRoomFromFoodList(int roomNumber){
        
        HotelManagementSystem.foodList.removeIf(e->e.getRoomNr()==roomNumber);
        
    }
    
    private static void removeGuestFroomRoomArrayList(int roomNumber){
        
        HotelManagementSystem.allRoomsList.stream().filter(e->e.getRoomNr()==roomNumber).forEach(e->e.setGuest(null));
        
    }

   
}
