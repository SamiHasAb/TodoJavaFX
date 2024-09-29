package org.example.todolistjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import org.example.todolistjavafx.datamodel.TodoData;

public class TodoListApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 900, 500);
    stage.setTitle("Todo List");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void stop() throws Exception {
    try {
      TodoData.getInstance().saveToFile();

    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void init() throws Exception {
    try {
      TodoData.getInstance().loadFromFile();

    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
}