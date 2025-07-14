package br.com.consultorio.dao;

import br.com.consultorio.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    
    /**
     * Verifica no banco de dados se a combinação de usuário e senha existe.
     * @param nomeUsuario O nome de usuário a ser verificado.
     * @param senha A senha a ser verificada.
     * @return true se o login for válido, false caso contrário.
     */
    public boolean verificarLogin(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM tbl_usuarios WHERE nomeusuario = ? AND senha = ?";
        // O 'try-with-resources' garante que a conexão e o statement sejam fechados.
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Substitui os '?' pelos valores recebidos.
            pstmt.setString(1, nomeUsuario);
            pstmt.setString(2, senha);
            
            // Executa a consulta e obtém o resultado.
            try (ResultSet rs = pstmt.executeQuery()) {
                // rs.next() retorna true se houver pelo menos uma linha no resultado,
                // o que significa que o usuário foi encontrado.
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}