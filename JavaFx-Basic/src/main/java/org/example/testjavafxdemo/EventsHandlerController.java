package org.example.testjavafxdemo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EventsHandlerController {

  @FXML
  public TextField nameField;
  @FXML
  public Button helloButton;
  @FXML
  public Button byeButton;
  @FXML
  public CheckBox checkBox;
  @FXML
  public Label updateLabel;

  // disable buttons on initialize
  public void initialize() {
    helloButton.setDisable(true);
    byeButton.setDisable(true);
  }

  public void onButtonClicked(ActionEvent event) {
    if (event.getSource().equals(helloButton)) {
      System.out.println("Hello " + nameField.getText());
    } else if (event.getSource().equals(byeButton)) {
      System.out.println("Bye " + nameField.getText());
    }

    // need to create another new thread inside the new thread to assign/update it to the main/old thread
    Runnable runnable = () -> {
      String threadType = Platform.isFxApplicationThread() ?
          "UI Thread" : "Background Thread";
      System.out.println("Sleep on the : " + threadType);
      try {
        Thread.sleep(10_000);
        Platform.runLater(() -> {
          String threadType1 = Platform.isFxApplicationThread() ?
              "UI Thread" : "Background Thread";
          System.out.println("Updating on the : " + threadType1);
          updateLabel.setText("Done updating!");
        });
      } catch (InterruptedException e) {

      }
    };

    new Thread(runnable).start();

    if (checkBox.isSelected()) {
      nameField.clear();
      helloButton.setDisable(true);
      byeButton.setDisable(true);
    }
  }

  public void handleKeyReleased() {
    String text = nameField.getText();
    boolean disableButtons = text.isBlank();
    helloButton.setDisable(disableButtons);
    byeButton.setDisable(disableButtons);
  }

  public void handleChange() {
    String checkBoxStatus = checkBox.isSelected() ?
        "checked" : "not checked";
    System.out.println("The checkbox is " + checkBoxStatus);
  }
}
