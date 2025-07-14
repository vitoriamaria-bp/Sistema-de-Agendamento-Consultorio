package br.com.consultorio.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe responsável por criar e gerenciar a conexão com o banco de dados,
 * lendo as configurações de um arquivo externo (config.properties).
 */
public class ConnectionFactory {

public static Connection getConnection() {
        try {
            Properties props = carregarPropriedades();
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.err.println("Falha na conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static Properties carregarPropriedades() throws IOException {
        Properties props = new Properties();
        // ATUALIZADO: Esta é a forma correta de ler um arquivo que está dentro do projeto.
        // Ele o procura no "classpath", que é para onde a pasta 'src' é copiada.
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Não foi possível encontrar o arquivo config.properties");
                throw new IOException("Arquivo config.properties não encontrado no classpath");
            }
            props.load(input);
        }
        return props;
    }

}