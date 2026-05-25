package com.example.poesudoku;

import javafx.application.Application;
import com.example.poesudoku.controller.SceneNavigator;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SUDOKU");
        primaryStage.setResizable(false);
        SceneNavigator.showMainMenu(primaryStage);
        primaryStage.show();
    }
}