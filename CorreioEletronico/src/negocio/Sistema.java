package negocio;

import apresentacao.Login;
import dados.Email;
import dados.Usuario;
import exceptions.InvalidNickException;
import exceptions.NotEnoughCaractersException;
import exceptions.UserAlreadySignedException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Sistema {
    private static List<Usuario> listaDeUsuarios = new ArrayList<>();

    public static List<Usuario> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    private static Sistema instance = null; 

    public static Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    public Sistema() {
        listaDeUsuarios = new ArrayList<>();
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
        listaDeUsuarios.add(u);
        return true;
    }

    public boolean verificarLogin(String email, String password) {
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getEmailPessoal().equals(email) && usuario.getSenha().equals(password)) {
                return true;
                }
            }
        return false;
        }
    
    public void enviarEmail(String remetente, String destinatario, String assunto, String corpo) {
        Email novoEmail = new Email(remetente, destinatario, corpo);
        novoEmail.setAssunto(assunto);
        
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getEmailPessoal().equals(destinatario)) {
                novoEmail.setCorpo(novoEmail.criptografar(corpo));
                usuario.getEmails().add(novoEmail); 
            }
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}