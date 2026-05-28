package com.example.poesudoku.model;

/**
 * Stores the result displayed after a victory.
 *
 * @param hintsUsed used hint count
 * @param grade victory grade
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public record VictoryResult(int hintsUsed, String grade) {
}
