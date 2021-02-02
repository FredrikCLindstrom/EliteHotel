package Main;

public interface Rankable<T> {

    /*
     * Compares this object with the specified object for ranking order. Returns a
     * negative integer, zero, or a positive integer as this object has lower ranking points, equal, 
     * or greater ranking points than the specified object.
     */
    //public int compareRank(T obj);

    /*
    *  Returns ranking points relative to other objects of the same class   
     */
    public int rankingPoints();
}
