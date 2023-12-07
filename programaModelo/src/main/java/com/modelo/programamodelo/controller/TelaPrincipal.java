package com.modelo.programamodelo.controller;

import com.modelo.programamodelo.HelloApplication;
import com.modelo.programamodelo.model.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaPrincipal {
  @FXML
  private Button btnEnviar;

  @FXML
  private TableView<Pessoa> tbPessoa;

  @FXML
  private TableColumn<Pessoa, Integer> clCodigo;

  @FXML
  private TableColumn<Pessoa, String> clNome;

  @FXML
  private TableColumn<Pessoa, String> clSobrenome;

  @FXML
  private Label welcomeText;

  @FXML
  void obterLinhaSelecionada(MouseEvent event) {
    if(tbPessoa.getSelectionModel().getSelectedItem()!=null){
      Pessoa p = tbPessoa.getSelectionModel().getSelectedItem();
      Alert dadosPessoa = new Alert(Alert.AlertType.INFORMATION);
      dadosPessoa.setTitle("Dados pessoa "+ p.getCodigo());
      dadosPessoa.setHeaderText("Id: "+p.getCodigo()+"\nNome: "+p.getNome()+" "+p.getSobrenome());
      dadosPessoa.setContentText("Dados pessoa");
      dadosPessoa.show();
      System.out.println("Nome: "+p.getNome()+" "+p.getSobrenome());
    }
  }

  @FXML
  protected void onHelloButtonClick() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(HelloApplication.class.getResource("telaDois.fxml"));
      Scene scene = new Scene(fxmlLoader.load());
      Stage stage = new Stage();
      stage.setTitle("Tela dois");
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e){
      System.out.println("Erro ao abrir o FXML");
    }
  }

  @FXML
  public void initialize(){
    //Inserindo imagens em botões
    File arquivo = new File("src/main/resources/com/modelo/programamodelo/img/game.png");
    Image imagem = new Image(arquivo.toURI().toString());
    btnEnviar.setGraphic(new ImageView(imagem));
    //Usando o table view
    //Configurando as colunas
    //Associar uma coluna da tabela a um atributo da classe
    clCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    clSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
    //Criando uma lista para adicionar elementos
    ObservableList<Pessoa> listaPessoa = FXCollections.observableArrayList();
    listaPessoa.add(new Pessoa(1,"Ana","Silva"));
    listaPessoa.add(new Pessoa(5,"João","Antunes"));
    listaPessoa.add(new Pessoa(6,"Jonas","Freitas"));
    //Associar a lista ao tableView
    tbPessoa.setItems(listaPessoa);
  }
}