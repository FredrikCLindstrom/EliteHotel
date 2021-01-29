package Main;

public class LuxuryDoubleRoom extends Room {

    public LuxuryDoubleRoom() {
        this.roomNr = firstFreeRoomNr++;
        this.name = "Luxury Double Room";

        this.beds = 1; // One double bed";
        this.bedName = "Kingsize bed";
        this.acEquipped = true;
        this.breakfastIncluded = true;
        this.chargePerDay = 4000;  // SEK 

        this.guest = null;
    }

    public LuxuryDoubleRoom(String name, int beds, String bedName, boolean acEquipped, boolean breakfastIncluded, int chargePerDay) {

        this.roomNr = firstFreeRoomNr++;
        this.name = "Luxury Double Room";

        this.beds = 1;
        this.bedName = bedName;
        this.acEquipped = acEquipped;
        this.breakfastIncluded = breakfastIncluded;
        this.chargePerDay = chargePerDay;

        this.guest = null;
    }
}
