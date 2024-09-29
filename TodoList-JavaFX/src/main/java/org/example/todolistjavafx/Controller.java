package org.example.todolistjavafx;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Callback;
import org.example.todolistjavafx.datamodel.TodoData;
import org.example.todolistjavafx.datamodel.TodoItem;

public class Controller {

  private List<TodoItem> todoItems;

  @FXML
  private ListView<TodoItem> todoListView;

  @FXML
  private TextArea itemDetailsTextArea;

  @FXML
  private Label deadlineLabel;

  @FXML
  private BorderPane mainBorderPane;

  @FXML
  private ContextMenu listContextMenu;

  @FXML
  private ToggleButton filterToggleButton;

  private FilteredList<TodoItem> filteredList;

  private Predicate<TodoItem> wantAllItems;

  private Predicate<TodoItem> wantTodaysItems;

  public void initialize() {

    listContextMenu = new ContextMenu(); // right click list/menu
    MenuItem deleteMenuItem = new MenuItem("Delete");
    deleteMenuItem.setOnAction(actionEvent ->
        deleteItem(todoListView.getSelectionModel().getSelectedItem()));

    listContextMenu.getItems().addAll(deleteMenuItem);

    // Here we are listening for selection changes. i.e this code will run whenever there is a change in selection
    // (ChangeListener) This will display the details and deadLine in the text field for the current selected item
    todoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

      //if there is a new item entered it will be selected and displayed
      if (newValue != null) {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy"); // "d M yy");
        deadlineLabel.setText(df.format(item.getDeadline()));
      }

    });

    wantAllItems = new Predicate<TodoItem>() {
      @Override
      public boolean test(TodoItem todoItem) { // will return all items
        return true;
      }
    };

    wantTodaysItems = new Predicate<TodoItem>() {
      @Override
      public boolean test(TodoItem todoItem) { // will return items which is due today
        return (todoItem.getDeadline().equals(LocalDate.now()));
      }
    };

    filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), wantAllItems);

    SortedList<TodoItem> sortedList = new SortedList<TodoItem>(filteredList,
        new Comparator<TodoItem>() {
          @Override
          public int compare(TodoItem o1, TodoItem o2) {
            return o1.getDeadline().compareTo(o2.getDeadline());
          }
        });

//    todoListView.getItems().setAll(TodoData.getInstance().getTodoItems()); //  when using a normal java List
//    todoListView.setItems(TodoData.getInstance().getTodoItems()); // b/c it's a ObservableList
    todoListView.setItems(sortedList);
    todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    todoListView.getSelectionModel().selectFirst();

    //CellFactory (items color)
    // each item in the listView is a cell, we can customize each one by the help of the cell factory
    todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
      @Override
      public ListCell<TodoItem> call(ListView<TodoItem> param) {
        ListCell<TodoItem> cell = new ListCell<TodoItem>() {

          @Override
          protected void updateItem(TodoItem item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {   // if the cell is empty
              setText(null);


            } else {
              setText(item.getShortDescription());
              // all the todoItems that is before tomorrow is  due
              if (item.getDeadline().isBefore(LocalDate.now().plusDays(1))) {
                setTextFill(Color.RED);
                setAlignment(Pos.CENTER_RIGHT);
              } else if (item.getDeadline().equals(LocalDate.now().plusDays(1))) {
                setTextFill(Color.BROWN);
                setFont(Font.font(Font.getFamilies().get(15),FontPosture.ITALIC,20));

              }
            }
          }
        };

        cell.emptyProperty().addListener(
            (obs, wasEmpty, isNowEmpty) -> {
              if (isNowEmpty) {              // if the selected cell is empty then return a null (right click menu)
                cell.setContextMenu(null);
              } else {
                cell.setContextMenu(listContextMenu);
              }

            });
        return cell;
      }
    });
  }

  @FXML
  public void showNewItemDialog() {
    Dialog<ButtonType> dialog = new Dialog<>(); //Dialog generic type is "ButtonType"
    dialog.initOwner(mainBorderPane.getScene().getWindow());
    dialog.setTitle("Add New Todo Item");
    dialog.setHeaderText("Use this dialog to create a new todo item");
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("add-window-view.fxml"));
    try {
      dialog.getDialogPane().setContent(fxmlLoader.load());

    } catch (IOException e) {
      System.out.println("Couldn't load the dialog");
      e.printStackTrace();
      return;
    }
    // add OK(Save) and cancel buttons
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

    Optional<ButtonType> result = dialog.showAndWait(); //result will return button type
    if (result.isPresent() && result.get() == ButtonType.OK) {
      AddWindowController controller = fxmlLoader.getController();
      TodoItem newItem = controller.processResults(); // adding item into the list in memory
      //use this if it's a list but no need if it's a ObservableList
      // todoListView.getItems().setAll(TodoData.getInstance().getTodoItems()); // calling the list from memory
      todoListView.getSelectionModel().select(newItem); // put the cursor on  the newly created item
    }
  }

  // can be used to display the details and deadLine in the text field if added as an event listener (onMouseClick='handleClickListView')
//  @FXML
//  public void handleClickListView() {
//    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
//    itemDetailsTextArea.setText(item.getDetails());
//    deadlineLabel.setText(item.getDeadline().toString());
//  }

  @FXML
  public void handleKeyPressed(KeyEvent keyEvent) {
    TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      if (keyEvent.getCode().equals(KeyCode.DELETE)) {
        deleteItem(selectedItem);
      }
    }
  }

  public void deleteItem(TodoItem item) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Todo Item");
    alert.setHeaderText("Delete item: " + item.getShortDescription());
    alert.setContentText("Are you sure?  Press OK to confirm, or cancel to Back out.");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && (result.get() == ButtonType.OK)) {
      TodoData.getInstance().deleteTodoItem(item);
    }
  }

  @FXML
  public void handleFilterButton() {
    TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
    if (filterToggleButton.isSelected()) {
      filteredList.setPredicate(wantTodaysItems);
      if (filteredList.isEmpty()) {
        itemDetailsTextArea.clear();
        deadlineLabel.setText("");
      } else if (filteredList.contains(selectedItem)) {
        todoListView.getSelectionModel().select(selectedItem);
      } else {
        todoListView.getSelectionModel().selectFirst();
      }
    } else {
      filteredList.setPredicate(wantAllItems);
      todoListView.getSelectionModel().select(selectedItem);
    }
  }

  @FXML
  public void handleExit() {
    Platform.exit();

  }
}