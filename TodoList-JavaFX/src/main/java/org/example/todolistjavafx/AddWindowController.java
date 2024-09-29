package org.example.todolistjavafx;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.todolistjavafx.datamodel.TodoData;
import org.example.todolistjavafx.datamodel.TodoItem;

public class AddWindowController {

  @FXML
  private TextField shortDescriptionField;

  @FXML
  private TextArea detailsArea;

  @FXML
  private DatePicker deadlinePicker;

  public TodoItem processResults() {
    String shortDescription = shortDescriptionField.getText().trim();
    String details = detailsArea.getText().trim();
    LocalDate deadlineValue = deadlinePicker.getValue();

    TodoItem newItem = new TodoItem(shortDescription, details, deadlineValue);
    TodoData.getInstance().addTodoItem(newItem);
    return newItem;
  }

}
