package com.modelo.programamodelo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaJogosController {

    private static final String URL = "jdbc:mysql://localhost:3306/jogos";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";

    @FXML
    private ListView<Jogo> listaJogosView;


    private static class Jogo {
        private String titulo;
        private String genero;
        private String plataforma;

        public Jogo(String titulo, String genero, String plataforma) {
            this.titulo = titulo;
            this.genero = genero;
            this.plataforma = plataforma;
        }

        @Override
        public String toString() {
            return titulo + " - " + genero + " - " + plataforma;
        }
    }

    @FXML
    private void initialize() {
        carregarJogosCadastrados();
    }

    private void carregarJogosCadastrados() {
        ObservableList<Jogo> jogos = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "SELECT titulo, genero, plataforma FROM jogo";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Jogo jogo = new Jogo(
                            resultSet.getString("titulo"),
                            resultSet.getString("genero"),
                            resultSet.getString("plataforma")
                    );
                    jogos.add(jogo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listaJogosView.setCellFactory(param -> new ListCell<Jogo>() {
            @Override
            protected void updateItem(Jogo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        listaJogosView.setItems(jogos);
    }

    @FXML
    private void voltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/modelo/programamodelo/cadastroJogo.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Cadastro de Produtos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
