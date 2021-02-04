package Main;

import java.util.List;

public class Building {

    public static void drawPlan(List<Room> roomList) {

        String str;
        String[] coloredAscii = new String[HOTEL_ASCII.length];
        char chr;
        int colorIndex;

        int startChar = 31;
        int noOfChars = 2000;
        int charsPerLine = 32;
        String padding = " ";

        for (int j = 0;
                j < noOfChars;
                j++) {

            if ((j % charsPerLine) == 0) {
                System.out.print(Misc.RESET + "\n" + (j + startChar) + " - " + (j + startChar + charsPerLine - 1) + ":\t" + Misc.YELLOW);
            }
            System.out.print((char) (j + startChar) + padding);
        }
        System.out.println("");
/*
        startChar = 9312;
        noOfChars = 16 * 29;
        charsPerLine = 16;
        padding = " ";

        System.out.println(Misc.RESET
                + "\n");

        for (int j = 0;
                j < noOfChars;
                j++) {

            if ((j % charsPerLine) == 0) {
                System.out.print(Misc.RESET + "\n" + (j + startChar) + " - " + (j + startChar + charsPerLine - 1) + ":\t" + Misc.YELLOW);
            }
            System.out.print((char) (j + startChar) + padding);
        }

        System.out.println(Misc.RESET
                + "\n");

        System.out.println(Misc.RESET);

        System.out.println(
                "╪╫╬╭╮╯╰╱");
        System.out.println(
                "╪╫╬╰╯╯╰╱");

        System.out.println(Misc.RESET);
         */

        for (int row = 0; row < HOTEL_ASCII.length; row++) {
            // Replace '£' with '\' and replace '§' with '"'
            HOTEL_ASCII[row] = HOTEL_ASCII[row].replace("£", "\\");
            HOTEL_ASCII[row] = HOTEL_ASCII[row].replace("§", "\"");

            str = "";
            for (int x = 0; x < HOTEL_ASCII[row].length(); x++) {

                // Get the corresponding location in the color strings, and check if it's lower case letter
                chr = HOTEL_COLORS[row].charAt(x);

                if (Character.isLowerCase(chr) && chr >= 'a' && chr <= 'z') {
                    //  Lookup the corresponding color code, and add it to the output string
                    colorIndex = (int) (chr - 'a');
                    
                    str += hotelColorLookup[colorIndex];
                }
                // Always add the chracter from the ascii strings
                str += HOTEL_ASCII[row].charAt(x);
            }
            System.out.println(str);
        }

    }

    static final String[] HOTEL_ASCII = {
        //1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
        " | Standard double  | Standard single        HOTEL FLOORPLAN            | Luxury single                 | Luxury Double             ",
        " ˅__________________˅_________               §§§§§§§§§§§§§§§            ˅_______________________________˅__________________________ ",
        " |`--´| WC Sh.|ʤ  ʤ|WC Sh.|`-´|                                         |_____|            |_____|ʤ   ʤ|___|___|        |______|   |",
        " |Room 1¨¨¨¨¨¨:    |¨¨¨¨¨     |                                         |          Room 11       |     |        Room 12   O  O     |",
        " |________   £      /  Room 2 |                                         |  _______              £       /               ________   |",
        " | Double ¦  ¦|    |   ,------|                                         | | Queen |  ___  __ £___|     |__ £____  ___  |  King  |  |",
        " | bed    ¦  ¦|    |   | Twin |                                         | | size  | (___)|WC,bath|     |WC, bath|(___) |  size  |  |",
        " |--------´   |    |   | bed  |                                         | | bed   |  ___ |Shower |     | Shower | ___  |  bed   |  |",
        " |____________|    |___|______|                  Garden                 |_|_______|_:___:|_______|     |________|:___:_|________|__|",
        " |`--´| WC Sh.|    |WC Sh.|`-´|                                         |_____|            |_____|     |___|___|        |______|   |",
        " |Room 3¨¨¨¨¨¨:    |¨¨¨¨¨     |                                         |          Room 9        |     |        Room 10   O  O     |",
        " |________   £      /  Room 4 |                                         |  _______              £       /               ________   |",
        " | Double ¦  ¦|    |   ,------|                                         | | Queen |  ___  __ £___|     |__ £____  ___  |  King  |  |",
        " | bed    ¦  ¦|    |   | Twin |                                         | | size  | (___)|WC,bath|     |WC, bath|(___) |  size  |  |",
        " |--------´   |    |   | bed  |                                         | | bed   |  ___ |Shower |     | Shower | ___  |  bed   |  |",
        " |____________|    |___|______|                                         |_|_______|_:___:|_______|     |________|:___:_|________|__|",               
        " |`--´| WC Sh.|    |WC Sh.|`-´|                                         |_____|            |_____|     |___|___|        |______|   |",
        " |Room 5¨¨¨¨¨¨:    |¨¨¨¨¨     |                                         |          Room 7        |     |        Room 8    O  O     |",
        " |________   £      /  Room 6 |                                         |  _______              £       /               ________   |",
        " | Double ¦  ¦|    |   ,------|                                         | | Queen |  ___  __ £___|     |__ £____  ___  |  King  |  |",
        " | bed    ¦  ¦|    |   | Twin |                                         | | size  | (___)|WC,bath|  .  |WC, bath|(___) |  size  |  |",
        " |--------´   |    |   | bed  |                                         | | bed   |  ___ |Shower | /|£ | Shower | ___  |  bed   |  |",
        " |____________|    |___|______|_________________________________________|_|_______|_:___:|_______|  |  |________|:___:_|________|__|", 
        " |            |      W e s t   c o r r i d o r                         E e s t   c o r r i d o r --´    / Broom closet, storage    |",
        " |  ,---------|ʤ___________________________________         __________________________________________ʤ|___________________________|",
        " |  |         |  |    _____       _____        |ʤ             ʤ:   Reception    £  Staff room | WC  | WC  |   o __ o     o __ o   ʤ|",
        " |  |         |__|   (_____)     (_____)       |               |_____________ˎˏ|______________|_ £__|_ £__|  o (__) o   o (__) o   |",
        " |  |         |                                |                                                                 Lounge   o  o    _|",
        " |  | Kitchen  £          Dining room         /                                               ʤ_    __     _    __   _      __   | |",
        " |  |          /      ___      ___      ___   £      __      __/  £__     o __ o     o __ o   ʤ |  (__)   | |  (__) | |    (__)  | |",
        " |  |         |__    (___)    (___)    (___)   |   o(__)o   |        |ʤ  o (__) o   o (__) o  ʤ  £_____   |  £_____/  |    _____/  |", 
        " |__|_________|__|_____________________________|ʤ__________ʤ|__/  £__|________________________ʤ________)_ʤ|___________|_ʤ_(________|",
        "                                                             Entrance                                                               "};

