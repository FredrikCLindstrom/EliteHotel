package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelManagementSystem {

    static List<Room> allRoomsList = new ArrayList<>();
    static List<Room> diffrentTypeOfRoomsList = new ArrayList<>();
    static List<Room> emptyRoomsList = new ArrayList<>();
    static List<Room> testingRoomList = new ArrayList<>();
    static List<Food> foodList = new ArrayList<>();
    static List<Food> foodMenu = new ArrayList<>();

    public static final String MENU_COLOR = Misc.CYAN;
    public static final String CHOICE_COLOR = Misc.YELLOW;
    public static final String ERROR_COLOR = Misc.RED;
    public static final String INFO_COLOR = Misc.GREEN;
    public static final String RESET_COLOR = Misc.RESET; // Normally white color
    public static final String USER_CHOICE_COLOR = Misc.GREEN;

    private final static Scanner SCANNER = new Scanner(System.in);

    public enum MainMenuItem {
        // parameters menuMAIN_MENU_DISPLAY_ROOMS__Char, menuChoiceText, enabledMenuChoice, hiddenMenuChoice
        MAIN_MENU______GUEST_MENU('G', "Guest view", true, false),
        MAIN_MENU_RECEPTION_LOGIN('R', "Reception view", true, false),
        MAIN_MENU_HIDDEN_ADD_DATA('#', "HIDDEN CHOICE, NOT SHOWN", true, true),
        MAIN_MENU_____HIDDEN_TEST('!', "HIDDEN CHOICE, NOT SHOWN", true, true),
        MAIN_MENU____EXIT_PROGRAM('X', "EXit the program", true, false);

        private final String menuText;
        private final char menuChar;
        private boolean enabledMenuChoice;
        private boolean hiddenMenuChoice;

        MainMenuItem(char menuCh, String menuChoiceText, boolean enabledMenuChoice, boolean hiddenMenuChoice) {
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

        public MainMenuItem getMainMenuItem(char menuCh) {
            MainMenuItem menuItem = null;

            // Loop over all the menu choises, and return the right (first) item that is enabled and has the matching character
            for (MainMenuItem value : MainMenuItem.values()) {
                if (value.isEnabledMenuChoice() && (menuCh == value.getMenuChoiceChar()) || Character.toLowerCase(menuCh) == Character.toLowerCase(value.getMenuChoiceChar())) {
                    menuItem = value;
                    return menuItem;
                }
            }
            return menuItem;
        }
    }

    public static void main(String[] args) {
        addMenuItemsToMenu(); // Set up the menu system;

        addRoomsToLists();

        // Adding 3 guests, and some food-orders to one rooom TODO: remove
        GuestUser.addSomePeopleToRooms();
        GuestUser.addSomeFodTestMethod();

        // Show the hotel plan with free rooms in green, and occupied ones in red
        Building.drawPlan(allRoomsList, false, false); // (markAvailability = true, reception = false

        //Hotel thisHotel = new Hotel();
        UseAsGuestOrReceptionist();
    }

    static void UseAsGuestOrReceptionist() {

        MainMenuItem userMenuChoice;

        do {
            // Show the menu choices, and get a valid choice from the user
            userMenuChoice = getMainMenuChoice(Misc.GREEN + "Your choice: " + Misc.RESET);

            switch (userMenuChoice) {

                case MAIN_MENU______GUEST_MENU:
                    // Go to the Guest sub menu
                    GuestUser.guestUserMenu();
                    break;

                case MAIN_MENU_RECEPTION_LOGIN:
                    // Go to the reception sub menu:
                    ReceptionUser.receptionUserMenu();
                    break;

                case MAIN_MENU_____HIDDEN_TEST:
                    System.out.println("You found the HIDDEN MENU CHOICE");
                    Misc.debug = !Misc.debug;
                    System.out.println("DEBUG = " + Misc.debug + " for  Misc.printDebug()");
                    break;

                case MAIN_MENU_HIDDEN_ADD_DATA:
                    System.out.println("Hidden menu choice: Adding 3 guests, and some food on room 12. ");
                    GuestUser.addSomePeopleToRooms();
                    GuestUser.addSomeFodTestMethod();
                    break;

                case MAIN_MENU____EXIT_PROGRAM:
                    System.out.println("Exiting the program");
                    break;

                default: {
                    System.out.println(ERROR_COLOR + "Unexpected error, should never end up here after the shape checks, missing case in switch?" + RESET_COLOR);
                }
            }

        } while (userMenuChoice != MainMenuItem.MAIN_MENU____EXIT_PROGRAM);
    }

    // Show the menu choices, and get a valid choice from the user
    private static MainMenuItem getMainMenuChoice(String prompt) {
        String choiceStr;
        MainMenuItem userMenuChoice;

        System.out.println(Misc.RESET + "==== Main menu: ====" + MENU_COLOR);

        // Loop over all menu choices in the enum, and print the "menu choice texts" for the enabled & non-hidden ones
        for (MainMenuItem value : MainMenuItem.values()) {
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
            userMenuChoice = (choiceStr.length() > 0) ? MainMenuItem.values()[0].getMainMenuItem(choiceStr.charAt(0)) : null;

            if (userMenuChoice == null) {
                System.out.println(ERROR_COLOR + "Not a valid choice. " + RESET_COLOR + "Try again!");

            }

        } while (userMenuChoice == null); // Loop as long as we haven't got a valid choice
        System.out.print(RESET_COLOR);

        return userMenuChoice;
    }

    public static void addRoomsToLists() {
        Room room;

        // Add the six room on the west wing, corresponding to the hotel plan
        room = new StandardDoubleRoom();
        allRoomsList.add(room);
        room = new StandardSingleRoom();
        allRoomsList.add(room);
        diffrentTypeOfRoomsList.add(room);  // Add one of each Room classes in differentTypeOfRoomsList
        room = new StandardDoubleRoom();
        allRoomsList.add(room);
        diffrentTypeOfRoomsList.add(room); // Add one of each Room classes in differentTypeOfRoomsList
        room = new StandardSingleRoom();
        allRoomsList.add(room);
        room = new StandardDoubleRoom();
        allRoomsList.add(room);
        room = new StandardSingleRoom();
        allRoomsList.add(room);

        // Add the six room on the hotels east wing, corresponding to the hotel plan
        room = new LuxurySingleRoom();
        allRoomsList.add(room);
        diffrentTypeOfRoomsList.add(room);  // Add one of each Room classes in differentTypeOfRoomsList
        room = new LuxuryDoubleRoom();
        allRoomsList.add(room);
        diffrentTypeOfRoomsList.add(room);  // Add one of each Room classes in differentTypeOfRoomsList
        room = new LuxurySingleRoom();
        allRoomsList.add(room);
        room = new LuxuryDoubleRoom();
        allRoomsList.add(room);
        room = new LuxurySingleRoom();
        allRoomsList.add(room);
        room = new LuxuryDoubleRoom();
        allRoomsList.add(room);

        // allRoomsList.stream().forEach(a -> System.out.println(a.toString()));
    }

    public static void testingMethod() throws IOException {
        System.out.println("TestMethod 4 rooms, 2 of them");
        String[] firstNames = {"mister", "dennis", "klara", "kenny", "vince"};
        String[] lastNames = {"mistersson", "karlson", "götesson", "bennysson", "hoofs"};
        testingRoomList.addAll(diffrentTypeOfRoomsList);
        int i = 0;
        for (Room room : testingRoomList) {
            i++;
            if (i % 2 == 0) {
                room.guest = new Guest(firstNames[i], lastNames[i], 2, "070");
            }
        }
        testingRoomList.stream().forEach(e -> System.out.println(e.toString()));
        System.out.println("test for occupied rooms");
        testingRoomList.stream().filter(e -> e.guest != null).forEach(e -> System.err.println("Room Number:" + e.roomNr + " " + e.name + " is occupied"));
        //FileManagement.printToTextDoc(testingRoomList.get(3).getGuest());

    }

    private static void addMenuItemsToMenu() {
        Food soda = new Soda();
        Food sandwich = new Sandwich();
        Food noodles = new Noodles();
        foodMenu.add(soda);
        foodMenu.add(sandwich);
        foodMenu.add(noodles);
    }

}


/*
===== HOTEL BOOKING MANAGEMENT PROJECT  ==========
Ghulam Murtaza

--- INTRODUCTION ----------------------------------
Elite Hotel Management Project in Java is designed which can be used to manage activities

 Storing Customer Details
 Searching Customer Details
 Upgrade and delete details
 Booking and upgrading room
 Ordering Food for Particular Room
 Check out for customer and showing bill

 It can also be used to see different room features and room availability.
 It is a menu driven program and it runs until the user exits.
 User defined exception is thrown if the user tries to book an already allotted room.
  Exception handling is properly done to deal with any kind of unexpected exception.

--- ROOM TYPES --------------------------------------
1. Luxury Double Room
2. Deluxe Double Room
3. Luxury Single Room
4. Deluxe Single Room
You are Free to add or remove items in the list.

--- FOOD MENU ---------------------------------------
1. Sandwich .   150 SEK
2. Pasta Rs.    160 SEk
3. Noodles      170 SEK
4 Drink         30 SEK
You are Free to add or remove items in the¨menu.

--- TOPIC COVERED ------------------------------------
 Classes, Interface
 Collections
 Exceptions
 Lambda
 Stream
 Generics
 File Handling

--- INTRODUCTION -------------------------------------
This project is divided into two parts
 First Part is to build a structure.
 Second part is to save data on file or database 
    and read from it and finish the project to present it.

--- GROUP WORK ---------------------------------------
 You can work in a group of maximum three students.

--- UI -----------------------------------------------
 You are free to choose UI for System
 One recomendations is given in next Slides

--- UI FOR CUSTOMER VIEW ------------------------------
Enter your choice :
1. Display room details
2. Display room availability
3. Book
4. Order food
5. Checkout
6. Exit
 1

Choose room type :
1. Luxury Double Room
2. Deluxe Double Room
3. Luxury Single Room
4.Deluxe Single Room
 1

Number of double beds : 1
AC : Yes
Free breakfast : Yes
Charge per day:4000
Continue : (y/n)
 y

Enter your choice :
1.Display room details
2.Display room availability
3.Book
4.Order food
5.Checkout
6.Exit
 2

Choose room type :
1. Luxury Double Room
2.Deluxe Double Room
3. Luxury Single Room
4.Deluxe Single Room
4
Number of rooms available : 20
Continue : (y/n)
 y

Enter your choice :
1. Display room details
2. Display room availability
3. Book
4. Order food
5. Checkout
6. Exit
 3

Choose room type :
1. Luxury Double Room
2. Deluxe Double Room
3. Luxury Single Room
4.Deluxe Single Room
 1

Choose room number from :
1,2,3,4,5,6,7,8,9,10,
Enter room number: 9
Enter customer name: Ghulam Murtaza
Enter contact number: 120 411 8730

Enter your choice :
1. Storing Customer Details
2. Searching Customer Details
3. Upgrade and delete details
4. Booking or upgrading room
5. Ordering Food for Particular Room
6. Check out for customer and showing bill

--- GRADE CRITERIA ------------------------------------------
(old) For ”G” You have to cover minimum
(old) objektive for course.
(old)  Classes
(old)  Collections or Arrays
(old)  Exceptions
(old)  Java 8 features (Lambda, Stream, Method Reference)
(old)  Nr ”VG” You have to cover some extra topics like generic methods or generic classes.
(old)  Some Extra feature to make your user interface secure and maintainable like
(old)     save customer data in both file and database.ext Level I/O hantering with Files or database.
(old)  For ”VG” You have to cover some extra topics like generic methods or generic classes.
(old)  Some Extra feature to make your user interface secure and maintainable like
(old)     save customer data in both file and database.

New:
=====
For ”G” You have to cover minimum
objektive for course.
 Classes
 Collections or Arrays
 Exceptions
 Java 8 features (Lambda, Stream, Method Reference)
 Next Level I/O hantering with Files or database.

 For ”VG” You have to cover some extra topics like generic methods or generic classes.
 Some Extra feature to make your user interface secure and maintainable like
    save customer data in both file and database.


--- DEADLINE -----------------------------------------------
 First Presentation 08 Feb 2021
 Second Presentation 09 Feb 2021
 Late Submission only Get ”G” and dedline for late submission will follow soon.

--- HOW TO SUBMIT? -----------------------------------------
 You have to present your code on teams
 Two chances to present.

 Lycka till

Copyright(2021) Ghulam Murtaza
 */
