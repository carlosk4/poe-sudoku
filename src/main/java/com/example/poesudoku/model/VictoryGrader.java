package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.SIZE;

/**
 * Calculates a victory grade from used hints.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class VictoryGrader {

    /**
     * Calculates the grade for a hint count.
     *
     * @param hintsUsed used hint count
     * @return grade label
     */
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

    /**
     * Calculates the hint usage percentage.
     *
     * @param hintsUsed used hint count
     * @return hint usage percentage
     */
    private double calculateHintPercentage(int hintsUsed) {
        return (hintsUsed * 100.0) / (SIZE * SIZE);
    }
}
