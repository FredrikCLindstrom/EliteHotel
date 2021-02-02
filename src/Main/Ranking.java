package Main;

import java.util.List;

public interface Ranking {

    public static <T extends Rankable> T highestRanked(List<T> objList) {

        T highestRanked = objList.get(0);
        for (T t : objList) {
            if (t.rankingPoints() > highestRanked.rankingPoints()) {
                highestRanked = t;
            }
        }
        Misc.printDebug("Highest ranking points is " + highestRanked.rankingPoints() + " for: " + highestRanked.toString());

        return highestRanked;
    }
}
