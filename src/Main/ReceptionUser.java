package Main;

import java.util.Scanner;

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
        RECEPTION_MENU____CHANGE_GUEST('C', "Change guest data", true, false),
        RECEPTION_MENU____DELETE_GUEST('D', "Delete guest data", true, false),
        RECEPTION_MENU_______BOOK_ROOM('B', "Book a room", true, false),
        RECEPTION_MENU____UPGRADE_ROOM('U', "Upgrade a room", true, false),
        RECEPTION_MENU______ORDER_FOOD('F', "Order Food", true, false),
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
                    break;

                case RECEPTION_MENU____DELETE_GUEST:
                case RECEPTION_MENU_______BOOK_ROOM:
                case RECEPTION_MENU____UPGRADE_ROOM:
                case RECEPTION_MENU______ORDER_FOOD:
                    System.out.println(TODO_COLOR + "TODO: Handle choice " + userMenuChoice.getMenuChoiceChar() + ", " + userMenuChoice.getMenuChoiceText()+ RESET_COLOR);
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

}
