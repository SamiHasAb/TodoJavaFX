module org.example.fxcontrolles {
  requires javafx.controls;
  requires javafx.fxml;

  opens org.example.fxcontrolles to javafx.fxml;
  exports org.example.fxcontrolles;
}