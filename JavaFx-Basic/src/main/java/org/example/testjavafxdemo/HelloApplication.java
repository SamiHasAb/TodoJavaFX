
package org.example.testjavafxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    /*
      Old way of leading a seen
     */
//    Parent root = FXMLLoader.load(getClass().getResource("GridPane-view-example.fxml" ));
//    Scene scene = new Scene(root, 1000, 275);
    /*
     * set GridPane by code. That is without the GridPane-view-example.fxml
     */
/*    GridPane root = new GridPane();
    root.setAlignment(Pos.CENTER);
    root.setHgap(10);
    root.setVgap(10);
    Scene scene = new Scene(root, 1000, 275);
    Label greeting = new Label("Welcome to JavaFX from GridPane java code!");
    greeting.setTextFill(Color.GREEN);
    greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
    root.getChildren().add(greeting);*/

    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1000, 270);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}