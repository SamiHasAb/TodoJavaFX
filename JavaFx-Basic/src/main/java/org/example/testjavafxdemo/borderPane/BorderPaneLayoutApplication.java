package org.example.testjavafxdemo.borderPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BorderPaneLayoutApplication extends Application {


  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Border-Pane-Layout.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
    stage.setTitle("Border Pane Layout");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
