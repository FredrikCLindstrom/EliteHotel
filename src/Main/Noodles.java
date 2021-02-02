
package Main;


public class Noodles extends Food{
    
    public Noodles(int roomNr) {
        super(roomNr);
        this.name="Noodles";
        this.cost=50;
    }

    public Noodles() {
        this.name="Noodles";
        this.cost=50;
    }
    
    @Override
    public int rankingPoints() {
        //Adjusting the ranking points set by the parent class (based on location mainly), with lots of points based on the class of room
        return (super.rankingPoints() + 100);  // Quite popular
    }
    
    
}
