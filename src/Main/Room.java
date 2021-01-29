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
        String str = "Nr " + this.roomNr + ", " + this.name + ", " + this.beds + " " + this.bedName + ", AC=" + this.acEquipped + ", Breakfast=" + 
                this.breakfastIncluded + ", charge " + this.chargePerDay + "; ";
        if (this.guest != null) {
            str += "guest: " + guest.toString();
        }
        return str;
    }
}
