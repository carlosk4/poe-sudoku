package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class VictoryGrader {

    public String calculateGrade(int hintsUsed) {
        double percentage = calculateHintPercentage(hintsUsed);

        if (percentage == 0) {
            return "S+";
        }

        if (percentage <= 5) {
            return "S";
        }

        if (percentage <= 10) {
            return "A+";
        }

        if (percentage <= 15) {
            return "A";
        }

        if (percentage <= 20) {
            return "B+";
        }

        if (percentage <= 25) {
            return "B";
        }

        if (percentage <= 30) {
            return "C+";
        }

        if (percentage <= 40) {
            return "C";
        }

        if (percentage <= 50) {
            return "D";
        }

        return "F";
    }

    private double calculateHintPercentage(int hintsUsed) {
        return (hintsUsed * 100.0) / (SIZE * SIZE);
    }
}
