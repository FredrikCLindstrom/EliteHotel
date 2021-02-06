package Main;

import java.util.List;

public interface Ranking {

    // Method highestRanked() is a generic method that takes any class T that is Rankable (implements the Rankable interface with the rankingPoints() method)
    public static <T extends Rankable> T highestRanked(List<T> objList) {
        T highestRanked = null;

        // Check that there's a list with at least one object  
        if (objList != null && !objList.isEmpty()) {

            // finding the object with highest ranking points
            highestRanked = objList.get(0);

            for (T object : objList) {

                if (object.rankingPoints() > highestRanked.rankingPoints()) {
                    highestRanked = object;
                }
            }
            Misc.printDebug("Highest ranking points of " + highestRanked.getClass() + " in the list is " + highestRanked.rankingPoints() + " for obj.instance: " + highestRanked.toString());
        }
        return highestRanked;
    }
}
