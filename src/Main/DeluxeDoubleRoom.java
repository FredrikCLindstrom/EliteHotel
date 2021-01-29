
package Main;

public class DeluxeDoubleRoom extends Room {

    public DeluxeDoubleRoom() {
        this.roomNr = firstFreeRoomNr++;
        this.name = "Luxury Single Room";

        this.beds = 1; // One double bed";
        this.bedName = "Double bed";
        this.acEquipped = true;
        this.breakfastIncluded = true;
        this.chargePerDay = 5555;  // SEK 

        this.guest = null;
    }

    public DeluxeDoubleRoom(String name, int beds, String bedName, boolean acEquipped, boolean breakfastIncluded, int chargePerDay) {

        this.roomNr = firstFreeRoomNr++;
        this.name = "Deluxe Double Room";

        this.beds = 1;
        this.bedName = bedName;
        this.acEquipped = acEquipped;
        this.breakfastIncluded = breakfastIncluded;
        this.chargePerDay = chargePerDay;

        this.guest = null;
    }
}
