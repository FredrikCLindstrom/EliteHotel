package Main;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class Building {

    public static void drawPlan(List<Room> roomList, boolean markAvailability, boolean reception) { // TODO: CHange behaviour if it's from the reception?

        String str;
        String[] coloredAscii = new String[HOTEL_ASCII.length];
        char chr;
        int colorIndex;
        int roomNr;
        String roomStr;
        String[] colorOfRooms = new String[Room.getNrOfCreatedRooms()]; // Lookup table for the ANIS color string of each room
        Room bestRoomOfClass;

        // Initialize all Room colors to Green/red depending on avialably/occupied by a guest
        for (roomNr = 1; roomNr <= Room.getNrOfCreatedRooms(); roomNr++) { // roomIndex = RoomNr-1;
            // if the guest is null it's available 
            colorOfRooms[roomNr - 1] = (Room.getRoomWithThisNr(roomNr, roomList).guest == null) ? Misc.GREEN : Misc.RED;
        }

        if (markAvailability) {
            // Explain color codes
            System.out.println("-- The floorplan with" + Misc.YELLOW + " yellow for available & recommended" + Misc.RESET + " rooms of each class,"
                    + Misc.GREEN + " green for available" + Misc.RESET + " rooms, and"
                    + Misc.RED + " red for occupied" + Misc.RESET + " rooms --");

            // Updating the rooms that are avaiable and recommend (of each class) to Yellow text
            //                                       <----------------------------------------------   list of rooms of this ▼ class   --------------------------->
            bestRoomOfClass = Ranking.highestRanked(roomList.stream().filter(e -> e.guest == null && e.getClass().equals(StandardSingleRoom.class)).collect(toList()));
            if (bestRoomOfClass != null) {
                colorOfRooms[bestRoomOfClass.roomNr - 1] = Misc.YELLOW;
            }
            bestRoomOfClass = Ranking.highestRanked(roomList.stream().filter(e -> e.guest == null && e.getClass().equals(StandardDoubleRoom.class)).collect(toList()));
            if (bestRoomOfClass != null) {
                colorOfRooms[bestRoomOfClass.roomNr - 1] = Misc.YELLOW;
            }
            bestRoomOfClass = Ranking.highestRanked(roomList.stream().filter(e -> e.guest == null && e.getClass().equals(LuxurySingleRoom.class)).collect(toList()));
            if (bestRoomOfClass != null) {
                colorOfRooms[bestRoomOfClass.roomNr - 1] = Misc.YELLOW;
            }
            bestRoomOfClass = Ranking.highestRanked(roomList.stream().filter(e -> e.guest == null && e.getClass().equals(LuxuryDoubleRoom.class)).collect(toList()));
            if (bestRoomOfClass != null) {
                colorOfRooms[bestRoomOfClass.roomNr - 1] = Misc.YELLOW;
            }
        }
        Misc.printDebug(colorOfRooms.toString());

        // Handle eaach row of the hotelbvbuilding ASCII art
        for (int row = 0; row < HOTEL_ASCII.length; row++) {
            // Replace '£' with '\' and replace '§' with '"'
            HOTEL_ASCII[row] = HOTEL_ASCII[row].replace("£", "\\");
            HOTEL_ASCII[row] = HOTEL_ASCII[row].replace("§", "\"");

            str = "";
            for (int x = 0; x < HOTEL_ASCII[row].length(); x++) {

                // Get the corresponding location in the color strings, and check if it's lower case letter
                chr = HOTEL_COLORS[row].charAt(x);

                if (Character.isLowerCase(chr) && chr >= 'a' && chr <= 'z') {
                    //  So the character specifies a color, now lookup the corresponding color code string, and add it to the output string
                    str += COLOR_CHAR_2_COLOR_STRING_LOOKUP[(int) (chr - 'a')];
                }
                // Always add the character from the ascii strings
                str += HOTEL_ASCII[row].charAt(x);
            }
            // If markAvailability, mark the room numers with GREEN/RED
            if (markAvailability) {
                switch (row) {
                    case 3:  // Room 1. Room 11, Room 12
                    case 10: // Room 3. Room 9, Room 10
                    case 17: // Room 5. Room 17, Room 8
                        roomNr = ((row - 3) / 7) * 2 + 1; //  Room 1 is written on row 3, room 3 on row 10, Room 5 on row 17
                        roomStr = "Room " + roomNr + "";
                        // Insert color code before Room x, so it's shown in the right color
                        str = str.replace(roomStr, colorOfRooms[roomNr - 1] + roomStr);

                        roomNr = ((17 - row) / 7) * 2 + 7;  // Room 11 is written on row 3, room 9 on row 10, Room 7 on row 17
                        roomStr = "Room " + roomNr;
                        // Insert color code before Room x
                        str = str.replace(roomStr, colorOfRooms[roomNr - 1] + roomStr);

                        roomNr = ((17 - row) / 7) * 2 + 8;  // Room 12 is written on row 3, room 10 on row 10, Room 8 on row 17
                        roomStr = "Room " + roomNr;
                        // Insert color code before Room x
                        str = str.replace(roomStr, colorOfRooms[roomNr - 1] + roomStr);
                        break;
                    case 4:  // Room 2
                    case 11: // Room 4
                    case 18: // Room 6
                        roomNr = (((row + 3) / 7) * 2);  //  Room 2 is written on row 4, room 4 on row 11, Room 6 on row 18
                        roomStr = "Room " + roomNr;
                        // Insert color code before Room x
                        str = str.replace(roomStr, colorOfRooms[roomNr - 1] + roomStr);
                        break;
                    default:
                        break;
                }
            }

            System.out.println(str);
        }

    }
    // ASCII represantation of the drawing & room layoyut of the hotel building
    static private final String[] HOTEL_ASCII = {
        //1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
        "Standard double┊┊┊Standard single        ╔=================╗           ┊Luxury single┊                 Luxury Double┊              ", // row 0
        "☀__▼_________________▼_______            ║ HOTEL FLOORPLAN ║            ___▼_____...._____________________▼______...._____________☀",
        "║`--´| WC Sh.│°  ʤ│WC Sh.|`-´║           ===================           ║_____|            |_____│ʤ   °│___|___|        |______|   ║",
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
        "║------------│     ☜  W e s t   c o r r i d o r  ☜         ☞  E a s t   c o r r i d o r  ☞             ╱Cleaning cupboard, storage║",
        "║  ,---------│◣▁__________________________________         _________________________________________▁◢│___________________________║",
        "┇  |        ®│  |    _____       _____    °|  │ʤD            ☂│   Reception   /  Staff room  │ WC  |  WC  │° o __ o     o __ o   ʤ║",
        "║  |         │__|   (_____)     (_____)    |__│D               £☎___________ˎˏ|_______________|_ ╲__|_ ╲__│ o (__) o   o (__) o   ┇",
        "║  | Kitchen  ╲          Dining room         ╱     Lobby           ░░░░                      __    __    _ Lounge  _      __    __║",
        "║  |          ╱      ___      ___      ___   ╲    _    ___╱  ╲___      o __ o      o __ o    ʤ  |  (__)  │ |      | │    (__)  |  ║",
        "┇  |         │__    (___)    (___)    (___)   │ o(_)o │         │ʤ    o (__) o    o (__) o   ʤ  £______  │  ╲____/  |    ______/  ┇",
        "║__|_________│__|__.......__.......__.......__│Ϣ_____Ϣ│__╱  ╲__│Ϣ_____.....________.....___Ϣ|__....__)_Ϣ|____....___|_Ϣ_(__....___║",
        "                                                 ☀☂☂☀   Entrance  ☀☂☂☀                                                           "};

    // Only lower case letters are taken into account, maps to colors according tp COLOR_CHAR_2_COLOR_STRING_LOOKUP  © ª « ¬ ® ¯ ° ± ² ³ ´ µ ¶ · ¸ ¹ º 
    static private final String[] HOTEL_COLORS = {
        //1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
        "i              m  i              m    k                               m i            m i                            m  i              ",
        "me_ie________________ie______m        k                   k            me__ie_____________________________ie______________________m   ",
        "ef--´a WC S..exeg eaC S..|f-´eg       k                                ef____|            |_____eg  x ef__|___|        |______|   e   ",
        "erOOM 1a¨¨¨¨¨ei   ea¨¨¨¨     eg       k                                ef        rROOM 11f      ei    ef      rROOM 12f  O  O     e   ",
        "efb______   e     e  rROOM 2 eg                                        efb_______ f            e       ef             b________f  e   ",
        "ebDOUBLE ¦f ¦ei   e   b------eg                                        efb       |  ___  a_ £___ei    ea_ £____ f___  b  KING  |  e   ",
        "ebBED    ¦f ¦e    e   b TWIN eg                                        efb       | (___)a       e     eaC, BATH|f___) b  SIZE  |  e   ",
        "eb-------´   e    ef  b BED  eg                                        efb       |  ___ a       e     eaSHOWER |f___  b  BED   |  e   ",
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
        "ef-----------ej                                                                                       e j                         e   ",
        "ef ,---------e____________________________________         ___________________________________________e___________________________|   ",
        "ef |        yel |    _____       _____        egh           j fei             ee j           eiWC  eiWC e  xl                   ege   ",
        "ef |        yel_|   (_____)     (_____)       eh               fyf____________e______________|_ £__|_ £___|l                      e   ",
        "ef |ej        e   i                          e i              m                              l             i      l               e   ",
        "ef |          el     ___      ___      ___   e h      e__/  £__  h                          egl                                   e   ",
        "ef |         el_    (___)    (___)    (___)   eh      e         |gh                         egl                                   e   ",
        "e__fe___________le____________________________|ge____ge__/  £__|ge_________________________gle_______legle__________legele________|   ",
        "ef                                                    i ENTRANCE ef                                                                   "};
    // Mapping lower case letters to color code strings, index with "(int)(char - 'a')" !
    static final String[] COLOR_CHAR_2_COLOR_STRING_LOOKUP = {
        Misc.D_YELLOW/*a = bAth room */,
        Misc.D_YELLOW/*b = bed*/,
        Misc.BR_CYAN/*c*/,
        Misc.D_CYAN/*d*/,
        Misc.ANSI_RESET/*e = exterior & walls */,
        Misc.D_YELLOW/*f = furniture */,
        Misc.N_GREEN/*g = grass */,
        Misc.D_GREEN/*h = hotel lobby */,
        Misc.RESET + Misc.N_CYAN/*i = info */,
        Misc.D_CYAN/*j = discrete info */,
        Misc.MAGENTA/*k*/,
        Misc.D_MAGENTA/*l = lounge furniture*/,
        Misc.D_BLUE/*m = mat*/,
        Misc.BR_MAGENTA/*n*/,
        ""/*o*/,
        ""/*p*/,
        Misc.RESET/*q*/,
        Misc.BR_MAGENTA/*r = room number*/,
        Misc.D_RED /*s*/,
        Misc.REVERSE_VIDEO/*t*/,
        Misc.UNDERLINE/*u*/,
        Misc.BR_WHITE/*v*/,
        Misc.N_CYAN /*w = water */,
        Misc.BR_RED/*x = extinguisher*/,
        Misc.N_RED/*y*/,
        Misc.D_YELLOW/*z*/,
        Misc.D_YELLOW/**/};

    static private final String[] ROOM_CHOICE_ASCII = {
        "┊Standard single┊┊ Standard double   ┊┊         Luxury single       ┊┊         Luxury Double┊       ", // row 0
        "  ▽__________▽      ▽____________▽      ▽________________________▽     ▽___________________________▽",
        "  │WC Sh.|`-´║      ║`--´| WC Sh.│      ║_____|            |_____│     │___|___|         |______|  ║",
        "  │¨¨¨¨¨     ║      ║Std.Dbl¨¨¨¨¨│      ║  Luxury single room    │     │Luxury double room O  O    ║",
        "   ╲ Std.Sngl┇      ┇________   ╲       ┇  _______              ╱       ╱               ________   ┇",
        "  │   ,------║      ║ Double |  ¦│      ║ | Queen |  ___  __ £___│     │__ £____  ___  |  King  |  ┇", // row 5
        "  │   | Twin ║      ║ bed    |  ¦│      ║ | size  | (___)|WC,bath│     │WC, bath|(___) |  size  |  ║",
        "  │¦  | bed  ║      ║--------´   │      ║ | bed   |  ___ |Shower │     │ Shower | ___  |  bed   |  ║",
        "  │___|______║      ║____________│      ║_|_______|_:___:|_______│     │________|:___:_|________|__║"};

    // Only lower case letters are taken into account, maps to colors according tp COLOR_CHAR_2_COLOR_STRING_LOOKUP  © ª « ¬ ® ¯ ° ± ² ³ ´ µ ¶ · ¸ ¹ º 
    static private final String[] ROOM_CHOICE_COLORS = {
        "mi      D S    Emm iT     D D    E   mmi        L      S    E       mmi        L    Y D    Em         ", // row 0
        "  me_________m      ▽____________e      ▽________________________e     ▽___________________________e ",
        "  efC Sh.|`-´e      ║f--´| WC Sh.e      ║f____|            |_____e     │f__|___|         |______|  e ",
        "  ef¨¨¨¨     e      ║i      f¨¨¨¨e      ║i                       e     │i                 fO  O    e ",
        "   erStd.Sngle      ┇f_______   e       ┇f _______              e       ╱f              ________   e ",
        "  ef  ,------e      ║f       |  ¦e      ║f| Queen |  ___  __ £___e     │f_ £____  ___  |  King  |  e ", // row 5
        "  ef  | Twin e      ║f       |  ¦e      ║f| size  | (___)|WC,bathe     │fC, bath|(___) |  size  |  e ",
        "  ef  | bed  e      ║f-------´   e      ║f| bed   |  ___ |Shower e     │fShower | ___  |  bed   |  e ",
        "  e___fe_____e      ║____________e      ║_fe______fefe__f|e______e     │________f:e__fefe_______fe_e "};

    public static void drawRoomClasses() { // Currently not needed...

        String str;
        char chr;

        // Handle eaach row of the hotelbvbuilding ASCII art
        for (int row = 0; row < HOTEL_ASCII.length; row++) {
            // Replace '£' with '\' and replace '§' with '"'
            ROOM_CHOICE_ASCII[row] = ROOM_CHOICE_ASCII[row].replace("£", "\\");
            ROOM_CHOICE_ASCII[row] = ROOM_CHOICE_ASCII[row].replace("§", "\"");

            str = "";
            for (int x = 0; x < ROOM_CHOICE_ASCII[row].length(); x++) {

                // Get the corresponding location in the color strings, and check if it's lower case letter
                chr = ROOM_CHOICE_COLORS[row].charAt(x);

                if (Character.isLowerCase(chr) && chr >= 'a' && chr <= 'z') {
                    //  So the character specifies a color, now lookup the corresponding color code string, and add it to the output string
                    str += COLOR_CHAR_2_COLOR_STRING_LOOKUP[(int) (chr - 'a')];
                }
                // Always add the character from the ascii strings
                str += ROOM_CHOICE_ASCII[row].charAt(x);
            }

            System.out.println(str);
        }

    }
}
    /* Wide cHaracters:
9312 - 9327:	① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩ ⑪ ⑫ ⑬ ⑭ ⑮ ⑯ 
9328 - 9343:	⑰ ⑱ ⑲ ⑳ ⑴ ⑵ ⑶ ⑷ ⑸ ⑹ ⑺ ⑻ ⑼ ⑽ ⑾ ⑿ 
9344 - 9359:	⒀ ⒁ ⒂ ⒃ ⒄ ⒅ ⒆ ⒇ ⒈ ⒉ ⒊ ⒋ ⒌ ⒍ ⒎ ⒏ 
9360 - 9375:	⒐ ⒑ ⒒ ⒓ ⒔ ⒕ ⒖ ⒗ ⒘ ⒙ ⒚ ⒛ ⒜ ⒝ ⒞ ⒟ 
9376 - 9391:	⒠ ⒡ ⒢ ⒣ ⒤ ⒥ ⒦ ⒧ ⒨ ⒩ ⒪ ⒫ ⒬ ⒭ ⒮ ⒯ 
9392 - 9407:	⒰ ⒱ ⒲ ⒳ ⒴ ⒵ Ⓐ Ⓑ Ⓒ Ⓓ Ⓔ Ⓕ Ⓖ Ⓗ Ⓘ Ⓙ 
9408 - 9423:	Ⓚ Ⓛ Ⓜ Ⓝ Ⓞ Ⓟ Ⓠ Ⓡ Ⓢ Ⓣ Ⓤ Ⓥ Ⓦ Ⓧ Ⓨ Ⓩ 
9424 - 9439:	ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ⓘ ⓙ ⓚ ⓛ ⓜ ⓝ ⓞ ⓟ 
9440 - 9455:	ⓠ ⓡ ⓢ ⓣ ⓤ ⓥ ⓦ ⓧ ⓨ ⓩ ⓪ ⓫ ⓬ ⓭ ⓮ ⓯ 
9456 - 9471:	⓰ ⓱ ⓲ ⓳ ⓴ ⓵ ⓶ ⓷ ⓸ ⓹ ⓺ ⓻ ⓼ ⓽ ⓾ ⓿ 
9472 - 9487:	─ ━ │ ┃ ┄ ┅ ┆ ┇ ┈ ┉ ┊ ┋ ┌ ┍ ┎ ┏ 
9488 - 9503:	┐ ┑ ┒ ┓ └ ┕ ┖ ┗ ┘ ┙ ┚ ┛ ├ ┝ ┞ ┟ 
9504 - 9519:	┠ ┡ ┢ ┣ ┤ ┥ ┦ ┧ ┨ ┩ ┪ ┫ ┬ ┭ ┮ ┯ 
9520 - 9535:	┰ ┱ ┲ ┳ ┴ ┵ ┶ ┷ ┸ ┹ ┺ ┻ ┼ ┽ ┾ ┿ 
9536 - 9551:	╀ ╁ ╂ ╃ ╄ ╅ ╆ ╇ ╈ ╉ ╊ ╋ ╌ ╍ ╎ ╏ 
9552 - 9567:	═ ║ ╒ ╓ ╔ ╕ ╖ ╗ ╘ ╙ ╚ ╛ ╜ ╝ ╞ ╟ 
9568 - 9583:	╠ ╡ ╢ ╣ ╤ ╥ ╦ ╧ ╨ ╩ ╪ ╫ ╬ ╭ ╮ ╯ 
9584 - 9599:	╰ ╱ ╲ ╳ ╴ ╵ ╶ ╷ ╸ ╹ ╺ ╻ ╼ ╽ ╾ ╿ 
9600 - 9615:	▀ ▁ ▂ ▃ ▄ ▅ ▆ ▇ █ ▉ ▊ ▋ ▌ ▍ ▎ ▏ 
9616 - 9631:	▐ ░ ▒ ▓ ▔ ▕ ▖ ▗ ▘ ▙ ▚ ▛ ▜ ▝ ▞ ▟ 
9632 - 9647:	■ □ ▢ ▣ ▤ ▥ ▦ ▧ ▨ ▩ ▪ ▫ ▬ ▭ ▮ ▯ 
9648 - 9663:	▰ ▱ ▲ △ ▴ ▵ ▶ ▷ ▸ ▹ ► ▻ ▼ ▽ ▾ ▿ 
9664 - 9679:	◀ ◁ ◂ ◃ ◄ ◅ ◆ ◇ ◈ ◉ ◊ ○ ◌ ◍ ◎ ● 
9680 - 9695:	◐ ◑ ◒ ◓ ◔ ◕ ◖ ◗ ◘ ◙ ◚ ◛ ◜ ◝ ◞ ◟ 
9696 - 9711:	◠ ◡ ◢ ◣ ◤ ◥ ◦ ◧ ◨ ◩ ◪ ◫ ◬ ◭ ◮ ◯ 
9712 - 9727:	◰ ◱ ◲ ◳ ◴ ◵ ◶ ◷ ◸ ◹ ◺ ◻ ◼ ◽ ◾ ◿ 
9728 - 9743:	☀ ☁ ☂ ☃ ☄ ★ ☆ ☇ ☈ ☉ ☊ ☋ ☌ ☍ ☎ ☏ 
9744 - 9759:	☐ ☑ ☒ ☓ ☔ ☕ ☖ ☗ ☘ ☙ ☚ ☛ ☜ ☝ ☞ ☟ 
9760 - 9775:	☠ ☡ ☢ ☣ ☤ ☥ ☦ ☧ ☨ ☩ ☪ ☫ ☬ ☭ ☮ ☯ 
     */
