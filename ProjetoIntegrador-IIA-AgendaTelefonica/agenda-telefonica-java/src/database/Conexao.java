package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

   private static final String URL =
        "jdbc:mysql://localhost:3306/agenda_telefonica"
        + "?useUnicode=true"
        + "&characterEncoding=UTF-8"
        + "&serverTimezone=America/Sao_Paulo";

    private static final String USUARIO = "root";

    private static final String SENHA = "root1234";

    public static Connection conectar() {

        try {

            return DriverManager.getConnection(
                    URL,
                    USUARIO,
                    SENHA
            );

        } catch (SQLException erro) {

            System.out.println("Erro ao conectar: " + erro.getMessage());

            return null;
        }
    }
}