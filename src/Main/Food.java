
package Main;


public class Food implements Ranking, Rankable<Food> {
    
    public int roomNr;
    public String name;

    public int cost;

    public Food(int roomNr) {
        this.roomNr = roomNr;
        
    }

    public Food() {
    }

    
    
    public int getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(int roomNr) {
        this.roomNr = roomNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Food{" + "roomNr=" + roomNr + ", name=" + name + ", cost=" + cost + '}';
    }
    
    public String forReceiptPrintOut() {
        return "Food "+ name + ", cost: " + cost;
    }
       
    public int rankingPoints() { // Higher ranking points are better
        return 1000 ; // Base value to be adjusted by subclasses
    }  
       
}
