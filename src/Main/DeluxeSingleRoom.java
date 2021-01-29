package Main;

public class DeluxeSingleRoom extends Room {

    public DeluxeSingleRoom() {
        this.roomNr = firstFreeRoomNr++;
        this.name = "Luxury Single Room";

        this.beds = 1; // One single bed";
        this.bedName = "Single bed";
        this.acEquipped = true;
        this.breakfastIncluded = true;
        this.chargePerDay = 5111;  // SEK 

        this.guest = null;
    }

    public DeluxeSingleRoom(String name, int beds, String bedName, boolean acEquipped, boolean breakfastIncluded, int chargePerDay) {

        this.roomNr = firstFreeRoomNr++;
        this.name = "Deluxe Single Room";

        this.beds = 1;
        this.bedName = bedName;
        this.acEquipped = acEquipped;
        this.breakfastIncluded = breakfastIncluded;
        this.chargePerDay = chargePerDay;

        this.guest = null;
    }
}
