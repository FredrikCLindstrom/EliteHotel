package Main;

import java.util.List;

public class Building {

    public static void drawPlan(List<Room> roomList, boolean markAvailability, boolean reception) { // TODO: CHange behaviour if it's from the reception?
 
        String str;
        String[] coloredAscii = new String[HOTEL_ASCII.length];
        char chr;
        int colorIndex;
        int roomNr;
        String roomStr;

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

                    str += HOTEL_COLOR_LOOKUP[colorIndex];
                }
                // Always add the chracter from the ascii strings
                str += HOTEL_ASCII[row].charAt(x);
            }
            // If markAvailability, mark the room numers with GREEN/RED
            if (markAvailability) {
                switch (row) {
                    case 3:  // Room 1. Room 11, Room 12
                    case 10: // Room 3. Room 9, Room 10
                    case 17: // Room 1. Room 17, Room 8
                        roomNr = ((row - 3) / 7) * 2 + 1; //  Room 1 is written on row 3, room 3 on row 10, Room 5 on row 17
                        roomStr = "Room " + roomNr + "";
                        // Insert color code for RED or GREEN in the string, depending on if the room has a guast or not
                        Misc.printDebug("Replace: '" + roomStr + "', guest: " + roomList.get(roomNr - 1).getGuest());
                        str = str.replace(roomStr, (roomList.get(roomNr - 1).getGuest() == null ? Misc.BR_GREEN : Misc.BR_RED) + roomStr);

                        roomNr = ((17 - row) / 7) * 2 + 7;  // Room 11 is written on row 3, room 9 on row 10, Room 7 on row 17
                        roomStr = "Room " + roomNr;
                        // Insert color code for RED or GREEN in the string, depending on if the room has a guast or not
                        Misc.printDebug("Replace: '" + roomStr + "', guest: " + roomList.get(roomNr - 1).getGuest());
                        str = str.replace(roomStr, (roomList.get(roomNr - 1).getGuest() == null ? Misc.BR_GREEN : Misc.BR_RED) + roomStr);

                        roomNr = ((17 - row) / 7) * 2 + 8;  // Room 12 is written on row 3, room 10 on row 10, Room 8 on row 17
                        roomStr = "Room " + roomNr;
                        // Insert color code for RED or GREEN in the string, depending on if the room has a guast or not
                        Misc.printDebug("Replace: '" + roomStr + "', guest: " + roomList.get(roomNr - 1).getGuest());
                        str = str.replace(roomStr, (roomList.get(roomNr - 1).getGuest() == null ? Misc.BR_GREEN : Misc.BR_RED) + roomStr);
                        break;
                    case 4:  // Room 2
                    case 11: // Room 4
                    case 18: // Room 6
                        roomNr = (((row + 3) / 7) * 2);  //  Room 2 is written on row 4, room 4 on row 11, Room 6 on row 18
                        roomStr = "Room " + roomNr;
                        // Insert color code for RED or GREEN in the string, depending on if the room has a guast or not
                        //Misc.printDebug("Replace: '" + roomStr + "', guest: " + roomList.get(roomNr - 1).getGuest());
                        str = str.replace(roomStr, (roomList.get(roomNr - 1).getGuest() == null ? Misc.BR_GREEN : Misc.BR_RED) + roomStr);
                        break;
                    default:
                        break;
                }
            }

            System.out.println(str);
        }

    }
    // ASCII represantation of the drawing & room layoyut of the hotel building
    static final String[] HOTEL_ASCII = {
        //1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
        "┊Standard double┊┊Standard single┊       ___________________           ┊Luxury single┊                 Luxury Double┊              ", // row 0
        "☀___▼_______________▼________☀           | HOTEL FLOORPLAN |           ☀___▼_____...._____________________▼______...._____________☀",
        "║`--´| WC Sh.│   ʤ│WC Sh.|`-´║           ===================           ║_____|            |_____│ʤ    │___|___|        |______|   ║",
        "║Room 1¨¨¨¨¨¨│1   │¨¨¨¨¨     ║                                         ║          Room 11       │   12│        Room 12   O  O     ║",
        "┇________   ╲      ╲  Room 2 ┇                                         ┇  _______              ╱       ╱               ________   ┇",
        "║ Double |  ¦│   2│   ,------║                                         ║ | Queen |  ___  __ £___│11   │__ £____  ___  |  King  |  ┇", // row 5
        "║ bed    |  ¦│    │   | Twin ║                                         ║ | size  | (___)|WC,bath│     │WC, bath|(___) |  size  |  ║",
        "║--------´   │    │¦  | bed  ║                                         ║ | bed   |  ___ |Shower │     │ Shower | ___  |  bed   |  ║",
        "║____________│    │___|______║                                         ║_|_______|_:___:|_______│     │________|:___:_|________|__║",
        "║`--´| WC Sh.│    │WC Sh.|`-´║                                         ║_____|            |_____│     │___|___|        |______|   ║",
        "║Room 3¨¨¨¨¨¨│3   │¨¨¨¨¨     ║                                         ║          Room 9        │   10│        Room 10   O  O     ║", // row 10
        "┇________   ╲      ╲  Room 4 ┇                                         ┇  _______              ╱       ╱               ________   ┇",
        "║ Double |  ¦│   4│   ,------║                                         ║ | Queen |  ___  __ £___│9    │__ £____  ___  |  King  |  ┇",
        "║ bed    |  ¦│    │   | Twin ║                                         ║ | size  | (___)|WC,bath│     │WC, bath|(___) |  size  |  ║",
        "║--------´   │    │¦  | bed  ║                                         ║ | bed   |  ___ |Shower │     │ Shower | ___  |  bed   |  ║",
        "║____________│    │___|______║                                         ║_|_______|_:___:|_______│     │________|:___:_|________|__║", // row 15
        "║`--´| WC Sh.│    │WC Sh.|`-´║                                         ║_____|            |_____│     │___|___|        |______|   ║",
        "║Room 5¨¨¨¨¨¨│5   │¨¨¨¨¨     ║                                         ║          Room 7        │    8│        Room 8    O  O     ║",
        "┇________   ╲      ╲  Room 6 ┇                                         ┇  _______              ╱       ╱               ________   ┇",
        "║ Double |  ¦│   6│   ,------║                                         ║ | Queen |  ___  __ £___│7    │__ £____  ___  |  King  |  ┇",
        "║ bed    |  ¦│    │   | Twin ║                                         ║ | size  | (___)|WC,bath│     │WC, bath|(___) |  size  |  ║", // row 20
        "║--------´   │    │¦  | bed  ║                                         ║ | bed   |  ___ |Shower │     │ Shower | ___  |  bed   |  ║",
        "║____________│    │___|______║=======....===================.....======║_|_______|_:___:|_______│     │________|:___:_|________|__║",
        "║            │      ☜  W e s t   c o r r i d o r  ☜             ☞  E e s t   c o r r i d o r  ☞        ╱ Broom closet, storage    ║",
        "║  ,---------│◣▁__________________________________         _________________________________________▁◢│___________________________║",
        "┇  |         │  |    _____       _____     |  │ʤD            ʤ│   Reception    ╱ Staff room  │ WC  |  WC  │  o __ o     o __ o   ʤ║",
        "║  |         │__|   (_____)     (_____)    |__│D               ╲____________ˎˏ|_______________|_ ╲__|_ ╲__│ o (__) o   o (__) o   ┇",
        "║  | Kitchen  ╲          Dining room         ╱     Lobby          ░░░░                       __    __    _ Lounge  _      __    __║",
        "║  |          ╱      ___      ___      ___   ╲    _    ___╱  ╲___      o __ o      o __ o    ʤ  |  (__)  │ |      | │    (__)  |  ║",
        "┇  |         │__    (___)    (___)    (___)   │ o(_)o │         │ʤ    o (__) o    o (__) o   ʤ  £______  │  ╲____/  |    ______/  ┇",
        "║__|_________│__|__.......__.......__.......__│Ϣ_____Ϣ│__╱  ╲__│Ϣ_____.....________.....___Ϣ|__....__)_Ϣ|____....___|_Ϣ_(__....___║",
        "                                                          Entrance                                                      "};

    // Only lower case letters are taken into account, maps to colors according tp HOTEL_COLOR_LOOKUP
    static final String[] HOTEL_COLORS = {
        //1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
        "mi              m i              m    i                               m i            m i                            m  i              ",
        "me__ie______________ie_______m        i                                me__ie_____________________________ie______________________m   ",
        "ef--´a WC S..eg   eaC S..|f-´eg       i                                ef____|            |_____eg    ef__|___|        |______|   e   ",
        "erOOM 1a¨¨¨¨¨ei   ea¨¨¨¨     eg       i                                ef        rROOM 11f      ei    ef      rROOM 12f  O  O     e   ",
        "efb______   e     e  rROOM 2 eg                                        efb_______ f            e       ef             b________f  e   ",
        "efbOUBLE ¦f ¦ei   e   b------eg                                        efb       |  ___  a_ £___ei    ea_ £____ f___  b  KING  |  e   ",
        "efbBD    ¦f ¦e    e   b TWIN eg                                        efb       | (___)a       e     eaC, BATH|f___) b  SIZE  |  e   ",
        "efb------´   e    ef  b BED  eg                                        efb       |  ___ a       e     eaSHOWER |f___  b  BED   |  e   ",
        "e____________e    e___be_____eg                                        e_be______befe__fae______e     e________afe__febe_______fe_e   ",
        "ef--´a WC S..e    eaC S..|f-´eg                                        ef____|            |_____e     ef__|___|        |______|   e   ",
        "erOOM 1a¨¨¨¨¨ei   ea¨¨¨¨     eg                                        ef        rROOM 11f      ei    ef      rROOM 12f  O  O     e   ",
        "efb______   e     e  rROOM 2 eg                                        efb_______ f            e       ef             b________f  e   ",
        "ebDOUBLE ¦f ¦ei   e   b------eg                                        efb       |  ___  a_ £___ei    ea_ £____ f___  b  KING  |  e   ",
        "ebBED    ¦f ¦e    e   b TWIN eg                                        efb       | (___)a       e     eaC, BATH|f___) b  SIZE  |  e   ",
        "eb-------´   e    ef  b BED  eg                                        efb       |  ___ a       e     eaSHOWER |f___  b  BED   |  e   ",
        "e____________e    e___be_____eg                                        e_be______befe__fae______e     e________afe__febe_______fe_e   ",
        "ef--´a WC S..e    eaC S..|f-´eg                                        ef____|            |_____e     ef__|___|        |______|   e   ",
        "erOOM 1a¨¨¨¨¨ei   ea¨¨¨¨     eg                                        ef        rROOM 11f      ei    ef      rROOM 12f  O  O     e   ",
        "efb______   e     e  rROOM 2 eg                                        efb_______ f            e       ef             b________f  e   ",
        "ebDOUBLE ¦f ¦ei   e   b------eg                                        efb       |  ___  a_ £___ei    ea_ £____ f___  b  KING  |  e   ",
        "ebBED    ¦f ¦e    e   b TWIN eg                                        efb       | (___)a       ei    eaC, BATH|f___) b  SIZE  |  e   ",
        "eb-------´   e    ef  b BED  eg                                        efb       |  ___ a       ei    eaSHOWER |f___  b  BED   |  e   ",
        "e____________ei   e___be_____e=========================================e_be______befe__fae______ei    e________afe__febe_______fe_e   ",
        "e            |i --                                                                                    e j                         e   ",
        "ef ,---------e____________________________________         ___________________________________________e___________________________|   ",
        "ef |         ef |    _____       _____        egf           g fei             ee j           eiWC  eiWC e  f                    ege   ",
        "ef |         ef_|   (_____)     (_____)       ef              f_______________e______________|_ £__|_ £___|f                      e   ",
        "ef |ej        e   i                          e i              m              f                             i      f               e   ",
        "ef |          ef     ___      ___      ___   e f      e__/  £__ f                           egf                                   e   ",
        "ef |         ef_    (___)    (___)    (___)   ef      e         |gf                         egf                                   e   ",
        "e__fe___________fe____________________________|ge____ge__/  £__|ge_________________________gfe_______fegfe__________fegefe________|   ",
        "i                                                                                         e                                           "};

    // Mapping lower case letters to color code strings, index with "(int)(char - 'a')" !
    static final String[] HOTEL_COLOR_LOOKUP = {
        Misc.D_YELLOW/*a = bAth room */,
        Misc.D_YELLOW/*b = bed*/,
        Misc.BR_CYAN/*c*/,
        Misc.D_CYAN/*d*/,
        Misc.ANSI_RESET/*e = exterior & walls */,
        Misc.D_YELLOW/*f = furniture */,
        Misc.N_GREEN/*g = grass */,
        Misc.D_GREEN/*h*/,
        Misc.RESET + Misc.N_CYAN/*i = info */,
        Misc.D_CYAN/*j = discrete info */,
        Misc.MAGENTA/*k*/,
        ""/*l*/,
        Misc.D_BLUE/*m = mat*/,
        Misc.BR_MAGENTA/*n*/,
        ""/*o*/,
        ""/*p*/,
        Misc.RESET/*q*/,
        Misc.BR_YELLOW/*r = room number*/,
        Misc.D_RED /*s*/,
        Misc.REVERSE_VIDEO/*t*/,
        Misc.UNDERLINE/*u*/,
        Misc.BR_WHITE/*v*/,
        Misc.N_CYAN /*w = water */,
        Misc.DD_WHITE/*x*/,
        Misc.BR_YELLOW/*y*/,
        Misc.D_YELLOW/*z*/,
        Misc.D_YELLOW/**/};

}