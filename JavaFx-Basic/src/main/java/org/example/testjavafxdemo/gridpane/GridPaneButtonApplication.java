package org.example.testjavafxdemo.gridpane;
import javafx.scene.Scene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class GridPaneButtonApplication
    extends Application
{

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(GridPaneButtonApplication.class.getResource("GridPane-Button-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 700, 500);
    stage.setTitle("GridPane");
    stage.setScene(scene);
    stage.show();

  }

  public static void main(String[] args) {
    launch();
  }
}
