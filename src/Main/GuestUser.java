package Main;

import java.util.Scanner;

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
            userMenuChoice = getGuestMenuChoice("What do you want to do? ");

            switch (userMenuChoice) {
                case GUEST_MENU___DISPLAY_ROOMS:
                case GUEST_MENU_AVAILABLE_ROOMS:
                case GUEST_MENU_______BOOK_ROOM:
                case GUEST_MENU______ORDER_FOOD:
                case GUEST_MENU________CHECKOUT:
                    System.out.println(TODO_COLOR + "TODO: Handle choice " + userMenuChoice.getMenuChoiceChar() + ", " + userMenuChoice.getMenuChoiceText() + RESET_COLOR);
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

}
