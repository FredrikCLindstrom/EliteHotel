package Main;

public class StandardSingleRoom extends Room {

    public StandardSingleRoom() {
        this.roomNr = firstFreeRoomNr++;
        this.name = "Standard Single Room";

        this.beds = 1; // One single bed";
        this.bedName = "Twin bed";
        this.acEquipped = true;
        this.breakfastIncluded = true;
        this.chargePerDay = 2500;  // SEK 

        this.guest = null;
    }

    public StandardSingleRoom(String name, int beds, String bedName, boolean acEquipped, boolean breakfastIncluded, int chargePerDay) {

        this.roomNr = firstFreeRoomNr++;
        this.name = "Standard Single Room";

        this.beds = 1;
        this.bedName = bedName;
        this.acEquipped = acEquipped;
        this.breakfastIncluded = breakfastIncluded;
        this.chargePerDay = chargePerDay;

        this.guest = null;
    }
}
