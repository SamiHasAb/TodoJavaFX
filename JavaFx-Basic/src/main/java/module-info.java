module org.example.testjavafxdemo {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires net.synedra.validatorfx;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires com.almasb.fxgl.all;


  opens org.example.testjavafxdemo to javafx.fxml;
  exports org.example.testjavafxdemo;
  exports org.example.testjavafxdemo.borderPane;
  opens org.example.testjavafxdemo.borderPane to javafx.fxml;
  opens org.example.testjavafxdemo.flowpane to javafx.fxml;
  exports org.example.testjavafxdemo.flowpane;
  opens org.example.testjavafxdemo.gridpane to javafx.fxml;
  exports org.example.testjavafxdemo.gridpane;
  opens org.example.testjavafxdemo.hbox to javafx.fxml;
  exports org.example.testjavafxdemo.hbox;
  exports org.example.testjavafxdemo.vbox;
  opens org.example.testjavafxdemo.vbox to javafx.fxml;
  exports org.example.testjavafxdemo.stackpane;
  opens org.example.testjavafxdemo.stackpane to javafx.fxml;
  exports org.example.testjavafxdemo.tilepane;
  opens org.example.testjavafxdemo.tilepane to javafx.fxml;
  exports org.example.testjavafxdemo.localexamples;
  opens org.example.testjavafxdemo.localexamples to javafx.fxml;
}