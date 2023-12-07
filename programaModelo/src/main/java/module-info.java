module com.modelo.programamodelo {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;

  opens com.modelo.programamodelo to javafx.fxml;
  exports com.modelo.programamodelo;
  exports com.modelo.programamodelo.controller;
  opens com.modelo.programamodelo.controller to javafx.fxml;

  exports com.modelo.programamodelo.model;
  opens com.modelo.programamodelo.model to javafx.fxml;
}