    static final String[] HOTEL_COLORS = {
        //1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
        " i                                      i                                                                                            ",
        " ie_________________ie_________         i                               ie______________________________ie_________________________  ",
        " ef--´a WC S..eg   eaC S..|f-´eg        i                               ef____|            |_____eg    ef__|___|        |______|   e ",
        " erOOM 1a¨¨¨¨¨e    ea¨¨¨¨     eg                                        ef        rROOM 11f      e     ef      rROOM 12f  O  O     e ",
        " efb______   e     e  rROOM 2 eg                                        efb_______ f            e       ef             b________f  e ",
        " efbOUBLE ¦f ¦e    e   b------eg                                        efb       |  ___  a_ £___e     ea_ £____ f___  b  KING  |  e ",
        " efbBD    ¦f ¦e    e   b TWIN eg                                        efb       | (___)a       e     eaC, BATH|f___) b  SIZE  |  e ",
        " efb------´   e    e   b BED  eg                                        efb       |  ___ a       e     eaSHOWER |f___  b  BED   |  e ",
        " e____________e    e___be_____eg                                        e_be______befe__fae______e     e________afe__febe_______fe_e ",
        " ef--´a WC S..e    eaC S..|f-´eg                                        ef____|            |_____e     ef__|___|        |______|   e ",
        " erOOM 1a¨¨¨¨¨e    ea¨¨¨¨     eg                                        ef        rROOM 11f      e     ef      rROOM 12f  O  O     e ",
        " efb______   e     e  rROOM 2 eg                                        efb_______ f            e       ef             b________f  e ",
        " ebDOUBLE ¦f ¦e    e   b------eg                                        efb       |  ___  a_ £___e     ea_ £____ f___  b  KING  |  e ",
        " ebBED    ¦f ¦e    e   b TWIN eg                                        efb       | (___)a       e     eaC, BATH|f___) b  SIZE  |  e ",
        " eb-------´   e    e   b BED  eg                                        efb       |  ___ a       e     eaSHOWER |f___  b  BED   |  e ",
        " e____________e    e___be_____eg                                        e_be______befe__fae______e     e________afe__febe_______fe_e ",
        " ef--´a WC S..e    eaC S..|f-´eg                                        ef____|            |_____e     ef__|___|        |______|   e ",
        " erOOM 1a¨¨¨¨¨e    ea¨¨¨¨     eg                                        ef        rROOM 11f      e     ef      rROOM 12f  O  O     e ",
        " efb______   e     e  rROOM 2 eg                                        efb_______ f            e       ef             b________f  e ",
        " ebDOUBLE ¦f ¦e    e   b------eg                                        efb       |  ___  a_ £___ei    ea_ £____ f___  b  KING  |  e ",
        " ebBED    ¦f ¦e    e   b TWIN eg                                        efb       | (___)a       ei    eaC, BATH|f___) b  SIZE  |  e ",
        " eb-------´   e    e   b BED  eg                                        efb       |  ___ a       ei    eaSHOWER |f___  b  BED   |  e ",
        " e____________ei   e___be_____e_________________________________________e_be______befe__fae______ei    e________afe__febe_______fe_e ",
        " e            |i --                                                                                    e i                         e ",
        " ef ,---------ege__________________________________         __________________________________________ge___________________________| ",
        " ef |         ef |    _____       _____        eg              fei             ee i           eiWC  eiWC e f                     ege ",
        " ef |         ef_|   (_____)     (_____)       e               f_______________e______________|_ £__|_ £__|f                       e ",
        " ef |         ef                              e f                                                 ei                     f         e ",
        " ef |ei        e                              e                                               gf                                   e ",
        " ef |          ef     ___      ___      ___   e f           e__/  £__ f                      egf                                   e ",
        " ef |         ef_    (___)    (___)    (___)   ef           e        |gf                     egf                                   e ", 
        " e__fe___________fe____________________________|ge_________ge__/  £__|Ee______________________ge_______fegfe__________fegefe_______| ",
        " i                                                           ENTRANCE                                                                 "};

