package Main;

public interface Rankable<T> {

    /*
    *  Returns ranking points for a objects higher is better, and the points 
    *  are relative to other objectgs of the same class
    */
    public int rankingPoints();
}
