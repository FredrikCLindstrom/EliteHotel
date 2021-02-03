
package Main;


public class Soda extends Food{
    
    public Soda(int roomNr) {
        super(roomNr);
        this.name="Soda";
        this.cost=30;
    }

    public Soda() {
        this.name="Soda";
        this.cost=30;
    }
    
    @Override
    public int rankingPoints() {
        //Adjusting the ranking points set by the parent class (based on location mainly), with lots of points based on the class of room
        return (super.rankingPoints() + 0);  
    }
    
}