    // Mapping lower case letters to color code strings, index with "(int)(char - 'a')" 
    static final String[] hotelColorLookup = {
        Misc.BR_BLUE/*a = bAth room */,
        Misc.D_YELLOW/*b = bed*/,
        Misc.BR_CYAN/*c*/,
        Misc.D_CYAN/*d*/,
        Misc.ANSI_RESET/*e = exterior & walls */,
        Misc.D_YELLOW/*f = furniture */,
        Misc.N_GREEN/*g = grass */,
        Misc.D_GREEN/*h*/,
        Misc.N_CYAN/*i = info */,
        Misc.GREEN/*j*/,
        Misc.MAGENTA/*k*/,  //Misc.BR_BLACK/*k*/,
        ""/*l*/,
        Misc.BR_MAGENTA/*m*/,
        Misc.D_MAGENTA/*n*/,
        ""/*o*/,
        ""/*p*/,
        Misc.RESET/*q*/,
        Misc.BR_YELLOW/*r = room number*/,
        Misc.D_RED /*s*/,
        Misc.REVERSE_VIDEO/*t*/,
        Misc.UNDERLINE/*u*/,
        Misc.BR_WHITE/*v*/,
        Misc.BR_CYAN /*w = water */,
        Misc.DD_WHITE/*x*/,
        Misc.BR_YELLOW/*y*/,
        Misc.D_YELLOW/*z*/};

}
/*

            "╔══┅┅════╦═┅┅════╦══┅┅═══╗", 
            "║┌───┐┈╭╮║┈┈┈┈┈┈┈║⒈⒉⒊⒋⒌⒍⒎║",
            "║│bed\t│┈╰╯║┈┈┈┈┈┈┈║1234567║",
            "║└───┘┈┈┈║┈┈┈▲△┈┈║⒒⒓⒔⒕⒖⒗⒘║",
            "║┈┈┈┈┈⒐┈┈║┈┈┈┈⒑┈┈║abcdefg\t║",
            "╟────┐┈┈┈║┈┈┈┈┈┈┈║⒜⒝⒞⒟⒠⒡⒢║",
            "║┈┈╰╯│┈┈┈║┈┈┈┈┈┈┈║ⒶⒷⒸⒹⒺⒻⒼ║",
            "║┈┈ⓌⒸ│┈┈┈║┈┈┈┈┈┈┈║ABCDEFG║",
            "╚═════──═╩═══─═══╩═══─═══╝",
 */

 /*

|  `--´     `---|    |                                                                           |    |______|                    |",
|  ______Room 1 :    |                                                                           |    |      Room 16              |",
| |      |  _ £_|    |                                                                           |     /                _______   |",
| |Double| | WC |    |                                                                           |    | _ £_____  ___  | Queen |  |",
| |  bed | | Sh.|    |                                                                           |    | WC, bath|(___) | size  |  |",
|_|______|_|____|    |                                                                           |    | Shower  | ___  | bed   |  |",
|               |    |                                                                           |    |_________|:___:_|_______|__|",
|               |    |                                                                           |    |                           |",
|               |    |                                                                           |    |                           |",
|               |    |                                                                           |    |                           |",
|               |    |                                                                           |    |                           |",
|               |    |                                                                           |    |                           |",

 */
