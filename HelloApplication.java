package com.example.demoproject1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 707, 487);

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 707, 487);

        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load(), 1000, 689);


        stage.setTitle("Profile Dashboard");
        stage.setScene(scene3);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}