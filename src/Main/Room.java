package Main;

import java.util.List;

public class Room implements Ranking, Rankable<Room> {

    public static final int LOWEST_ROOM_NUMBER = 1;

    public static int firstFreeRoomNr = LOWEST_ROOM_NUMBER; // Class variable

    public int roomNr;
    public String name;

    public int beds;
    public String bedName;
    public boolean acEquipped;
    public boolean breakfastIncluded;
    public int chargePerDay;
    public int rankingPoints;

    public Guest guest;

    static public int getNrOfCreatedRooms() { // Class method 
        return firstFreeRoomNr - 1;
    }

    static public int getClassName() { // Class method 
        return firstFreeRoomNr - 1;
    }

    static public Room getRoomWithThisNr(int roomNr, List<Room> roomList) { // Class method 

        Room foundRoom = null;
        for (Room room : roomList) {
            if (room.getRoomNr() == roomNr) {
                foundRoom = room;
            }
        }
        return foundRoom;
    }

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
        /* 
        String str = "Room " + roomNr + ", " + name + ", " + beds + " " + bedName + ", ";
        str += (acEquipped ? "AC, " : "no AC, ");
        str += (breakfastIncluded ? "breakfast included, " : "no breakfast, ");
        str += "cost " + chargePerDay + ", ";
        str += (guest != null) ? guest.toString() : "unoccupied";
         */
        String str = String.format("Room %2d, %-20s, %1d %-13s, %5s, %-17s, cost/night%5d SEK, %s",
                roomNr, name, beds, bedName, (acEquipped ? "AC" : "no AC"),
                (breakfastIncluded ? "breakfast included" : "no breakfast"),
                chargePerDay,
                ((guest != null) ? guest.toString() : "unoccupied"));

        return str;
    }

    public String descriptionOfRooms() {

        String str = String.format("%-20s, %1d %-13s, %5s, %-17s, cost/night%5d SEK",
                name, beds, bedName, (acEquipped ? "AC" : "no AC"),
                (breakfastIncluded ? "breakfast included" : "no breakfast"),
                chargePerDay);

        return str;
    }

    @Override
    public int rankingPoints() { // Ranking points based on room numbers/location, adjustments for type/klass of room can be made in subclasses

        int returnValue = 1000; // Base value to be adjusted   

        if ((roomNr <= 6) && ((roomNr % 2) == 1) || (roomNr >= 7) && ((roomNr % 2) == 0)) { // Odd room indexes up to 5, and even rooms över 6 have better views
            returnValue += 200;
        }
        // The last two rooms are corner rooms with an extra window and better views:
        if (roomNr == (getNrOfCreatedRooms()) || roomNr == (getNrOfCreatedRooms() - 1)) {
            returnValue += 300;
        }
        if (roomNr == 5) { // The room under the AC unit:
            returnValue -= 150;
        }
        if (roomNr == 7) { // The stain in the carpet is still slightly visible:
            returnValue -= 80;
        }
        returnValue -= 5 * (Math.abs(roomNr - 7)); // Adjusting for distance from the reception, dining room & lounge

        return returnValue;
    }

}
