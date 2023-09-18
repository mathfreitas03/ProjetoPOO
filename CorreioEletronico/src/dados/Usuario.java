package dados;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Usuario {
    private String nome;
    private String senha;
    private List<Email> emails;
    private String emailPessoal;

    public Usuario() {
        emails = new ArrayList<>();
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

    public static Usuario cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        Usuario novoUsuario = new Usuario();

        System.out.print("Digite o nome do usuário: ");
        novoUsuario.setNome(scanner.nextLine());

        System.out.print("Digite a senha do usuário: ");
        novoUsuario.setSenha(scanner.nextLine());

        System.out.print("Digite o email do usuário: ");
        novoUsuario.setEmailPessoal(scanner.nextLine());

        return novoUsuario;
    }

    public static Usuario fazerLogin(String emailPessoal, String senha, List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmailPessoal().equals(emailPessoal) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        System.out.println("Usuário não encontrado");
        return null;
    }

        public void adicionarEmail(Email email) {
        emails.add(email);
    }

    public void enviarEmail(List<Usuario> usuarios) {
        Scanner scanner = new Scanner(System.in);
        String remetente = getEmailPessoal();
        System.out.println("Digite o email do destinatário:");
        String destinatario = scanner.nextLine();
        if(destinatario.equals(getEmailPessoal())){
            System.out.println("O destinatário não pode ser igual ao remetente");
            System.out.println("Digite qualquer coisa para voltar ao menu...");
            scanner.nextLine();
        }
        else{
        System.out.println("Digite a mensagem:");
        String corpo = scanner.nextLine();
        Email novoEmail = new Email(remetente, destinatario, corpo);
        for (Usuario usuario : usuarios) {
            if (usuario.getEmailPessoal().equals(destinatario)) {
                novoEmail.setCorpo(novoEmail.criptografar(corpo));
                usuario.adicionarEmail(novoEmail);
                emails.add(novoEmail);
                System.out.println("Email enviado com sucesso!");
                return;
                }
            }
        }
    }
    public void listarEmailsRecebidos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista de Emails Recebidos:");

        for (Email email : emails) {
            if (email.getDestinatario().equals(emailPessoal)) {
                System.out.println("email-ID: " + email.getId()); 
                System.out.println("De: " + email.getRemetente());
                email.setCorpo(email.descriptografar(email.getCorpo()));
                System.out.println("Mensagem: " + email.getCorpo());
                System.out.println("Data do Envio: " + email.getDiaFormatado() + "/" + email.getMesFormatado() + "/" + email.ano);
                 System.out.println("Hora do Envio: " + email.getHoraFormatada() + ":" + email.getMinutosFormatados());
                System.out.println("---------------------------------");
            }
        }
        System.out.println("Digite qualquer coisa para voltar ao menu:");
        scanner.nextLine();
    } 

    /*
    public void responderEmail(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecione o ID do email ao qual quer responder:");
        int idSelected = Integer.parseInt(scanner.nextLine());
        
        for(Email email:emails){
            if(email.getId()==idSelected){
                String remetente = getEmailPessoal();
                String destinatario = email.getDestinatario();
                System.out.println("Digite a mensagem: ");
                String resposta = scanner.nextLine();
                Email emailResposta = new Email(remetente, destinatario, resposta);
                emails.add(emailResposta);
            }
        }
    }
    Exception in thread "main" java.util.ConcurrentModificationException
        at java.util.ArrayList$Itr.checkForComodification(Unknown Source)
    */
    public void responderEmail(List<Usuario> usuarios) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Selecione o ID do email ao qual quer responder:");
        int idSelected = Integer.parseInt(scanner.nextLine());
        String remetente = getEmailPessoal();
        String destinatario = null;
    
        System.out.println("Digite sua resposta: ");
        String resposta = scanner.nextLine();
    
        for (Usuario usuario : usuarios) {
            List<Email> emailsUsuario = usuario.getEmails();
            for (int i = 0; i < emailsUsuario.size(); i++) {
                Email email = emailsUsuario.get(i);
                if (email.getId() == idSelected) {
                    destinatario = email.getRemetente();
                    Email emailResposta = new Email(remetente, destinatario, resposta);
                    usuario.adicionarEmail(emailResposta);
                    System.out.println("Email enviado com sucesso!");
                    return;
                }
            }
        }
        System.out.println("Email com o ID selecionado não encontrado.");
    }

    public void apagarEmailRecebido() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do email que gostaria de excluir:");
        int idSelected = Integer.parseInt(scanner.nextLine());

        Iterator<Email> iterator = emails.iterator();
        while (iterator.hasNext()) {
            Email email = iterator.next();
            if (idSelected == email.getId()) {
                iterator.remove();
                System.out.println("Email removido com sucesso.");
                return;
            }
        }
    }
}
