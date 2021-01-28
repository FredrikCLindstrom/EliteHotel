package Main;

import java.util.Scanner;

public class ReceptionUser {

    private final static Scanner SCANNER = new Scanner(System.in);

    // Console color strings, bright versions: https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html  
    public static final String RESET = "\u001b[0m";
    public static final String GREY = "\u001b[30;1m";
    public static final String RED = "\u001b[31;1m";
    public static final String GREEN = "\u001b[32;1m";
    public static final String YELLOW = "\u001b[33;1m";
    public static final String BLUE = "\u001b[34;1m";
    public static final String MAGENTA = "\u001b[35;1m";
    public static final String CYAN = "\u001b[36;1m";
    public static final String WHITE = "\u001b[37;1m";

    public enum ReceptionMenuItem {
        // parameters menuChar, menuChoiceText, enabledMenyChoice, hiddenMenyChoice
        RECEPTION_MENU_STORE_CUSTOMER_('1', "Store customer data",      true, false),
        RECEPTION_MENU_SEARCH_CUSTOMER('2', "Searching customer data",  true, false),
        RECEPTION_MENU_CHANGE_CUSTOMER('C', "Change customer data", true, false),
        RECEPTION_MENU_DELETE_CUSTOMER('D', "Delete customer data", true, false),
        RECEPTION_MENU_BOOK_ROOM______('B', "Book a room", true, false),
        RECEPTION_MENU_UPGRADE_ROOM___('U', "Upgrade a room", true, false),
        RECEPTION_MENU_ORDER_FOOD_____('F', "Order food", true, false),
        RECEPTION_MENU_HIDDEN_TEST____('!', "HIDDEN CHOICE, NOT SHOWN", true, true),
        RECEPTION_MENU_EXIT_RECEPTION_('X', "Exit reception menu", true, false);

        private final String menyText;
        private final char menyChar;
        private boolean enabledMenyChoice;
        private boolean hiddenMenyChoice;

        ReceptionMenuItem(char menuCh, String menuChoiceText, boolean enabledMenyChoice, boolean hiddenMenyChoice) {
            this.menyChar = menuCh;
            this.menyText = menuChoiceText;
            this.enabledMenyChoice = enabledMenyChoice;
            this.hiddenMenyChoice = hiddenMenyChoice;
        }

        public char getMenyChoiceChar() {
            return menyChar;
        }

        public String getMenuChoiceText() {
            return menyText;
        }

        public void setEnabledMenyChoice(boolean enabledMenyChoice) {
            this.enabledMenyChoice = enabledMenyChoice;
        }

        public void setHiddenMenyChoice(boolean hiddenMenyChoice) {
            this.hiddenMenyChoice = hiddenMenyChoice;
        }

        public boolean isEnabledMenyChoice() {
            return enabledMenyChoice;
        }

        public boolean ishiddenMenyChoice() {
            return hiddenMenyChoice;
        }

        public ReceptionMenuItem getReceptionMenuItem(char menuCh) {
            ReceptionMenuItem menuItem = null;

            // Loop over all the meny choises, and return the right (first) item that is enabled and has the matching character
            for (ReceptionMenuItem value : ReceptionMenuItem.values()) {
                if (value.isEnabledMenyChoice() && (menuCh == value.getMenyChoiceChar()) || Character.toLowerCase(menuCh) == Character.toLowerCase(value.getMenyChoiceChar())) {
                    menuItem = value;
                    return menuItem;
                }
            }
            return menuItem;
        }
    }

    static void receptionUserMenu() {
        ReceptionMenuItem receptionMenuChoice;

        //System.out.println("Playing the game...");
        System.out.println("");

        do {
            // Show the menu choices, and get a valid choice from the user
            receptionMenuChoice = getReceptionMenuChoice("What do you want to do? ");

            switch (receptionMenuChoice) {
                case RECEPTION_MENU_STORE_CUSTOMER_:
                    System.out.println("TODO: Handle storing customer data");
                    break;

                case RECEPTION_MENU_CHANGE_CUSTOMER:
                    System.out.println("TODO: Handle changing customer data");
                    break;

                case RECEPTION_MENU_SEARCH_CUSTOMER:
                    System.out.println("TODO: Handle searching for customer data");
                    break;

                case RECEPTION_MENU_DELETE_CUSTOMER:
                case RECEPTION_MENU_BOOK_ROOM______:
                case RECEPTION_MENU_UPGRADE_ROOM___:
                case RECEPTION_MENU_ORDER_FOOD_____:

                    System.out.println("TODO: Handle choice " + receptionMenuChoice.getMenyChoiceChar());
                    break;

                case RECEPTION_MENU_HIDDEN_TEST____:
                    System.out.println("You found the HIDDEN MENU CHOICE - TODO: Handle this");
                    break;

                case RECEPTION_MENU_EXIT_RECEPTION_:
                    System.out.println("The program is shutting down");
                    break;

                default: {
                    System.out.println(RED + "Unexpected error, should never end up here after the shape checks, missing case in switch?" + RESET);
                }
            }

            System.out.println("");

        } while (receptionMenuChoice != ReceptionMenuItem.RECEPTION_MENU_EXIT_RECEPTION_);
    }

    // Show the menu choices, and get a valid choice from the user
    private static ReceptionMenuItem getReceptionMenuChoice(String prompt) {
        String choiceStr;
        ReceptionMenuItem userMenuChoice;

        System.out.println("Receptionation menu:" + YELLOW);

        // Loop over all meny choices in the enum, and print the "meny choice texts" for the enabled & non-hidden ones
        for (ReceptionMenuItem value : ReceptionMenuItem.values()) {
            if (value.isEnabledMenyChoice() && !value.ishiddenMenyChoice()) {
                System.out.println(YELLOW + value.getMenyChoiceChar() + ": " + value.getMenuChoiceText());
            }
        }

        do {  // loop until a valid choice has been read

            System.out.print(RESET + prompt);

            // Try to read an menu choice integer from the console
            choiceStr = SCANNER.nextLine();
            // Using the first character as the user input
            userMenuChoice = (choiceStr.length() > 0) ? ReceptionMenuItem.values()[0].getReceptionMenuItem(choiceStr.charAt(0)) : null;

            if (userMenuChoice == null) {
                System.out.println(RED + "Not a valid choice. " + "Try again!" + RESET);

            }

        } while (userMenuChoice == null); // Loop as long as we haven't got a valid choice

        System.out.println("");
        return userMenuChoice;
    }

}
