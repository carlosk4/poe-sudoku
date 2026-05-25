package com.example.poesudoku.model;

public final class GameSessionResult {

    private static VictoryResult currentResult = new VictoryResult(0, "S+");

    private GameSessionResult() {
    }

    public static void setCurrentResult(VictoryResult result) {
        currentResult = result;
    }

    public static VictoryResult getCurrentResult() {
        return currentResult;
    }
}