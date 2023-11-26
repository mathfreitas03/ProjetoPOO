package persistencia;

import dados.Email;
import dados.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    private Connection conexao = Conexao.getConexao();

    public UsuarioDAO() throws SQLException, ClassNotFoundException{}

    public void inserirUsuario(Usuario usuario) {
        try {
            String comando = "insert into usuario (nome, senha, email_pessoal) values (?, ?, ?) returning id";
            try (PreparedStatement statement = conexao.prepareStatement(comando)) {
                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getSenha());
                statement.setString(3, usuario.getEmailPessoal());
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next()){
                        usuario.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        try {
            String comando = "update usuario set nome = ?, senha = ?, email_pessoal = ? where id = ?";
            try (PreparedStatement statement = conexao.prepareStatement(comando)) {
                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getSenha());
                statement.setString(3, usuario.getEmailPessoal());
                statement.setInt(4, usuario.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarUsuarioPorEmail(String emailPessoal){
        try {
            String sql = "select * from usuario where email_pessoal = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, emailPessoal);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(resultSet.getInt("id"));
                        usuario.setNome(resultSet.getString("nome"));
                        usuario.setSenha(resultSet.getString("senha"));
                        usuario.setEmailPessoal(resultSet.getString("email_pessoal"));
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(int id) {
        try {
            String comando = "select * from usuario where id = ?";
            try (PreparedStatement statement = conexao.prepareStatement(comando)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(resultSet.getInt("id"));
                        usuario.setNome(resultSet.getString("nome"));
                        usuario.setSenha(resultSet.getString("senha"));
                        usuario.setEmailPessoal(resultSet.getString("email_pessoal"));
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String comando = "select * from usuario";
            try (PreparedStatement statement = conexao.prepareStatement(comando);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(resultSet.getInt("id"));
                    usuario.setNome(resultSet.getString("nome"));
                    usuario.setSenha(resultSet.getString("senha"));
                    usuario.setEmailPessoal(resultSet.getString("email_pessoal"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public List<Email> listarEmails(int id, Usuario usuario) {
        List<Email> emails = new ArrayList<>();
        try {
            String comando = "select * from email where id_usuario = ?";
            try (PreparedStatement statement = conexao.prepareStatement(comando)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public Usuario verificarLogin(String email, String senha) {
        try {
            String comando = "select * from usuario where email_pessoal = ? and senha = ?";
            try (PreparedStatement statement = conexao.prepareStatement(comando)) {
                statement.setString(1, email);
                statement.setString(2, senha);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(resultSet.getInt("id"));
                        usuario.setNome(resultSet.getString("nome"));
                        usuario.setSenha(resultSet.getString("senha"));
                        usuario.setEmailPessoal(resultSet.getString("email_pessoal"));
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}