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
            userMenuChoice = getGuestMenuChoice(Misc.GREEN+"--What do you want to do?-- "+Misc.RESET);

            switch (userMenuChoice) {
                case GUEST_MENU___DISPLAY_ROOMS:
                    
                    diffrentTypesOfRoomsDescription();
                    break;
                    
                case GUEST_MENU_AVAILABLE_ROOMS:
                    emptyRooms();
                    break;
                case GUEST_MENU_______BOOK_ROOM:
                    String youString="you";
                    String yourString="your";
                    
                    bookRoom(youString,yourString);
                    break;
                case GUEST_MENU______ORDER_FOOD:
                    String youString2="you";
                    if (checkThatThereAreGuestsThatCanCheckOut()==true) {
                        try {
                        orderFoodForTheRoom(youString2);
                    } catch (Exception e) {
                            System.err.println("food error occured");
                    }
                    }else{
                        System.err.println("No Guests in rooms to to order");
                    }
                    break;
                case GUEST_MENU________CHECKOUT:
                    if (checkThatThereAreGuestsThatCanCheckOut()==true) {
                        try {
                        checkOutprint();
                    } catch (IOException e) {
                            System.err.println("I/O exception error occured");
                    }
                    }else{
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

        System.out.println("Guest menu:" + MENU_COLOR);

        // Loop over all menu choices in the enum, and print the "menu choice texts" for the enabled & non-hidden ones
        for (GuestMenuItem value : GuestMenuItem.values()) {
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
            userMenuChoice = (choiceStr.length() > 0) ? GuestMenuItem.values()[0].getGuestMenuItem(choiceStr.charAt(0)) : null;

            if (userMenuChoice == null) {
                System.out.println(ERROR_COLOR + "Not a valid choice. " + RESET_COLOR + "Try again!");

            }

        } while (userMenuChoice == null); // Loop as long as we haven't got a valid choice

        return userMenuChoice;
    }

    private static void ShowAllRooms(){
        System.out.println("ALL ROOMS"); 
        HotelManagementSystem.allRoomsList.stream().forEach(e->System.out.println(e.toString()));
    }
    
    private static void diffrentTypesOfRoomsDescription(){
        System.out.println(Misc.GREEN+"DIFFRENT TYPE OF ROOMS"+Misc.RESET);
        System.out.println(Misc.GREEN+"*************************************************************"+Misc.GREEN+"\n");
        HotelManagementSystem.diffrentTypeOfRoomsList.stream().forEach(e->System.out.println(e.descriptionOfRooms()+"\n"));
        System.out.println(Misc.GREEN+"*************************************************************"+Misc.GREEN);
    }
    
    private static void emptyRooms(){
        System.out.println(Misc.GREEN+"EMPTY ROOMS"+Misc.RESET); 
        HotelManagementSystem.emptyRoomsList.clear();
        HotelManagementSystem.emptyRoomsList=
                HotelManagementSystem.allRoomsList.
                stream().
                filter(e->e.guest==null).
                collect(toList());
        
        HotelManagementSystem.emptyRoomsList.stream().forEach(System.out::println);
    }
    
    public static void bookRoom(String eitherGuestOrYou,String eitherGuestsOrYour){
        List<Integer> listNonOccupiedRooms=new ArrayList<>();
        int roomNumberChoice;
        
        do { 
            
            listNonOccupiedRooms=availableSSRoomsWithRoomNumber();
            
            
            
        roomNumberChoice=chooseRoomNumberToBook(eitherGuestOrYou);
            
            if (!listNonOccupiedRooms.contains(roomNumberChoice)) {
                System.err.println("Room is not available");
            }
        } while (!listNonOccupiedRooms.contains(roomNumberChoice));
        
        createGuestAndAddToRoom(roomNumberChoice,eitherGuestOrYou,eitherGuestsOrYour);
        
    }
    
    private static List<Integer> availableSSRoomsWithRoomNumber() {

        List<Class> roomTypeArrayList = new ArrayList<>();
        roomTypeArrayList.add(StandardSingleRoom.class);
        roomTypeArrayList.add(StandardDoubleRoom.class);
        roomTypeArrayList.add(LuxurySingleRoom.class);
        roomTypeArrayList.add(LuxuryDoubleRoom.class);
        List<Integer> newListOfAvailRoomsOfCertainClass = new ArrayList<Integer>();
        List<Integer> listToReturnAllNonOccupied = new ArrayList<Integer>();
        for (Class thisClass : roomTypeArrayList) {

            long availableNumberOfRooms = HotelManagementSystem.allRoomsList.stream().
                    filter(e -> e.guest == null).
                    filter(e -> e.getClass().equals(thisClass)).count();

            newListOfAvailRoomsOfCertainClass = HotelManagementSystem.allRoomsList.stream().
                    filter(e -> e.guest == null).
                    filter(e -> e.getClass().equals(thisClass)).map(e -> e.getRoomNr()).collect(toList());

            String bedRoomName = thisClass.getTypeName();
            String useThisNameForPrintOut = bedRoomName.substring(5);
            
            System.out.println(Misc.GREEN+useThisNameForPrintOut + " available : " + availableNumberOfRooms+Misc.GREEN);

            for (Integer roomNr : newListOfAvailRoomsOfCertainClass) {
                System.out.println("Room Number :" + roomNr);
                
            }
          listToReturnAllNonOccupied.addAll(newListOfAvailRoomsOfCertainClass);
        }
        return listToReturnAllNonOccupied;
    }
    
    public static void addSomePeopleToRooms(){ //TODO: REMOVE, just testing with this
        Guest testGuest1= new Guest("hasse","olofsson",2,"0706609034");
        Guest testGuest2= new Guest("maja","kennethsson",5,"0706609035");
        Guest testGuest3= new Guest("samuel","lavasani",2,"0706609036");
        for (Room room : HotelManagementSystem.allRoomsList) {
            if(room.roomNr==2){
                room.setGuest(testGuest1);
            }
            if(room.roomNr==6){
                room.setGuest(testGuest2);
            }
            if(room.roomNr==14){
                room.setGuest(testGuest3);
            }
        }
    }
    private static int chooseRoomNumberToBook(String eitherGuestOrYou){
        List<Room>availableToChangeToList=HotelManagementSystem.allRoomsList.stream().filter(e->e.guest==null).collect((Collectors.toList()));
        Ranking.highestRanked(availableToChangeToList);
        System.out.println(Misc.GREEN+"What room number would "+eitherGuestOrYou+" like to book?"+Misc.RESET);
        int choice=Input.getUserInputInt();
        
        return choice;
    }
    
    private static void createGuestAndAddToRoom(int roomChoice, String eitherGuestOrYou,String eitherGuestsOrYour){
        
        
        System.out.println("Enter "+eitherGuestsOrYour+" first Name");
        String firstName=Input.getUserInputString();
        System.out.println("Enter "+eitherGuestsOrYour+" last Name");
        String lastName=Input.getUserInputString();
        System.out.println("Please enter a phone number");
        String phoneNr=Input.getUserInputString();
        System.out.println("How many Nights do "+eitherGuestOrYou+" want to stay?");
        int numberOfNights=Input.getUserInputInt();
        
        LocalDate today= LocalDate.now();
        LocalDate checkOut= today.plusDays(numberOfNights);
        
        System.out.println("Guest :"+firstName+" "+lastName+", check in: "+today+" check out : "+checkOut);
        
        Guest guestCreate=new Guest(firstName,lastName,numberOfNights,phoneNr);
        HotelManagementSystem.allRoomsList.stream().filter(e->e.getRoomNr()==(roomChoice)).forEach(e->e.setGuest(guestCreate));
        
        int guestId = guestCreate.getGuestId();
        
        SQLManagement.guesstDataToDb(guestId,firstName, lastName, numberOfNights,phoneNr);
        
    }
    
    public static void checkOutprint() throws IOException {
        boolean roomHasGuest = false;
        int choice=0;
        System.out.println(Misc.GREEN + "---CHECKOUT SECTION---" + Misc.RESET);
        HotelManagementSystem.allRoomsList.stream().filter(e -> e.getGuest() != null).
                forEach(e -> System.out.println(e.roomNr + ": " + e.guest.getFirstName() + " " + e.guest.getLastName()));

        while (roomHasGuest==false) {

            System.out.println("What room number do you wish to check out?");
            List<Integer> occupiedRoomsCheckoutCheck = new ArrayList<>();
            for (Room room : HotelManagementSystem.allRoomsList) {
                if (room.guest != null) {
                    occupiedRoomsCheckoutCheck.add(room.getRoomNr());
                }
            }

            choice = Input.getUserInputInt();

            if (occupiedRoomsCheckoutCheck.contains(choice)) {
                roomHasGuest = true;
            } else {
                roomHasGuest = false;
                System.err.println("Room can NOT be checked out, try again please");
            }
        }
        

        FileManagement.printToTextDoc(choice); //this is the method to be called on checkout. 

    }
    
    public static void addSomeFodTestMethod(){
        Food food1=new Sandwich(14);
        Food food2 = new Soda(14);
        HotelManagementSystem.foodList.add(food1);
        HotelManagementSystem.foodList.add(food2);
        
    }
    public static boolean checkThatThereAreGuestsThatCanCheckOut(){
        long numberOfGuests=0;
        boolean moreThanZeroGuests=false;
        numberOfGuests=HotelManagementSystem.allRoomsList.stream().filter(e->e.guest!=null).count();
        
        if (numberOfGuests>0) {
            moreThanZeroGuests=true;
        }
        return moreThanZeroGuests;
    }
    
    public static void orderFoodForTheRoom(String YouOrTheGuest){
        int choice;
        int roomNumber;

        choice = orderWhat(YouOrTheGuest);

        if (choice <= HotelManagementSystem.foodMenu.size()) {
            roomNumber = enterRoomNUmberForFood();

            switch (choice) {
                case 1:
                    Food soda = new Soda(roomNumber);
                    HotelManagementSystem.foodList.add(soda);
                    break;
                case 2:
                    Food sandwich = new Sandwich(roomNumber);
                    HotelManagementSystem.foodList.add(sandwich);
                    break;
                case 3:
                    Food noodles = new Noodles(roomNumber);
                    HotelManagementSystem.foodList.add(noodles);
                    break;//add case 4 if added food to menu
                default:
                    break;
            }

        }else{
            System.out.println("Ok, nothing ordered");
        }
    }
    
    public static int orderWhat(String YouOrTheGuest){
        int num=1;
        System.out.println("--What would "+YouOrTheGuest+" like to order?--");
        for (Food food : HotelManagementSystem.foodMenu) {
            System.out.println(num+": "+food.forReceiptPrintOut());
            num++;
        }
        
        System.out.println(num+": Go Back");
        int choice=Input.getUserInputInt();
        
        return choice;
    }
    
    public static int enterRoomNUmberForFood() {
        boolean roomHasGuest = false;
        boolean roomExistInlist=false;
        int roomNumber=0;
        
        while (roomHasGuest == false) {

            System.out.println("--please enter room number--");
            roomNumber = Input.getUserInputInt();

            for (Room room : HotelManagementSystem.allRoomsList) {
                if (room.getRoomNr() == roomNumber) {
                    roomExistInlist=true;
                    if (room.guest != null) {
                        System.out.println("Room Number: " + room.getRoomNr() + " " + room.guest.getFirstName() + " " + room.guest.getLastName());
                        roomHasGuest=true;
                    } else {
                        System.err.println("Room Number: " + room.getRoomNr() + " is Empty");
                    }

                } 
            }
            if (roomExistInlist==false) {
                System.err.println("Room does not exist");}
            
        }
        return roomNumber;
    }
}

