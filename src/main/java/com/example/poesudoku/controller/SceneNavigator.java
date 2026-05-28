package com.example.poesudoku.controller;

import com.example.poesudoku.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Loads and switches between application scenes.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public final class SceneNavigator {

    private static final String MAIN_MENU_VIEW = "main-menu-view.fxml";
    private static final String SUDOKU_VIEW = "sudoku-view.fxml";
    private static final String VICTORY_VIEW = "victory-view.fxml";

    /**
     * Prevents utility class instantiation.
     */
    private SceneNavigator() {
    }

    /**
     * Shows the main menu scene.
     *
     * @param stage target stage
     */
    public static void showMainMenu(Stage stage) {
        loadScene(stage, MAIN_MENU_VIEW);
    }

    /**
     * Shows the Sudoku game scene.
     *
     * @param stage target stage
     */
    public static void showSudokuGame(Stage stage) {
        loadScene(stage, SUDOKU_VIEW);
    }

    /**
     * Shows the victory scene.
     *
     * @param stage target stage
     */
    public static void showVictory(Stage stage) {
        loadScene(stage, VICTORY_VIEW);
    }

    /**
     * Loads an FXML file into the stage.
     *
     * @param stage target stage
     * @param fxmlFile fxml file name
     * @throws IllegalStateException when the scene cannot be loaded
     */
    private static void loadScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load scene: " + fxmlFile, exception);
        }
    }
}
