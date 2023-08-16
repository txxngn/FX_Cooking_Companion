package com.example.groupassignment;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.fxml.FXMLLoader;
import com.example.groupassignment.Recipe;
import com.example.groupassignment.RecipeDAO;

import java.util.ArrayList;

public class HomeView extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("MainViewApp.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cooking Companion App");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading fxml " + e);
            e.printStackTrace();
        }
    }
}



