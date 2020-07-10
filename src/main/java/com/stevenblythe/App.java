package com.stevenblythe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, JavaFX");
        Scene scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Colony colony = new Colony(10, 10);
        colony.spawnCellAt(2, 2);
        colony.spawnCellAt(2, 3);
        colony.spawnCellAt(2, 4);
        colony.showColony();
        colony.evolve();
        colony.showColony();
        //launch();
    }
}