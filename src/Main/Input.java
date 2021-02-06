package Main;

import java.util.Scanner;

public class Input {//input class handles all user input, loops included and all

    public static Scanner sc = new Scanner(System.in);

    public static final String RESET_COLOR = "\033[0;30m";
    public static final String RED = "\033[0;31m";

    public static int getUserInputInt() {
        String userInput;
        int parseInput = 0;
        boolean runInput = true;

        do {
            try {
                userInput = sc.nextLine();
                parseInput = Integer.parseInt(userInput);
                if (parseInput < 0) {
                    System.out.print(RED + "Negative numbers are not allowed, Try again: " + RESET_COLOR);
                } else {
                    runInput = false;
                }
            } catch (Exception e) {
                System.out.print(RED + "Wrong input, Try again: " + RESET_COLOR);
            }
        } while (runInput);
        return parseInput;
    }

    public static String getUserInputString() {
        String userInput = null;
        boolean run = true;

        do {
            try {
                userInput = sc.nextLine();
                run = false;
            } catch (NullPointerException e) {
                System.out.print(RED + "Wrong input, Try again: " + RESET_COLOR);
            }
        } while (run);
        return userInput;
    }

    public static double getUserInputDouble() {
        String userInput;
        double parseInput = 0;
        boolean run = true;

        do {
            try {
                userInput = sc.nextLine();
                parseInput = Double.parseDouble(userInput);
                run = false;
            } catch (Exception e) {
                System.out.print(RED + "Wrong input, Try again: " + RESET_COLOR);
            }
        } while (run);
        return parseInput;
    }
}
