package br.com.consultorio.dao;

import br.com.consultorio.model.Agendamento;
import br.com.consultorio.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

public class AgendamentoDAO {
        public void salvar(Agendamento agendamento) {
        String sql = "INSERT INTO tbl_agendamentos (pacienteid, data_hora_consulta, motivo_consulta, status_agendamento) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, agendamento.getPaciente().getId());
            pstmt.setTimestamp(2, Timestamp.valueOf(agendamento.getDataHoraConsulta()));
            pstmt.setString(3, agendamento.getMotivoConsulta());
            pstmt.setString(4, agendamento.getStatus());
            
            pstmt.execute();
            JOptionPane.showMessageDialog(null, "Agendamento salvo com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar agendamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
