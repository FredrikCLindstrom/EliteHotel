package Main;

import java.util.Scanner;

public class HotelManagementSystem {

    public static final String MENU_COLOR = Misc.CYAN;
    public static final String CHOICE_COLOR = Misc.YELLOW;
    public static final String ERROR_COLOR = Misc.RED;
    public static final String INFO_COLOR = Misc.GREEN;
    public static final String RESET_COLOR = Misc.RESET; // Normally white color

    private final static Scanner SCANNER = new Scanner(System.in);

    public enum MainMenuItem {
        // parameters menuMAIN_MENU_DISPLAY_ROOMS__Char, menuChoiceText, enabledMenuChoice, hiddenMenuChoice
        MAIN_MENU______GUEST_MENU('G', "Guest view", true, false),
        MAIN_MENU_RECEPTION_LOGIN('R', "Reception view", true, false),
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

        //Hotel thisHotel = new Hotel();
        UseAsGuestOrReceptionist();

    }

    static void UseAsGuestOrReceptionist() {

        MainMenuItem userMenuChoice;

        System.out.println("");

        do {
            // Show the menu choices, and get a valid choice from the user
            userMenuChoice = getMainMenuChoice("What do you want to do? ");

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
                    System.out.println("You found the HIDDEN MENU CHOICE - TODO: Handle this");
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

        System.out.println("---- Main menu: ----" + MENU_COLOR);

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

}
