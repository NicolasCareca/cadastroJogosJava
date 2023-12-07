package com.modelo.programamodelo.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class banco {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jogos";
        String user = "root";
        String password = "123456";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão com o banco de dados estabelecida!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
