package com.example.poesudoku;

import javafx.application.Application;
import com.example.poesudoku.controller.SceneNavigator;
import javafx.stage.Stage;

/**
 * Starts the Sudoku JavaFX application.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Launches the JavaFX runtime.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Configures and shows the primary application stage.
     *
     * @param primaryStage primary application stage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SUDOKU");
        primaryStage.setResizable(false);
        SceneNavigator.showMainMenu(primaryStage);
        primaryStage.show();
    }
}
