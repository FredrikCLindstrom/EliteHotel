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
                    case 3:
                    case 10:
                    case 17:
                        roomNr = ((row - 3) * 2) / 7 + 1; //  Room 1 is written on row 3, room 3 on row 10, Room 5 on row 17
                        roomStr = "Room " + roomNr + "";
                        // Insert color code for RED or GREEN in the string, depending on if the room has a guast or not
                        // Misc.printDebug("Replace: '" + roomStr + "', guest: " + roomList.get(roomNr - 1).getGuest());
                        str = str.replace(roomStr, (roomList.get(roomNr - 1).getGuest() == null ? Misc.BR_GREEN : Misc.BR_RED) + roomStr);

                        roomNr = ((17 - row) / 7) * 2 + 7;  // Room 11 is written on row 3, room 9 on row 10, Room 7 on row 17
                        roomStr = "Room " + roomNr;
                        // Insert color code for RED or GREEN in the string, depending on if the room has a guast or not
                        // Misc.printDebug("Replace: '" + roomStr + "', guest: " + roomList.get(roomNr - 1).getGuest());
                        str = str.replace(roomStr, (roomList.get(roomNr - 1).getGuest() == null ? Misc.BR_GREEN : Misc.BR_RED) + roomStr);

                        roomNr = ((17 - row) / 7) * 2 + 8;  // Room 12 is written on row 3, room 10 on row 10, Room 8 on row 17
                        roomStr = "Room " + roomNr;
                        // Insert color code for RED or GREEN in the string, depending on if the room has a guast or not
                        // Misc.printDebug("Replace: '" + roomStr + "', guest: " + roomList.get(roomNr - 1).getGuest());

                        str = str.replace(roomStr, (roomList.get(roomNr - 1).getGuest() == null ? Misc.BR_GREEN : Misc.BR_RED) + roomStr);
                        break;
                    case 4: // Room 2
                    case 11: // Room 4
                    case 18: // Room 6
                        roomNr = (((row + 3) / 7) * 2);  //  Room 2 is written on row 4, room 4 on row 11, Room 6 on row 18
                        roomStr = "Room " + roomNr;
                        // Insert color code for RED or GREEN in the string, depending on if the room has a guast or not
                        // Misc.printDebug("Replace: '" + roomStr + "', guest: " + roomList.get(roomNr - 1).getGuest());
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
        "                                                          Entrance       "};

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
        "i                                                                                                                                     "};

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
/*
31 - 62:	   ! " # $ % & ' ( ) * + , - . / 0 1 2 3 4 5 6 7 8 9 : ; < = > 
63 - 94:	? @ A B C D E F G H I J K L M N O P Q R S T U V W X Y Z [ \ ] ^ 
95 - 126:	_ ` a b c d e f g h i j k l m n o p q r s t u v w x y z { | } ~ 
127 - 158:	                                
159 - 190:	   ¡ ¢ £ ¤ ¥ ¦ § ¨ © ª « ¬ ­ ® ¯ ° ± ² ³ ´ µ ¶ · ¸ ¹ º » ¼ ½ ¾ 
191 - 222:	¿ À Á Â Ã Ä Å Æ Ç È É Ê Ë Ì Í Î Ï Ð Ñ Ò Ó Ô Õ Ö × Ø Ù Ú Û Ü Ý Þ 
223 - 254:	ß à á â ã ä å æ ç è é ê ë ì í î ï ð ñ ò ó ô õ ö ÷ ø ù ú û ü ý þ 
255 - 286:	ÿ Ā ā Ă ă Ą ą Ć ć Ĉ ĉ Ċ ċ Č č Ď ď Đ đ Ē ē Ĕ ĕ Ė ė Ę ę Ě ě Ĝ ĝ Ğ 
287 - 318:	ğ Ġ ġ Ģ ģ Ĥ ĥ Ħ ħ Ĩ ĩ Ī ī Ĭ ĭ Į į İ ı Ĳ ĳ Ĵ ĵ Ķ ķ ĸ Ĺ ĺ Ļ ļ Ľ ľ 
319 - 350:	Ŀ ŀ Ł ł Ń ń Ņ ņ Ň ň ŉ Ŋ ŋ Ō ō Ŏ ŏ Ő ő Œ œ Ŕ ŕ Ŗ ŗ Ř ř Ś ś Ŝ ŝ Ş 
351 - 382:	ş Š š Ţ ţ Ť ť Ŧ ŧ Ũ ũ Ū ū Ŭ ŭ Ů ů Ű ű Ų ų Ŵ ŵ Ŷ ŷ Ÿ Ź ź Ż ż Ž ž 
383 - 414:	ſ ƀ Ɓ Ƃ ƃ Ƅ ƅ Ɔ Ƈ ƈ Ɖ Ɗ Ƌ ƌ ƍ Ǝ Ə Ɛ Ƒ ƒ Ɠ Ɣ ƕ Ɩ Ɨ Ƙ ƙ ƚ ƛ Ɯ Ɲ ƞ 
415 - 446:	Ɵ Ơ ơ Ƣ ƣ Ƥ ƥ Ʀ Ƨ ƨ Ʃ ƪ ƫ Ƭ ƭ Ʈ Ư ư Ʊ Ʋ Ƴ ƴ Ƶ ƶ Ʒ Ƹ ƹ ƺ ƻ Ƽ ƽ ƾ 
447 - 478:	ƿ ǀ ǁ ǂ ǃ Ǆ ǅ ǆ Ǉ ǈ ǉ Ǌ ǋ ǌ Ǎ ǎ Ǐ ǐ Ǒ ǒ Ǔ ǔ Ǖ ǖ Ǘ ǘ Ǚ ǚ Ǜ ǜ ǝ Ǟ 
479 - 510:	ǟ Ǡ ǡ Ǣ ǣ Ǥ ǥ Ǧ ǧ Ǩ ǩ Ǫ ǫ Ǭ ǭ Ǯ ǯ ǰ Ǳ ǲ ǳ Ǵ ǵ Ƕ Ƿ Ǹ ǹ Ǻ ǻ Ǽ ǽ Ǿ 
511 - 542:	ǿ Ȁ ȁ Ȃ ȃ Ȅ ȅ Ȇ ȇ Ȉ ȉ Ȋ ȋ Ȍ ȍ Ȏ ȏ Ȑ ȑ Ȓ ȓ Ȕ ȕ Ȗ ȗ Ș ș Ț ț Ȝ ȝ Ȟ 
543 - 574:	ȟ Ƞ ȡ Ȣ ȣ Ȥ ȥ Ȧ ȧ Ȩ ȩ Ȫ ȫ Ȭ ȭ Ȯ ȯ Ȱ ȱ Ȳ ȳ ȴ ȵ ȶ ȷ ȸ ȹ Ⱥ Ȼ ȼ Ƚ Ⱦ 
575 - 606:	ȿ ɀ Ɂ ɂ Ƀ Ʉ Ʌ Ɇ ɇ Ɉ ɉ Ɋ ɋ Ɍ ɍ Ɏ ɏ ɐ ɑ ɒ ɓ ɔ ɕ ɖ ɗ ɘ ə ɚ ɛ ɜ ɝ ɞ 
607 - 638:	ɟ ɠ ɡ ɢ ɣ ɤ ɥ ɦ ɧ ɨ ɩ ɪ ɫ ɬ ɭ ɮ ɯ ɰ ɱ ɲ ɳ ɴ ɵ ɶ ɷ ɸ ɹ ɺ ɻ ɼ ɽ ɾ 
639 - 670:	ɿ ʀ ʁ ʂ ʃ ʄ ʅ ʆ ʇ ʈ ʉ ʊ ʋ ʌ ʍ ʎ ʏ ʐ ʑ ʒ ʓ ʔ ʕ ʖ ʗ ʘ ʙ ʚ ʛ ʜ ʝ ʞ 
671 - 702:	ʟ ʠ ʡ ʢ ʣ ʤ ʥ ʦ ʧ ʨ ʩ ʪ ʫ ʬ ʭ ʮ ʯ ʰ ʱ ʲ ʳ ʴ ʵ ʶ ʷ ʸ ʹ ʺ ʻ ʼ ʽ ʾ 
703 - 734:	ʿ ˀ ˁ ˂ ˃ ˄ ˅ ˆ ˇ ˈ ˉ ˊ ˋ ˌ ˍ ˎ ˏ ː ˑ ˒ ˓ ˔ ˕ ˖ ˗ ˘ ˙ ˚ ˛ ˜ ˝ ˞ 
735 - 766:	˟ ˠ ˡ ˢ ˣ ˤ ˥ ˦ ˧ ˨ ˩ ˪ ˫ ˬ ˭ ˮ ˯ ˰ ˱ ˲ ˳ ˴ ˵ ˶ ˷ ˸ ˹ ˺ ˻ ˼ ˽ ˾ 
767 - 798:	˿ ̀ ́ ̂ ̃ ̄ ̅ ̆ ̇ ̈ ̉ ̊ ̋ ̌ ̍ ̎ ̏ ̐ ̑ ̒ ̓ ̔ ̕ ̖ ̗ ̘ ̙ ̚ ̛ ̜ ̝ ̞ 
799 - 830:	̟ ̠ ̡ ̢ ̣ ̤ ̥ ̦ ̧ ̨ ̩ ̪ ̫ ̬ ̭ ̮ ̯ ̰ ̱ ̲ ̳ ̴ ̵ ̶ ̷ ̸ ̹ ̺ ̻ ̼ ̽ ̾ 
831 - 862:	̿ ̀ ́ ͂ ̓ ̈́ ͅ ͆ ͇ ͈ ͉ ͊ ͋ ͌ ͍ ͎ ͏ ͐ ͑ ͒ ͓ ͔ ͕ ͖ ͗ ͘ ͙ ͚ ͛ ͜ ͝ ͞ 
863 - 894:	͟ ͠ ͡ ͢ ͣ ͤ ͥ ͦ ͧ ͨ ͩ ͪ ͫ ͬ ͭ ͮ ͯ Ͱ ͱ Ͳ ͳ ʹ ͵ Ͷ ͷ ͸ ͹ ͺ ͻ ͼ ͽ ; 
895 - 926:	Ϳ ΀ ΁ ΂ ΃ ΄ ΅ Ά · Έ Ή Ί ΋ Ό ΍ Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ 
927 - 958:	Ο Π Ρ ΢ Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ 
959 - 990:	ο π ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ Ϗ ϐ ϑ ϒ ϓ ϔ ϕ ϖ ϗ Ϙ ϙ Ϛ ϛ Ϝ ϝ Ϟ 
991 - 1022:	ϟ Ϡ ϡ Ϣ ϣ Ϥ ϥ Ϧ ϧ Ϩ ϩ Ϫ ϫ Ϭ ϭ Ϯ ϯ ϰ ϱ ϲ ϳ ϴ ϵ ϶ Ϸ ϸ Ϲ Ϻ ϻ ϼ Ͻ Ͼ 
1023 - 1054:	Ͽ Ѐ Ё Ђ Ѓ Є Ѕ І Ї Ј Љ Њ Ћ Ќ Ѝ Ў Џ А Б В Г Д Е Ж З И Й К Л М Н О 
1055 - 1086:	П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я а б в г д е ж з и й к л м н о 
1087 - 1118:	п р с т у ф х ц ч ш щ ъ ы ь э ю я ѐ ё ђ ѓ є ѕ і ї ј љ њ ћ ќ ѝ ў 
1119 - 1150:	џ Ѡ ѡ Ѣ ѣ Ѥ ѥ Ѧ ѧ Ѩ ѩ Ѫ ѫ Ѭ ѭ Ѯ ѯ Ѱ ѱ Ѳ ѳ Ѵ ѵ Ѷ ѷ Ѹ ѹ Ѻ ѻ Ѽ ѽ Ѿ 
1151 - 1182:	ѿ Ҁ ҁ ҂ ҃ ҄ ҅ ҆ ҇ ҈ ҉ Ҋ ҋ Ҍ ҍ Ҏ ҏ Ґ ґ Ғ ғ Ҕ ҕ Җ җ Ҙ ҙ Қ қ Ҝ ҝ Ҟ 
1183 - 1214:	ҟ Ҡ ҡ Ң ң Ҥ ҥ Ҧ ҧ Ҩ ҩ Ҫ ҫ Ҭ ҭ Ү ү Ұ ұ Ҳ ҳ Ҵ ҵ Ҷ ҷ Ҹ ҹ Һ һ Ҽ ҽ Ҿ 
1215 - 1246:	ҿ Ӏ Ӂ ӂ Ӄ ӄ Ӆ ӆ Ӈ ӈ Ӊ ӊ Ӌ ӌ Ӎ ӎ ӏ Ӑ ӑ Ӓ ӓ Ӕ ӕ Ӗ ӗ Ә ә Ӛ ӛ Ӝ ӝ Ӟ 
1247 - 1278:	ӟ Ӡ ӡ Ӣ ӣ Ӥ ӥ Ӧ ӧ Ө ө Ӫ ӫ Ӭ ӭ Ӯ ӯ Ӱ ӱ Ӳ ӳ Ӵ ӵ Ӷ ӷ Ӹ ӹ Ӻ ӻ Ӽ ӽ Ӿ 
1279 - 1310:	ӿ Ԁ ԁ Ԃ ԃ Ԅ ԅ Ԇ ԇ Ԉ ԉ Ԋ ԋ Ԍ ԍ Ԏ ԏ Ԑ ԑ Ԓ ԓ Ԕ ԕ Ԗ ԗ Ԙ ԙ Ԛ ԛ Ԝ ԝ Ԟ 
1311 - 1342:	ԟ Ԡ ԡ Ԣ ԣ Ԥ ԥ Ԧ ԧ Ԩ ԩ Ԫ ԫ Ԭ ԭ Ԯ ԯ ԰ Ա Բ Գ Դ Ե Զ Է Ը Թ Ժ Ի Լ Խ Ծ 
1343 - 1374:	Կ Հ Ձ Ղ Ճ Մ Յ Ն Շ Ո Չ Պ Ջ Ռ Ս Վ Տ Ր Ց Ւ Փ Ք Օ Ֆ ՗ ՘ ՙ ՚ ՛ ՜ ՝ ՞ 
1375 - 1406:	՟ ՠ ա բ գ դ ե զ է ը թ ժ ի լ խ ծ կ հ ձ ղ ճ մ յ ն շ ո չ պ ջ ռ ս վ 
1407 - 1438:	տ ր ց ւ փ ք օ ֆ և ֈ ։ ֊ ֋ ֌ ֍ ֎ ֏ ֐ ֑ ֒ ֓ ֔ ֕ ֖ ֗ ֘ ֙ ֚ ֛ ֜ ֝ ֞ 
1439 - 1470:	֟ ֠ ֡ ֢ ֣ ֤ ֥ ֦ ֧ ֨ ֩ ֪ ֫ ֬ ֭ ֮ ֯ ְ ֱ ֲ ֳ ִ ֵ ֶ ַ ָ ֹ ֺ ֻ ּ ֽ ־ 
1471 - 1502:	ֿ ׀ ׁ ׂ ׃ ׄ ׅ ׆ ׇ ׈ ׉ ׊ ׋ ׌ ׍ ׎ ׏ א ב ג ד ה ו ז ח ט י ך כ ל ם מ 
1567 - 1598:	؟ ؠ ء آ أ ؤ إ ئ ا ب ة ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ػ ؼ ؽ ؾ 
1599 - 1630:	ؿ ـ ف ق ك ل م ن ه و ى ي ً ٌ ٍ َ ُ ِ ّ ْ ٓ ٔ ٕ ٖ ٗ ٘ ٙ ٚ ٛ ٜ ٝ ٞ 
1631 - 1662:	ٟ ٠ ١ ٢ ٣ ٤ ٥ ٦ ٧ ٨ ٩ ٪ ٫ ٬ ٭ ٮ ٯ ٰ ٱ ٲ ٳ ٴ ٵ ٶ ٷ ٸ ٹ ٺ ٻ ټ ٽ پ 
1663 - 1694:	ٿ ڀ ځ ڂ ڃ ڄ څ چ ڇ ڈ ډ ڊ ڋ ڌ ڍ ڎ ڏ ڐ ڑ ڒ ړ ڔ ڕ ږ ڗ ژ ڙ ښ ڛ ڜ ڝ ڞ 
1695 - 1726:	ڟ ڠ ڡ ڢ ڣ ڤ ڥ ڦ ڧ ڨ ک ڪ ګ ڬ ڭ ڮ گ ڰ ڱ ڲ ڳ ڴ ڵ ڶ ڷ ڸ ڹ ں ڻ ڼ ڽ ھ 
1727 - 1758:	ڿ ۀ ہ ۂ ۃ ۄ ۅ ۆ ۇ ۈ ۉ ۊ ۋ ی ۍ ێ ۏ ې ۑ ے ۓ ۔ ە ۖ ۗ ۘ ۙ ۚ ۛ ۜ ۝ ۞ 
1759 - 1790:	۟ ۠ ۡ ۢ ۣ ۤ ۥ ۦ ۧ ۨ ۩ ۪ ۫ ۬ ۭ ۮ ۯ ۰ ۱ ۲ ۳ ۴ ۵ ۶ ۷ ۸ ۹ ۺ ۻ ۼ ۽ ۾ 



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
