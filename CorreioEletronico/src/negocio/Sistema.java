package negocio;

import apresentacao.Login;
import dados.Email;
import dados.Usuario;
import exceptions.InvalidNickException;
import exceptions.NotEnoughCaractersException;
import exceptions.UserAlreadySignedException;
import persistencia.EmailDAO;
import persistencia.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Sistema {
    private static List<Usuario> listaDeUsuarios = new ArrayList<>();
    private UsuarioDAO usuarioDAO;
    private EmailDAO emailDAO;
    
    public static List<Usuario> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    private static Sistema instance = null; 

    public static Sistema getInstance() {
        if (instance == null) {
            try {
                instance = new Sistema();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Sistema() throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
        emailDAO = new EmailDAO();
        listaDeUsuarios = new ArrayList<>(usuarioDAO.listarUsuarios());
    }

    public boolean cadastrarUsuario(String email, String senha, String nome) throws InvalidNickException, NotEnoughCaractersException, UserAlreadySignedException{
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
            
        if (!matcher.matches()) {
            throw new InvalidNickException();
        }

        if(senha.length() < 8) {
            throw new NotEnoughCaractersException();
        }

        for(Usuario usuario : listaDeUsuarios){
            if(usuario.getEmailPessoal().equals(email)){
                throw new UserAlreadySignedException();
            }
        }

        Usuario u = new Usuario();
        u.setEmailPessoal(email);
        u.setNome(nome);
        u.setSenha(senha);
        usuarioDAO.inserirUsuario(u);
        return true;
    }

    public boolean verificarLogin(String email, String password) {
        Usuario usuario = usuarioDAO.verificarLogin(email, password);
        return usuario != null;
    }
    
    public void enviarEmail(String remetente, String destinatario, String assunto, String corpo) {
        Email novoEmail = new Email(remetente, destinatario, corpo);
        novoEmail.setAssunto(assunto);
        Usuario usuarioDestinatario = usuarioDAO.buscarUsuarioPorEmail(destinatario);
        
        if (usuarioDestinatario != null) {
            novoEmail.setIdUsuario(usuarioDestinatario.getId());
            emailDAO.inserirEmail(novoEmail);
        }
    }

    public void excluirEmail(int id){
        emailDAO.excluirEmail(id);
    }

    public static void main(String[] args) throws SQLException {
        try {
            Login login;
            try {
                login = new Login();
                login.setVisible(true);
            } catch (ClassNotFoundException e) {
                
                e.printStackTrace();
            }
        } finally {
            
        }
    }
}