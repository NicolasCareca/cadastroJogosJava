package com.modelo.programamodelo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jogoController {
    private static final String URL = "jdbc:mysql://localhost:3306/jogos";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";

    @FXML
    private TextField tituloField;

    @FXML
    private TextField generoField;

    @FXML
    private TextField plataformaField;






    @FXML
    private void cadastrarJogo(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "INSERT INTO jogo (titulo, genero, plataforma) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, tituloField.getText());
                statement.setString(2, generoField.getText());
                statement.setString(3, plataformaField.getText());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    exibirAlerta("Cadastro de Jogo", "Jogo cadastrado com sucesso!");
                    limparCampos();
                } else {
                    exibirAlerta("Erro", "Erro ao cadastrar o jogo.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            exibirAlerta("Erro", "Erro ao conectar ao banco de dados.");
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        tituloField.clear();
        generoField.clear();
        plataformaField.clear();
    }
    @FXML
    private void mostrarLista(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/modelo/programamodelo/listaJogos.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Cadastro de Produtos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
