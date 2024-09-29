package org.example.testjavafxdemo.vbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VBoxApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VBox-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 500, 300);
    stage.setTitle("VBox");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
