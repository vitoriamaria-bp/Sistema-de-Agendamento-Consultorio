package br.com.consultorio.dao;

import br.com.consultorio.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    
    public boolean verificarLogin(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM tbl_usuarios WHERE nomeusuario = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nomeUsuario);
            pstmt.setString(2, senha);
            
            try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}