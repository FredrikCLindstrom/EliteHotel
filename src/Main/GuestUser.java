package Main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

public class GuestUser {

    public static final String MENU_COLOR = Misc.CYAN;
    public static final String CHOICE_COLOR = Misc.YELLOW;
    public static final String ERROR_COLOR = Misc.RED;
    public static final String INFO_COLOR = Misc.GREEN;
    public static final String TODO_COLOR = Misc.MAGENTA;
    public static final String RESET_COLOR = Misc.RESET; // Normally white color

    private final static Scanner SCANNER = new Scanner(System.in);

    public enum GuestMenuItem {
        // parameters menuChar, menuChoiceText, enabledMenuChoice, hiddenMenuChoice
        GUEST_MENU___DISPLAY_ROOMS('D', "Display room details", true, false),
        GUEST_MENU_AVAILABLE_ROOMS('A', "Display room Availability", true, false),
        GUEST_MENU_______BOOK_ROOM('B', "Book room", true, false),
        GUEST_MENU______ORDER_FOOD('F', "Order Food", true, false),
        GUEST_MENU________CHECKOUT('C', "Checkout", true, false),
        GUEST_MENU___HIDDEN_CHOICE('@', "HIDDEN CHOICE, NOT SHOWN", true, true),
        GUEST_MENU__EXIT_THIS_MENU('X', "EXit guest menu", true, false);

        private final String menuText;
        private final char menuChar;
        private boolean enabledMenuChoice;
        private boolean hiddenMenuChoice;

        GuestMenuItem(char menuCh, String menuChoiceText, boolean enabledMenuChoice, boolean hiddenMenuChoice) {
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

        public GuestMenuItem getGuestMenuItem(char menuCh) {
            GuestMenuItem menuItem = null;

            // Loop over all the menu choises, and return the right (first) item that is enabled and has the matching character
            for (GuestMenuItem value : GuestMenuItem.values()) {
                if (value.isEnabledMenuChoice() && (menuCh == value.getMenuChoiceChar()) || Character.toLowerCase(menuCh) == Character.toLowerCase(value.getMenuChoiceChar())) {
                    menuItem = value;
                    return menuItem;
                }
            }
            return menuItem;
        }
    }

    static void guestUserMenu() {

        GuestMenuItem userMenuChoice;

        System.out.println("");

        do {
            // Show the menu choices, and get a valid choice from the user
            userMenuChoice = getGuestMenuChoice(Misc.GREEN + "Your choice: " + Misc.RESET);
            System.out.println("");
            
            switch (userMenuChoice) {
                case GUEST_MENU___DISPLAY_ROOMS:

                    diffrentTypesOfRoomsDescription();
                    break;

                case GUEST_MENU_AVAILABLE_ROOMS:
                    emptyRooms();
                    break;
                case GUEST_MENU_______BOOK_ROOM:
                    String youString = "you";
                    String yourString = "your";

                    bookRoom(youString, yourString);
                    break;
                case GUEST_MENU______ORDER_FOOD:
                    String youString2 = "you";
                    if (checkThatThereAreGuestsThatCanCheckOut() == true) {
                        try {
                            orderFoodForTheRoom(youString2);
                        } catch (Exception e) {
                            System.err.println("food error occured");
                        }
                    } else {
                        System.err.println("No Guests in rooms to to order");
                    }
                    break;
                case GUEST_MENU________CHECKOUT:
                    if (checkThatThereAreGuestsThatCanCheckOut() == true) {
                        try {
                            checkOutprint();
                        } catch (IOException e) {
                            System.err.println("I/O exception error occured");
                        }
                    } else {
                        System.err.println("No Guests to CheckOut");
                    }

                    break;

                case GUEST_MENU___HIDDEN_CHOICE:
                    System.out.println(TODO_COLOR + "You found the HIDDEN MENU CHOICE - TODO: Handle this" + RESET_COLOR);
                    break;

                case GUEST_MENU__EXIT_THIS_MENU:
                    System.out.println("Exiting from the guest menu");
                    break;

                default: {
                    System.out.println(ERROR_COLOR + "Unexpected error, should never end up here after the shape checks, missing case in switch?" + RESET_COLOR);
                }
            }

            System.out.println("");

        } while (userMenuChoice != GuestMenuItem.GUEST_MENU__EXIT_THIS_MENU);
    }

