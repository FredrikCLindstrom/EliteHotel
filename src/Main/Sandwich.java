
package Main;


public class Sandwich extends Food{
    
    public Sandwich(int roomNr) {
        super(roomNr);
        this.name = "Sandwich";
        this.cost = 100;
    }

    public Sandwich() {
        this.name = "Sandwich";
        this.cost = 100;
    }
    
     @Override
    public int rankingPoints() {
        //Adjusting the ranking points set by the parent class (based on location mainly), with lots of points based on the class of room
        return (super.rankingPoints() + 200);  // The popular & famous hotel sandwish
    }
   
    
}
