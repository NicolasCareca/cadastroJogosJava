package com.modelo.programamodelo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cadastroUsuariosController {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldSenha;

    @FXML
    private Button cadastrarButton;

    @FXML
    private void cadastrarUsuario(ActionEvent event) {
        String nome = textFieldNome.getText();
        String email = textFieldEmail.getText();
        String senha = textFieldSenha.getText();

        // Verifica se algum dos campos está vazio
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            showAlert("Por favor, preencha todos os campos.");
            return; // Retorna sem prosseguir se algum campo estiver vazio
        }

        String url = "jdbc:mysql://localhost:3306/jogos";
        String usuarioBD = "root";
        String senhaBD = "123456";

        try {
            // isso aqui ta fazendo minha conexão com o banco
            Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);

            // ta inserindo na tabela usuario
            String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

            // Cria um PreparedStatement para executar a declaração SQL com parâmetros
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);

            // Executa a declaração SQL para inserir o novo usuário
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                showAlert("Cadastro de usuário realizado com sucesso!");
                voltar();
            } else {
                showAlert("Falha ao cadastrar usuário.");
            }

            // desliga a conexao com o banco
            pstmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Usuário");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/modelo/programamodelo/LoginScreen.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Cadastro de Produtos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


