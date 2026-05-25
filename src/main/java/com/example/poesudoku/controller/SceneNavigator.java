package com.example.poesudoku.controller;

import com.example.poesudoku.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class SceneNavigator {

    private static final String MAIN_MENU_VIEW = "main-menu-view.fxml";
    private static final String SUDOKU_VIEW = "sudoku-view.fxml";
    private static final String VICTORY_VIEW = "victory-view.fxml";

    private SceneNavigator() {
    }

    public static void showMainMenu(Stage stage) {
        loadScene(stage, MAIN_MENU_VIEW);
    }

    public static void showSudokuGame(Stage stage) {
        loadScene(stage, SUDOKU_VIEW);
    }

    public static void showVictory(Stage stage) {
        loadScene(stage, VICTORY_VIEW);
    }

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
