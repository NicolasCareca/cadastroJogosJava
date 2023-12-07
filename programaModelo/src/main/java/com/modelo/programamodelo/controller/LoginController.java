package com.modelo.programamodelo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField textFieldEmail;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private void login(ActionEvent event) {
        String email = textFieldEmail.getText();
        String senha = passwordFieldSenha.getText();
        // Verifica se algum dos campos está vazio
        if ( email.isEmpty() || senha.isEmpty()) {
            showAlert("Por favor, preencha todos os campos.");
            return;
        }
        String url = "jdbc:mysql://localhost:3306/jogos";
        String usuarioBD = "root";
        String senhaBD = "123456";

        try {
            Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);

            // verifica o email e minha senha pra poder logar
            if (verifyLogin(conexao, email, senha)) {
                showAlert("Login successful!");
                voltar();
            } else {
                showAlert("Login failed. Please check your credentials.");
            }

            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean verifyLogin(Connection conexao, String email, String senha) {
        // logica de verificação usando sql
        try {
            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);

            ResultSet resultSet = pstmt.executeQuery();

            // olha se ja tem usuario com as info inseridas
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/modelo/programamodelo/cadastroJogo.fxml"));

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro de Produtos");
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error ao voltar para a tela de cadastro de jogos.");
        }
    }

}
