package com.example.poesudoku.model;

/**
 * Stores the latest victory result for scene navigation.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public final class GameSessionResult {

    private static VictoryResult currentResult = new VictoryResult(0, "S+");

    /**
     * Prevents utility class instantiation.
     */
    private GameSessionResult() {
    }

    /**
     * Stores the current victory result.
     *
     * @param result victory result
     */
    public static void setCurrentResult(VictoryResult result) {
        currentResult = result;
    }

    /**
     * Returns the current victory result.
     *
     * @return current victory result
     */
    public static VictoryResult getCurrentResult() {
        return currentResult;
    }
}
