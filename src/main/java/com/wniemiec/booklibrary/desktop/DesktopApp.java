package com.wniemiec.booklibrary.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DesktopApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //ExampleQuery.run();

        URL resource = getClass().getResource("main_window.fxml");
        Parent root = FXMLLoader.load(resource);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Library");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
