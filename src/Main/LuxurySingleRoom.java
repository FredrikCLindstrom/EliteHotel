package Main;

public class LuxurySingleRoom extends Room {

    public LuxurySingleRoom() {
        this.roomNr = firstFreeRoomNr++;
        this.name = "Luxury Single Room";

        this.beds = 1; // One single bed";
        this.bedName = "Single bed";
        this.acEquipped = true;
        this.breakfastIncluded = true;
        this.chargePerDay = 3000;  // SEK 

        this.guest = null;
    }

    public LuxurySingleRoom(String name, int beds, String bedName, boolean acEquipped, boolean breakfastIncluded, int chargePerDay) {

        this.roomNr = firstFreeRoomNr++;
        this.name = "Luxury Single Room";

        this.beds = 1;
        this.bedName = bedName;
        this.beds = 1; // One single bed";
        this.acEquipped = acEquipped;
        this.breakfastIncluded = breakfastIncluded;
        this.chargePerDay = chargePerDay;

        this.guest = null;
    }
}
