
package Main;

public class Misc {
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

    public static final boolean DEBUG = true; // Enables debug output. TODO: turn it off in the end
    
    public static void printDebug(String str) {
        if (DEBUG) {
            System.out.println(MAGENTA + str + RESET);
        }
    }   
   
    // ANSI colcor codes, not all work on all consoles
    public static final String ANSI_RESET = "\u001b[0m";
    public static final String HIGH_INTENSITY = "\u001B[1m";
    public static final String LOW_INTENSITY = "\u001B[2m";

    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String BLINK = "\u001B[5m";
    public static final String RAPID_BLINK = "\u001B[6m";
    public static final String REVERSE_VIDEO = "\u001B[7m";
    public static final String INVISIBLE_TEXT = "\u001B[8m";

    // Normal color versions:
    public static final String N_BLACK = "\u001b[30m";
    public static final String N_RED = "\u001b[31m";
    public static final String N_GREEN = "\u001b[32m";
    public static final String N_YELLOW = "\u001b[33m";
    public static final String N_BLUE = "\u001b[34m";
    public static final String N_MAGENTA = "\u001b[35m";
    public static final String N_CYAN = "\u001b[36m";
    public static final String N_WHITE = "\u001b[37m";
    // Bright versions:
    public static final String BR_BLACK = "\u001b[30;1m";
    public static final String BR_RED = "\u001b[31;1m";
    public static final String BR_GREEN = "\u001b[32;1m";
    public static final String BR_YELLOW = "\u001b[33;1m";
    public static final String BR_BLUE = "\u001b[34;1m";
    public static final String BR_MAGENTA = "\u001b[35;1m";
    public static final String BR_CYAN = "\u001b[36;1m";
    public static final String BR_WHITE = "\u001b[37;1m";
    // Dark versions:
    public static final String D_BLACK = "\u001b[30;2m";
    public static final String D_RED = "\u001b[31;2m";
    public static final String D_GREEN = "\u001b[32;2m";
    public static final String D_YELLOW = "\u001b[33;2m";
    public static final String D_BLUE = "\u001b[34;2m";
    public static final String D_MAGENTA = "\u001b[35;2m";
    public static final String D_CYAN = "\u001b[36;2m";
    public static final String D_WHITE = "\u001b[37;2m";
    // Extra dark(?) version
    public static final String DD_BLACK = "\u001b[30;3m";
    public static final String DD_RED = "\u001b[31;3m";
    public static final String DD_GREEN = "\u001b[32;3m";
    public static final String DD_YELLOW = "\u001b[33;3m";
    public static final String DD_BLUE = "\u001b[34;3m";
    public static final String DD_MAGENTA = "\u001b[35;3m";
    public static final String DD_CYAN = "\u001b[36;3m";
    public static final String DD_WHITE = "\u001b[37;3m";

    // Mapping lower case letters to color code strings in ASCII art, index with "(int)(char - 'a')"!
    public static final String[] colorLookup = {
        D_BLUE/*a*/, 
        BR_BLUE/*b*/, 
        BR_CYAN/*c*/, 
        D_CYAN/*d*/,
        BR_BLACK/*e = grey*/, 
        D_WHITE/*f*/, 
        BR_GREEN/*g*/, 
        D_GREEN/*h*/,
        BLINK/*i*/, 
        GREEN/*j*/, 
        BR_BLACK/*k*/, 
        ""/*l*/,
        BR_MAGENTA/*m*/, 
        D_MAGENTA/*n*/, 
        ""/*o*/, 
        ""/*p*/,
        RESET/*q*/, 
        BR_RED/*r*/, 
        D_RED /*s*/, 
        REVERSE_VIDEO/*t*/,
        UNDERLINE/*u*/, 
        BR_WHITE/*v*/, 
        RESET/*w = white*/, 
        DD_WHITE/*x*/,
        BR_YELLOW/*y*/, 
        D_YELLOW/*z*/};
    
}
