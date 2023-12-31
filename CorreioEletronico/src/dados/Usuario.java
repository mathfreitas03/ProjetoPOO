package dados;

import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private List<Email> emails;
    private String emailPessoal;

    public Usuario() {
        emails = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailPessoal() {
        return emailPessoal;
    }

    public void setEmailPessoal(String emailPessoal) {
        this.emailPessoal = emailPessoal;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void removerEmail(Email email) {
        emails.remove(email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void adicionarEmail(Email email) {
        emails.add(email);
    }
}