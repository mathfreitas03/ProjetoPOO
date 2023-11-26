package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import dados.Email;

public class EmailDAO {
    private Connection conexao = Conexao.getConexao();
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");

    public EmailDAO() throws SQLException, ClassNotFoundException{}

    public void inserirEmail(Email email) {
        try {
            String comando = "insert into email (corpo, assunto, remetente, destinatario, data, id_usuario) values (?, ?, ?, ?, ?, ?) returning id";
            try (PreparedStatement statement = conexao.prepareStatement(comando)) {          
                statement.setString(1, email.getCorpo());
                statement.setString(2, email.getAssunto());
                statement.setString(3, email.getRemetente());
                statement.setString(4, email.getDestinatario());
                LocalDateTime data = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(data); 
                statement.setTimestamp(5, timestamp);
                statement.setInt(6, email.getIdUsuario());   

                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        email.setId(rs.getInt(1));
                        
                    }
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    public void atualizarEmail(Email email) {
        try {
            String comando = "update email set corpo = ?, assunto = ?, remetente = ?, destinatario = ? where id = ?";
            try (PreparedStatement statement = conexao.prepareStatement(comando)) {
                statement.setString(1, email.getCorpo());
                statement.setString(2, email.getAssunto());
                statement.setString(3, email.getRemetente());
                statement.setString(4, email.getDestinatario());
                statement.setInt(5, email.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */

    public Email buscarEmailPorId(int id) {
        try {
            String sql = "select * from email where id = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Email email = new Email();
                        email.setId(resultSet.getInt("id"));
                        email.setCorpo(resultSet.getString("corpo"));
                        email.setAssunto(resultSet.getString("assunto"));
                        email.setRemetente(resultSet.getString("remetente"));
                        email.setDestinatario(resultSet.getString("destinatario"));
                        Timestamp timestamp = resultSet.getTimestamp("data");
                        if (timestamp != null) {
                            LocalDateTime dataLocal = timestamp.toLocalDateTime();
                            email.setData(dataLocal);
                        }
                        int diaFormatado = timestamp.toLocalDateTime().getDayOfMonth();
                        email.setDiaFormatado(diaFormatado);
                        return email;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Email> listarEmails() {
        List<Email> emails = new ArrayList<>();
        try {
            String sql = "select * from email";
            try (PreparedStatement statement = conexao.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Email email = new Email();
                    email.setId(resultSet.getInt("id"));
                    email.setCorpo(resultSet.getString("corpo"));
                    email.setAssunto(resultSet.getString("assunto"));
                    email.setRemetente(resultSet.getString("remetente"));
                    email.setDestinatario(resultSet.getString("destinatario"));
                    Timestamp timestamp = resultSet.getTimestamp("data");
                    if (timestamp != null) {
                        LocalDateTime dataLocal = timestamp.toLocalDateTime();
                        email.setData(dataLocal);
                    }
                    emails.add(email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public void excluirEmail(int id){
        try {
            String comando = "delete from email where id = ?";
            try (PreparedStatement statement = conexao.prepareStatement(comando)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
