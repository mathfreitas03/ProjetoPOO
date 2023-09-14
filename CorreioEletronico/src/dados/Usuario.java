package dados;
import java.util.Scanner;

public class Usuario {

    private String nome;
    private String email;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Usuario cadastrarUsuario() {
        
        Scanner scanner = new Scanner(System.in);
        Usuario novoUsuario = new Usuario();

        System.out.print("Digite o nome do usuário: ");
        novoUsuario.setNome(scanner.nextLine());

        System.out.print("Digite o email do usuário: ");
        novoUsuario.setEmail(scanner.nextLine());

        System.out.print("Digite a senha do usuário: ");
        novoUsuario.setSenha(scanner.nextLine());

        scanner.close();

        return novoUsuario;
    }
}
