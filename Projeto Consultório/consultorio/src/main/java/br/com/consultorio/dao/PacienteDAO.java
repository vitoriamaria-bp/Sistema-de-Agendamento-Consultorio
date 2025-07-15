package br.com.consultorio.dao;

import br.com.consultorio.model.Endereco;
import br.com.consultorio.model.Paciente;
import br.com.consultorio.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PacienteDAO {

    public void salvar(Paciente paciente) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        int enderecoId = enderecoDAO.salvar(paciente.getEndereco());

        if (enderecoId == -1) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o endereço do paciente.");
            return;
        }

        String sql = "INSERT INTO tbl_pacientes (nomecompleto, datanascimento, telefone, email, cpf, enderecoid) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, paciente.getNomeCompleto());
            pstmt.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            pstmt.setString(3, paciente.getTelefone());
            pstmt.setString(4, paciente.getEmail());
            pstmt.setString(5, paciente.getCpf());
            pstmt.setInt(6, enderecoId);

            pstmt.execute();
            JOptionPane.showMessageDialog(null, "Paciente salvo com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Paciente> listar() {
        String sql = "SELECT pacienteid, nomecompleto, cpf, telefone, email FROM tbl_pacientes";
        List<Paciente> pacientes = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("pacienteid"));
                paciente.setNomeCompleto(rs.getString("nomecompleto"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEmail(rs.getString("email"));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            e.printStackTrace();
        }
        return pacientes;
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM tbl_pacientes p INNER JOIN tbl_enderecos e ON p.enderecoid = e.enderecoid WHERE p.pacienteid = ?";
        Paciente paciente = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setId(rs.getInt("enderecoid"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setLogradouro(rs.getString("logradouro"));
                    endereco.setNumero(rs.getString("numero"));
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setCidade(rs.getString("cidade"));
                    endereco.setEstado(rs.getString("estado"));

                    paciente = new Paciente();
                    paciente.setId(rs.getInt("pacienteid"));
                    paciente.setNomeCompleto(rs.getString("nomecompleto"));
                    paciente.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
                    paciente.setTelefone(rs.getString("telefone"));
                    paciente.setEmail(rs.getString("email"));
                    paciente.setCpf(rs.getString("cpf"));
                    paciente.setEndereco(endereco);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    public void atualizar(Paciente paciente) {
        String sqlEndereco = "UPDATE tbl_enderecos SET cep = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ? WHERE enderecoid = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlEndereco)) {
            
            pstmt.setString(1, paciente.getEndereco().getCep());
            pstmt.setString(2, paciente.getEndereco().getLogradouro());
            pstmt.setString(3, paciente.getEndereco().getNumero());
            pstmt.setString(4, paciente.getEndereco().getBairro());
            pstmt.setString(5, paciente.getEndereco().getCidade());
            pstmt.setString(6, paciente.getEndereco().getEstado());
            pstmt.setInt(7, paciente.getEndereco().getId());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar endereço: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        String sqlPaciente = "UPDATE tbl_pacientes SET nomecompleto = ?, datanascimento = ?, telefone = ?, email = ?, cpf = ? WHERE pacienteid = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlPaciente)) {

            pstmt.setString(1, paciente.getNomeCompleto());
            pstmt.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            pstmt.setString(3, paciente.getTelefone());
            pstmt.setString(4, paciente.getEmail());
            pstmt.setString(5, paciente.getCpf());
            pstmt.setInt(6, paciente.getId());
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }

        public void deletar(int id) {

        int enderecoId = -1;
        String sqlBuscaEndereco = "SELECT enderecoid FROM tbl_pacientes WHERE pacienteid = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlBuscaEndereco)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    enderecoId = rs.getInt("enderecoid");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar endereço do paciente: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        String sqlDeletaAgendamentos = "DELETE FROM tbl_agendamentos WHERE pacienteid = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlDeletaAgendamentos)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir agendamentos do paciente: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        String sqlDeletaPaciente = "DELETE FROM tbl_pacientes WHERE pacienteid = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlDeletaPaciente)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir paciente: " + e.getMessage());
            e.printStackTrace();
            return; 
        }

        if (enderecoId != -1) {
            String sqlDeletaEndereco = "DELETE FROM tbl_enderecos WHERE enderecoid = ?";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sqlDeletaEndereco)) {
                pstmt.setInt(1, enderecoId);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir endereço: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        JOptionPane.showMessageDialog(null, "Paciente e todos os seus dados foram excluídos com sucesso!");
    }
}