package Main;

public class Room {

    public static int firstFreeRoomNr = 1;

    public int roomNr;
    public String name;

    public int beds;
    public String bedName;
    public boolean acEquipped;
    public boolean breakfastIncluded;
    public int chargePerDay;

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

    @Override
    public String toString() {
        String str = "Room " + this.roomNr + ", " + this.name + ", " + this.beds + " " + this.bedName + ", ";
        str += (this.acEquipped ? "AC, " : "no AC, ");
        str += (this.breakfastIncluded ? "breakfast included, " : "no breakfast, ");
        str += "cost " + chargePerDay + ", ";
        str += (this.guest != null) ? guest.toString(): "unoccupied";
        return str;
    }
    
    public String descriptionOfRooms(){
        String str = this.name + ", " + this.beds + " " + this.bedName + ", ";
        str += (this.acEquipped ? "AC, " : "no AC, ");
        str += (this.breakfastIncluded ? "breakfast included, " : "no breakfast, ");
        str += "cost " + chargePerDay + ", ";
        
        return str;
    }
}
