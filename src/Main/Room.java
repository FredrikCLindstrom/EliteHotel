package Main;

public class Room implements Ranking, Rankable<Room> {

    public static int firstFreeRoomNr = 1;

    public int roomNr;
    public String name;

    public int beds;
    public String bedName;
    public boolean acEquipped;
    public boolean breakfastIncluded;
    public int chargePerDay;
    public int rankingPoints;

    public Guest guest;

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getRoomNr() {
        return roomNr;
    }

    public String getName() {
        return name;
    }

    public int getBeds() {
        return beds;
    }

    public String getBedName() {
        return bedName;
    }

    public boolean isAcEquipped() {
        return acEquipped;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public int getChargePerDay() {
        return chargePerDay;
    }

    public Guest getGuest() {
        return guest;
    }

    public Guest getRankingPoints() {
        return guest;
    }

    @Override
    public String toString() {
        String str = "Room " + this.roomNr + ", " + this.name + ", " + this.beds + " " + this.bedName + ", ";
        str += (this.acEquipped ? "AC, " : "no AC, ");
        str += (this.breakfastIncluded ? "breakfast included, " : "no breakfast, ");
        str += "cost " + chargePerDay + ", ";
        str += (this.guest != null) ? guest.toString() : "unoccupied";
        return str;
    }

    public String descriptionOfRooms() {
        String str = this.name + ", " + this.beds + " " + this.bedName + ", ";
        str += (this.acEquipped ? "AC, " : "no AC, ");
        str += (this.breakfastIncluded ? "breakfast included, " : "no breakfast, ");
        str += "cost " + chargePerDay + ", ";

        return str;
    }

    public int rankingPoints() { // Ranking points based on room numbers/location, adjustments for type/klass of room can be made in subclasses
        
        int returnValue = 1000; // Base value to be adjusted
        
        if (this.roomNr % 2 == 0) { // Even room numbers are on the backside, and hava a slightly better view
            returnValue += 200;
        }
        // The corner rooms (first and last two room numbers) with an extra window:
        if (this.roomNr == 1 || this.roomNr == 2 || this.roomNr == (firstFreeRoomNr-1) || this.roomNr == (firstFreeRoomNr-2)) {
                   returnValue += 300;
        }
        if (this.roomNr == 4) { // The room under the AC unit:
            returnValue -= 150;
        }
        if (this.roomNr == 7) { // The stain in the carpet is still slightly visible:
            returnValue -= 80;
        }  
        returnValue -= (5 * Math.abs(this.roomNr - 7)); // Adjusting for distance from the Reception:
        
        return returnValue;
    }  
    
}
