package com.modelo.programamodelo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TelaDois {

  @FXML
  private Label lblTexto;

  @FXML
  private Button btnClique;

  @FXML
  void clicou(ActionEvent event) {
    lblTexto.setText("Eu disse para não clicar seu teimoso, agora não pode clicar mais...");
    //btnClique.setDisable(true);
    btnClique.setVisible(false);
  }

}