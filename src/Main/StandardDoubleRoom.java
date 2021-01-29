
package Main;

public class StandardDoubleRoom extends Room {

    public StandardDoubleRoom() {
        this.roomNr = firstFreeRoomNr++;
        this.name = "Standard Double Room";

        this.beds = 1; // One double bed";
        this.bedName = "Kingsize bed";
        this.acEquipped = true;
        this.breakfastIncluded = true;
        this.chargePerDay = 3500;  // SEK 

        this.guest = null;
    }

    public StandardDoubleRoom(String name, int beds, String bedName, boolean acEquipped, boolean breakfastIncluded, int chargePerDay) {

        this.roomNr = firstFreeRoomNr++;
        this.name = "Standard Double Room";

        this.beds = 1;
        this.bedName = bedName;
        this.acEquipped = acEquipped;
        this.breakfastIncluded = breakfastIncluded;
        this.chargePerDay = chargePerDay;

        this.guest = null;
    }
}
