
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
    
}
