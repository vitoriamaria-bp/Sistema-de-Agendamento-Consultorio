package br.com.consultorio.dao;

import br.com.consultorio.model.Paciente;
import br.com.consultorio.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PacienteDAO {

    // Orquestra o processo de salvar um paciente.
    public void salvar(Paciente paciente) {
        // 1. Cria uma instância do EnderecoDAO para salvar o endereço primeiro.
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        // Chama o método salvar do EnderecoDAO e guarda o ID retornado.
        int enderecoId = enderecoDAO.salvar(paciente.getEndereco());

        // 2. Verifica se o endereço foi salvo com sucesso.
        if (enderecoId == -1) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o endereço do paciente.");
            return; // Interrompe a execução se o endereço não pôde ser salvo.
        }

        // 3. Se o endereço foi salvo, agora salva o paciente com o ID do endereço.
        String sql = "INSERT INTO tbl_pacientes (nomecompleto, datanascimento, telefone, email, cpf, enderecoid) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, paciente.getNomeCompleto());
            // Converte o LocalDate do Java para o java.sql.Date que o JDBC entende.
            pstmt.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            pstmt.setString(3, paciente.getTelefone());
            pstmt.setString(4, paciente.getEmail());
            pstmt.setString(5, paciente.getCpf());
            // Usa o ID do endereço que foi salvo no passo 1.
            pstmt.setInt(6, enderecoId);

            pstmt.execute();
            JOptionPane.showMessageDialog(null, "Paciente salvo com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
