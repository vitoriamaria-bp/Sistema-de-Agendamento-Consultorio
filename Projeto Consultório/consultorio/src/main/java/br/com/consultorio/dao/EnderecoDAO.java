package br.com.consultorio.dao;

import br.com.consultorio.model.Endereco;
import br.com.consultorio.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoDAO {

    // Salva um novo endereço no banco e retorna o ID gerado automaticamente.
    public int salvar(Endereco endereco) {
        // Query SQL para inserir um novo endereço. Os '?' são placeholders.
        String sql = "INSERT INTO tbl_enderecos (cep, logradouro, numero, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?)";
        int idGerado = -1; // Valor padrão em caso de falha.

        // O 'try-with-resources' garante que a conexão (conn) e o statement (pstmt)
        // sejam fechados automaticamente no final, mesmo que ocorra um erro.
        try (Connection conn = ConnectionFactory.getConnection();
             // Prepara a query SQL, informando que queremos recuperar as chaves geradas (o ID).
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Substitui os '?' pelos valores do objeto 'endereco'.
            pstmt.setString(1, endereco.getCep());
            pstmt.setString(2, endereco.getLogradouro());
            pstmt.setString(3, endereco.getNumero());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getEstado());

            // Executa o comando de inserção no banco.
            pstmt.executeUpdate();

            // Pega o resultado com as chaves geradas (neste caso, o 'enderecoid').
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                // Se houver um resultado, pega o primeiro valor (o ID).
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            // Imprime o erro no console para ajudar na depuração.
            e.printStackTrace();
        }
        // Retorna o ID do endereço que foi salvo, ou -1 se falhou.
        return idGerado;
    }
}