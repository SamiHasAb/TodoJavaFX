module org.example.todolistjavafx {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.xml;

  opens org.example.todolistjavafx to javafx.fxml;
  exports org.example.todolistjavafx;
}