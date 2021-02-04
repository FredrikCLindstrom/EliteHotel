package Main;

import static Main.GuestUser.checkThatThereAreGuestsThatCanCheckOut;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReceptionUser {

    private final static Scanner SCANNER = new Scanner(System.in);

    public static final String MENU_COLOR = Misc.CYAN;
    public static final String CHOICE_COLOR = Misc.YELLOW;
    public static final String ERROR_COLOR = Misc.RED;
    public static final String INFO_COLOR = Misc.GREEN;
    public static final String TODO_COLOR = Misc.MAGENTA;
    public static final String RESET_COLOR = Misc.RESET; // Normally white color

    public enum ReceptionMenuItem {
        // parameters menuChar, menuChoiceText, enabledMenuChoice, hiddenMenuChoice
        RECEPTION_MENU_____STORE_GUEST('T', "STore guest data", true, false),
        RECEPTION_MENU____SEARCH_GUEST('S', "Search guest data", true, false),
        RECEPTION_MENU____CHANGE_GUEST('Z', "Change guest data", true, false),
        RECEPTION_MENU____DELETE_GUEST('D', "Delete guest data", true, false),
        RECEPTION_MENU_______BOOK_ROOM('B', "Book a room", true, false),
        RECEPTION_MENU____UPGRADE_ROOM('U', "Upgrade a room", true, false),
        RECEPTION_MENU______ORDER_FOOD('F', "Order Food", true, false),
        GUEST_MENU________CHECKOUT('C', "Checkout", true, false),
        RECEPTION_MENU_____HIDDEN_TEST('!', "HIDDEN CHOICE, NOT SHOWN", true, true),
        RECEPTION_MENU__EXIT_RECEPTION('X', "EXit reception menu", true, false);

        private final String menuText;
        private final char menuChar;
        private boolean enabledMenuChoice;
        private boolean hiddenMenuChoice;

        ReceptionMenuItem(char menuCh, String menuChoiceText, boolean enabledMenuChoice, boolean hiddenMenuChoice) {
            this.menuChar = menuCh;
            this.menuText = menuChoiceText;
            this.enabledMenuChoice = enabledMenuChoice;
            this.hiddenMenuChoice = hiddenMenuChoice;
        }

        public char getMenuChoiceChar() {
            return menuChar;
        }

        public String getMenuChoiceText() {
            return menuText;
        }

        public void setEnabledMenuChoice(boolean enabledMenuChoice) {
            this.enabledMenuChoice = enabledMenuChoice;
        }

        public void setHiddenMenuChoice(boolean hiddenMenuChoice) {
            this.hiddenMenuChoice = hiddenMenuChoice;
        }

        public boolean isEnabledMenuChoice() {
            return enabledMenuChoice;
        }

        public boolean ishiddenMenuChoice() {
            return hiddenMenuChoice;
        }

        public ReceptionMenuItem getReceptionMenuItem(char menuCh) {
            ReceptionMenuItem menuItem = null;

            // Loop over all the menu choises, and return the right (first) item that is enabled and has the matching character
            for (ReceptionMenuItem value : ReceptionMenuItem.values()) {
                if (value.isEnabledMenuChoice() && (menuCh == value.getMenuChoiceChar()) || Character.toLowerCase(menuCh) == Character.toLowerCase(value.getMenuChoiceChar())) {
                    menuItem = value;
                    return menuItem;
                }
            }
            return menuItem;
        }
    }

    static void receptionUserMenu() {

        System.out.println(INFO_COLOR + "Straight to the rececption menu (no login or security check for now)" + RESET_COLOR);

        ReceptionMenuItem userMenuChoice;

        System.out.println("");

        do {
            // Show the menu choices, and get a valid choice from the user
            userMenuChoice = getReceptionMenuChoice("What do you want to do? ");

            switch (userMenuChoice) {
                case RECEPTION_MENU_____STORE_GUEST:
                    System.out.println(TODO_COLOR + "TODO: Handle storing guest data"+ RESET_COLOR);
                    break;

                case RECEPTION_MENU____CHANGE_GUEST:
                    System.out.println(TODO_COLOR + "TODO: Handle changing guest data"+ RESET_COLOR);
                    break;

                case RECEPTION_MENU____SEARCH_GUEST:
                    System.out.println(TODO_COLOR + "TODO: Handle searching for guest data"+ RESET_COLOR);
                    SQLManagement.searchSpecifikGuestData();
                    break;

                case RECEPTION_MENU____DELETE_GUEST:
                case RECEPTION_MENU_______BOOK_ROOM:
                    String theGuestString= "the Guest";
                    String theGuestString2= "the Guests";
                    GuestUser.bookRoom(theGuestString,theGuestString2);
                    break;
                case RECEPTION_MENU____UPGRADE_ROOM:
                     upGradeRoomMethod();
                     break;
                case RECEPTION_MENU______ORDER_FOOD:
                    String theGuestString1="the guest";
                    GuestUser.orderFoodForTheRoom(theGuestString1);
                case GUEST_MENU________CHECKOUT:
                    if (checkThatThereAreGuestsThatCanCheckOut()==true) {
                        try {
                            GuestUser.checkOutprint();
                            
                    }catch (IOException e){
                            System.err.println("I/O exception error occured");
                    }
                    }else{
                        System.err.println("No Guests to CheckOut");
                    }
                    break;
                

                case RECEPTION_MENU_____HIDDEN_TEST:
                    System.out.println(TODO_COLOR + "You found the HIDDEN MENU CHOICE - TODO: Handle this");
                    break;

                case RECEPTION_MENU__EXIT_RECEPTION:
                    System.out.println("Exiting from the reception menu");
                    break;

                default: {
                    System.out.println(ERROR_COLOR + "Unexpected error, should never end up here after the shape checks, missing case in switch?" + RESET_COLOR);
                }
            }

            System.out.println("");

        } while (userMenuChoice != ReceptionMenuItem.RECEPTION_MENU__EXIT_RECEPTION);
    }
       

    // Show the menu choices, and get a valid choice from the user
    private static ReceptionMenuItem getReceptionMenuChoice(String prompt) {
        String choiceStr;
        ReceptionMenuItem userMenuChoice;

        System.out.println("---- Reception menu: ----" + MENU_COLOR);

        // Loop over all menu choices in the enum, and print the "menu choice texts" for the enabled & non-hidden ones
        for (ReceptionMenuItem value : ReceptionMenuItem.values()) {
            if (value.isEnabledMenuChoice() && !value.ishiddenMenuChoice()) {
                // Get hold of the menu chioce character, in string form
                choiceStr = ((Character) value.getMenuChoiceChar()).toString();

                System.out.println(CHOICE_COLOR + value.getMenuChoiceChar() + MENU_COLOR + ": "
                        + value.getMenuChoiceText().replaceFirst(choiceStr, CHOICE_COLOR + choiceStr + MENU_COLOR));
            }
        }

        do {  // loop until a valid choice has been read

            System.out.print(RESET_COLOR + prompt);

            // Try to read an menu choice integer from the console
            choiceStr = SCANNER.nextLine();
            // Using the first character as the user input
            userMenuChoice = (choiceStr.length() > 0) ? ReceptionMenuItem.values()[0].getReceptionMenuItem(choiceStr.charAt(0)) : null;

            if (userMenuChoice == null) {
                System.out.println(ERROR_COLOR + "Not a valid choice. " + RESET_COLOR + "Try again!");

            }

        } while (userMenuChoice == null); // Loop as long as we haven't got a valid choice

        return userMenuChoice;
    }

    private static void upGradeRoomMethod(){
        boolean roomHasGuest=false;
        List<Room>cointainsGuestsList=new ArrayList<>();
        System.out.println("--What room do you want to uppgrade/change--");
        cointainsGuestsList = HotelManagementSystem.allRoomsList.stream().filter(e -> e.guest != null).collect(Collectors.toList());
        //cointainsGuestsList=HotelManagementSystem.allRoomsList.stream().filter(e->e.guest!=null).forEach(e->System.out.println(e.getRoomNr()+" "+e.getName()+" "+e.guest.getFirstName()+"       "+e.guest.getLastName()));
        cointainsGuestsList.stream().filter(e -> e.guest != null).forEach(e -> System.out.println(e.getRoomNr() + " " + e.getName() + "       " + e.guest.getFirstName() + " " + e.guest.getLastName()));

        int choice = Input.getUserInputInt();

        for (Room room : cointainsGuestsList) {
            if (room.getRoomNr() == choice) {
                roomHasGuest = true;
            }

        }

        if (roomHasGuest==true) {
            whatRoomToChangeTo(choice);
        }else{
            System.out.println("No one in that room");
        }
        
    }
    private static void whatRoomToChangeTo(int choice){
        boolean okToChange=false;
        Guest guestToMove=new Guest();
        List<Room>availableToChangeToList=new ArrayList<>();
        System.out.println("--What new room do you want to change the guest to?--");
        HotelManagementSystem.allRoomsList.stream().filter(e->e.guest==null).forEach(System.out::println);
        availableToChangeToList=HotelManagementSystem.allRoomsList.stream().filter(e->e.guest==null).collect((Collectors.toList()));
        int newRoomNumber=Input.getUserInputInt();
        
        for (Room room : availableToChangeToList) {
            if(room.getRoomNr()==newRoomNumber){
                okToChange=true;
            }
        }
        if (okToChange==true) {
            
            for (Room room : HotelManagementSystem.allRoomsList) {
                if (room.getRoomNr()==choice) {
                    guestToMove=room.guest;
                    room.setGuest(null);
                }
            }
            for (Room room : HotelManagementSystem.allRoomsList) {
                if (room.getRoomNr()==newRoomNumber){
                    room.setGuest(guestToMove);
                }
            }
            for (Food food : HotelManagementSystem.foodList) {
                if (food.getRoomNr()==choice) {
                    food.setRoomNr(newRoomNumber);
                    
                }
            }
            System.out.println("--Guest moved from room "+choice+" to room "+newRoomNumber+"--");
        }else {
            System.err.println("--Can not change to that room--");
        }
        
    }
     public static void updateAndDeleteData(){
       Scanner scan = new Scanner(System.in);
       System.out.println("Would you like to delete or update data?");
       System.out.println("1.Delete");
       System.out.println("2.update");
       int a = scan.nextInt();
       switch(a){
           case 1:
               SQLManagement.deleteGuestData();
               break;
           case 2:
               SQLManagement.updateGuestData();
               break;
           default:
               System.out.println("Not a valid choice");
               break;
               
               
       }
   }
}