    // Show the menu choices, and get a valid choice from the user
    private static GuestMenuItem getGuestMenuChoice(String prompt) {
        String choiceStr;
        GuestMenuItem userMenuChoice;

        System.out.println(RESET_COLOR + "--- Guest menu: ---" + MENU_COLOR);

        // Loop over all menu choices in the enum, and print the "menu choice texts" for the enabled & non-hidden ones
        for (GuestMenuItem value : GuestMenuItem.values()) {
            if (value.isEnabledMenuChoice() && !value.ishiddenMenuChoice()) {
                // Get hold of the menu choice character, in string form
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
            userMenuChoice = (choiceStr.length() > 0) ? GuestMenuItem.values()[0].getGuestMenuItem(choiceStr.charAt(0)) : null;

            if (userMenuChoice == null) {
                System.out.println(ERROR_COLOR + "Not a valid choice. " + RESET_COLOR + "Try again!");

            }

        } while (userMenuChoice == null); // Loop as long as we haven't got a valid choice

        return userMenuChoice;
    }

    private static void ShowAllRooms() {
        System.out.println(Misc.GREEN + "ALL ROOMS" + Misc.RESET);
        HotelManagementSystem.allRoomsList.stream().forEach(e -> System.out.println(e.toString()));
    }

    private static void diffrentTypesOfRoomsDescription() {
        System.out.println(Misc.GREEN + "DIFFRENT TYPE OF ROOMS" + Misc.RESET);
        System.out.println(Misc.GREEN + "*************************************************************" + Misc.GREEN + "\n");
        HotelManagementSystem.diffrentTypeOfRoomsList.stream().forEach(e -> System.out.println(e.descriptionOfRooms() + "\n"));
        System.out.println(Misc.GREEN + "*************************************************************" + Misc.GREEN);
    }

    private static void emptyRooms() {
        System.out.println(Misc.GREEN + "EMPTY ROOMS" + Misc.RESET);
        HotelManagementSystem.emptyRoomsList.clear();
        HotelManagementSystem.emptyRoomsList
                = HotelManagementSystem.allRoomsList.
                        stream().
                        filter(e -> e.guest == null).
                        collect(toList());

        HotelManagementSystem.emptyRoomsList.stream().forEach(System.out::println);
    }

    public static void bookRoom(String eitherGuestOrYou, String eitherGuestsOrYour) {
        List<Integer> listNonOccupiedRooms = new ArrayList<>();
        int roomNumberChoice;

        do {

            listNonOccupiedRooms = availableRoomsWithRoomNumber();

            roomNumberChoice = chooseRoomNumberToBook(eitherGuestOrYou);

            if (roomNumberChoice == 0) {
                return;
            }
            if (!listNonOccupiedRooms.contains(roomNumberChoice)) {
                System.err.println("Room is not available. \nPress <enter>");
                SCANNER.nextLine();
            }
        } while (!listNonOccupiedRooms.contains(roomNumberChoice));

        createGuestAndAddToRoom(roomNumberChoice, eitherGuestOrYou, eitherGuestsOrYour);
        

    }

    private static List<Integer> availableRoomsWithRoomNumber() {

        String[] MAP_NR_TO_SPELLED_OUT_STRING = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve"};

        List<Class> roomTypeArrayList = new ArrayList<>();
        roomTypeArrayList.add(StandardSingleRoom.class);
        roomTypeArrayList.add(StandardDoubleRoom.class);
        roomTypeArrayList.add(LuxurySingleRoom.class);
        roomTypeArrayList.add(LuxuryDoubleRoom.class);
        List<Integer> newListOfAvailRoomNrsOfCertainClass = new ArrayList<Integer>();
        List<Room> newListOfAvailRoomsOfCertainClassObjects = new ArrayList<Room>();
        List<Integer> listToReturnAllNonOccupied = new ArrayList<Integer>();

        // Show the hotel floorplan, with the available ones in green
        Building.drawPlan(HotelManagementSystem.allRoomsList, true, false); // list of riomms, markAvailability = true, reception = false 

        System.out.println(Misc.GREEN + "-- Available rooms: (recommended rooms are shown in" + Misc.YELLOW + " yellow/gold" + Misc.GREEN + ") --" + Misc.RESET);
        for (Class thisClass : roomTypeArrayList) {

            /*
            long availableNumberOfRooms = HotelManagementSystem.allRoomsList.stream().
                    filter(e -> e.guest == null).
                    filter(e -> e.getClass().equals(thisClass)).count(); 
          
            newListOfAvailRoomsOfCertainClass = HotelManagementSystem.allRoomsList.stream().
            
                   filter(e -> e.guest == null).
                   filter(e -> e.getClass().equals(thisClass)).map(e -> e.getRoomNr()).collect(toList());
            newListOfAvailRoomsOfCertainClassObjects = HotelManagementSystem.allRoomsList.stream().
                    filter(e -> e.guest == null).
                    filter(e -> e.getClass().equals(thisClass)).collect(toList());
             */
            // Making a list of available rooms of correct class
            newListOfAvailRoomsOfCertainClassObjects = HotelManagementSystem.allRoomsList.stream().
                    filter(e -> e.guest == null).
                    filter(e -> e.getClass().equals(thisClass)). // Could be done with one filter: filter(e -> (e.guest == null && e.getClass().equals(thisClass)));
                    collect(toList());

            long availableNumberOfRooms = newListOfAvailRoomsOfCertainClassObjects.stream().count(); // Could be done with newListOfAvailRoomsOfCertainClassObjects.size();

            // Did we find any rooms of the class? 
            if (!newListOfAvailRoomsOfCertainClassObjects.isEmpty()) {
                // Fouond some, make a list with just the available room numbers for this class
                newListOfAvailRoomNrsOfCertainClass = newListOfAvailRoomsOfCertainClassObjects.stream().map(e -> e.getRoomNr()).collect(toList());

                Room bestRoom = Ranking.highestRanked(newListOfAvailRoomsOfCertainClassObjects); // Whar room can we recommend of thise?
                String bedRoomName = thisClass.getTypeName();
                String useThisClassNameForPrintOut = bedRoomName.substring(5);

                System.out.println(Misc.RESET + MAP_NR_TO_SPELLED_OUT_STRING[(int) availableNumberOfRooms] + " " + useThisClassNameForPrintOut
                        + ((newListOfAvailRoomsOfCertainClassObjects.size() == 1) ? " is " : "s are ")
                        + "available:"
                        + ((newListOfAvailRoomsOfCertainClassObjects.isEmpty()) ? ", unfortunately" : ""));

                if (!newListOfAvailRoomsOfCertainClassObjects.isEmpty()) {
                    // There are some rooms, list all of them;
                    for (Room room: newListOfAvailRoomsOfCertainClassObjects) {
                        if (room.equals(bestRoom)) {
                            System.out.println(Misc.YELLOW + "  Room " + room.roomNr + Misc.RESET);
                        } else {
                            System.out.println(Misc.GREEN + "  Room " + room.roomNr + Misc.RESET);
                        }
                    }
                } else {
                    System.out.println("  (No rooms of this class are available at the moment)"); // Not applicable when only writing when !isEmpty
                }
            }

            listToReturnAllNonOccupied.addAll(newListOfAvailRoomNrsOfCertainClass);
        }
        return listToReturnAllNonOccupied;
    }

    public static void addSomePeopleToRooms() { //TODO: REMOVE, just testing with this  
        Guest testGuest1 = new Guest("Hasse", "Olofsson", 2, "0706609034");
        Guest testGuest2 = new Guest("Maja", "Kennethsson", 5, "0706609035");
        Guest testGuest3 = new Guest("Samuel", "Larsson", 2, "0706609036");

        for (Room room : HotelManagementSystem.allRoomsList) {
            if (room.roomNr == 2) {
                room.setGuest(testGuest1);
            }
            if (room.roomNr == 5) {
                room.setGuest(testGuest2);
            }
            if (room.roomNr == 12) {
                room.setGuest(testGuest3);
            }
        }
    }

    private static int chooseRoomNumberToBook(String eitherGuestOrYou) {
        //List<Room>availableToChangeToList=HotelManagementSystem.allRoomsList.stream().filter(e->e.guest==null).collect((Collectors.toList()));
        //Misc.printDebug(RESET_COLOR+availableToChangeToList.toString());
        //Room bestroom=Ranking.highestRanked(availableToChangeToList);
        //System.out.println(Misc.CYAN+"Suggested available room is: "+bestroom+Misc.RESET);
        System.out.print(Misc.GREEN + "What room number would " + eitherGuestOrYou + " like to book? (0 to cancel): " + Misc.RESET);
        int choice = Input.getUserInputInt();

        return choice;
    }

    private static void createGuestAndAddToRoom(int roomChoice, String eitherGuestOrYou, String eitherGuestsOrYour) {

        System.out.print(Misc.GREEN + "Enter " + eitherGuestsOrYour + " first Name: " + Misc.RESET);
        String firstName = Input.getUserInputString();
        System.out.print(Misc.GREEN + "Enter " + eitherGuestsOrYour + " last Name: " + Misc.RESET);
        String lastName = Input.getUserInputString();
        System.out.print(Misc.GREEN + "Please enter a phone number: " + Misc.RESET);
        String phoneNr = Input.getUserInputString();
        System.out.print(Misc.GREEN + "How many Nights do " + eitherGuestOrYou + " want to stay?: " + Misc.RESET);
        int numberOfNights = Input.getUserInputInt();

        LocalDate today = LocalDate.now();
        LocalDate checkOut = today.plusDays(numberOfNights);

        System.out.println(Misc.GREEN + "Guest :" + firstName + " " + lastName + ", check in: " + today + " check out : " + checkOut + Misc.RESET);

        Guest guestCreate = new Guest(firstName, lastName, numberOfNights, phoneNr);
        HotelManagementSystem.allRoomsList.stream().filter(e -> e.getRoomNr() == (roomChoice)).forEach(e -> e.setGuest(guestCreate));

        int guestId = guestCreate.getGuestId();

        SQLManagement.guesstDataToDb(guestId, firstName, lastName, numberOfNights, phoneNr);

    }

    public static void checkOutprint() throws IOException {
        boolean roomHasGuest = false;
        int choice = 0;
        System.out.println(Misc.GREEN + "--- CHECKOUT SECTION ---" + Misc.RESET);
        HotelManagementSystem.allRoomsList.stream().filter(e -> e.getGuest() != null).
                forEach(e -> System.out.println(e.roomNr + ": " + e.guest.getFirstName() + " " + e.guest.getLastName()));

        while (roomHasGuest == false) {

            System.out.print(Misc.GREEN + "What room number do you wish to check out? (0 to cancel): " + Misc.RESET);
            List<Integer> occupiedRoomsCheckoutCheck = new ArrayList<>();

            for (Room room : HotelManagementSystem.allRoomsList) {
                if (room.guest != null) {
                    occupiedRoomsCheckoutCheck.add(room.getRoomNr());
                }
            }

            choice = Input.getUserInputInt();

            if (choice == 0) {
                return;
            }

            if (occupiedRoomsCheckoutCheck.contains(choice)) {
                roomHasGuest = true;
            } else {
                roomHasGuest = false;
                System.err.println("Room can NOT be checked out, try again please");
            }
        }

        int toBillGuest = FileManagement.printToTextDoc(choice); //this is the method to be called on checkout. 
        
        System.out.println(Misc.GREEN + "Total bill: " + toBillGuest + ", see receipt for details");
    }

    public static void addSomeFodTestMethod() {
        Food food1 = new Sandwich(12);
        Food food2 = new Soda(12);
        HotelManagementSystem.foodList.add(food1);
        HotelManagementSystem.foodList.add(food2);
    }

    public static boolean checkThatThereAreGuestsThatCanCheckOut() {
        long numberOfGuests = 0;
        boolean moreThanZeroGuests = false;
        numberOfGuests = HotelManagementSystem.allRoomsList.stream().filter(e -> e.guest != null).count();

        if (numberOfGuests > 0) {
            moreThanZeroGuests = true;
        }
        return moreThanZeroGuests;
    }

    public static void orderFoodForTheRoom(String YouOrTheGuest) {
        int choice;
        int roomNumber;

        choice = orderWhat(YouOrTheGuest);

        if (choice <= HotelManagementSystem.foodMenu.size()) {
            roomNumber = enterRoomNUmberForFood();

            switch (choice) {
                case 1:
                    Food soda = new Soda(roomNumber);
                    HotelManagementSystem.foodList.add(soda);
                    System.out.println(Misc.GREEN + "-- Soda ordered --" + Misc.RESET);
                    break;
                case 2:
                    Food sandwich = new Sandwich(roomNumber);
                    HotelManagementSystem.foodList.add(sandwich);
                    System.out.println(Misc.GREEN + "-- Sandwich ordered --" + Misc.RESET);
                    break;
                case 3:
                    Food noodles = new Noodles(roomNumber);
                    HotelManagementSystem.foodList.add(noodles);
                    System.out.println(Misc.GREEN + "-- Noodles ordered --" + Misc.RESET);
                    break;//add case 4 if added food to menu
                default:
                    break;
            }

        } else {
            System.out.println(Misc.GREEN + "Ok, nothing ordered" + Misc.RESET);
        }
    }

    public static int orderWhat(String YouOrTheGuest) {
        int num = 1;
        System.out.println(Misc.GREEN + "Available menu choices for " + YouOrTheGuest + " (with the chefs recommendation in" + Misc.YELLOW + " yellow" + Misc.GREEN + "): " + Misc.RESET);

        Food bestFood = Ranking.highestRanked(HotelManagementSystem.foodMenu);

        for (Food food : HotelManagementSystem.foodMenu) {
            // Write each menu row, with the color changed to Yellow for the highestRanked one
            System.out.println(CHOICE_COLOR + num + (food.equals(bestFood) ? Misc.YELLOW : MENU_COLOR) + ": " + food.forReceiptPrintOut() + Misc.RESET);
            num++;
        }
        System.out.println(CHOICE_COLOR + num + MENU_COLOR + ": Go Back" + Misc.RESET);
        
        System.out.print(Misc.GREEN + "Choice: " + Misc.RESET);
        int choice = Input.getUserInputInt();
        
        return choice;
    }

    public static int enterRoomNUmberForFood() {
        boolean roomHasGuest = false;
        boolean roomExistInlist = false;
        int roomNumber = 0;

        while (roomHasGuest == false) {

            System.out.print(Misc.GREEN + "Please enter room number: " + Misc.RESET);
            roomNumber = Input.getUserInputInt();
            roomExistInlist = false;   // Added for the case afer an empty room as been chosen, and then a nonexistent room, in which case no error message would have been written

            for (Room room : HotelManagementSystem.allRoomsList) {
                if (room.getRoomNr() == roomNumber) {
                    roomExistInlist = true;
                    if (room.guest != null) {
                        System.out.println(Misc.GREEN + "Room Number: " + room.getRoomNr() + " " + room.guest.getFirstName() + " " + room.guest.getLastName() + Misc.RESET);
                        roomHasGuest = true;
                    } else {
                        System.err.println("Room Number: " + room.getRoomNr() + " is Empty");
                    }

                }
            }
            if (roomExistInlist == false) {
                System.err.println("Room does not exist");
            }
        }
        return roomNumber;
    }
}
