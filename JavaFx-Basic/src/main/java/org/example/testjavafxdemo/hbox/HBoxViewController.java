package org.example.testjavafxdemo.hbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HBoxViewController extends Application {


  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HBox-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 700, 500);
    stage.setTitle("HBox");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
