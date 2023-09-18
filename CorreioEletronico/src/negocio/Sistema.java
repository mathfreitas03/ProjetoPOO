package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dados.Usuario;

public class Sistema {

    private static void menuLogado(Usuario usuario, List<Usuario> usuarios) {
        Scanner input = new Scanner(System.in);
        int op = 0;
        boolean sair = false;

        while (!sair) {
            System.out.println("Bem-vindo, " + usuario.getNome());
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Novo Email");
            System.out.println("2 - Listar emails recebidos");
            System.out.println("3 - Responder emails");
            System.out.println("4 - Excluir emails recebidos");
            System.out.println("5 - Ver perfil");
            System.out.println("6 - Sair");

            if (input.hasNextInt()) {
                op = input.nextInt();
                input.nextLine();

                switch (op) {
                    case 1:
                        usuario.enviarEmail(usuarios);
                        break;
                    case 2:
                        usuario.listarEmailsRecebidos();
                        break;
                    case 3:
                        usuario.responderEmail(usuarios);
                        break;
                    case 4:
                        usuario.apagarEmailRecebido();
                        break;
                    case 5:
                        System.out.println("Perfil do usuário:");
                        System.out.println("Nome: " + usuario.getNome());
                        System.out.println("Email: " + usuario.getEmailPessoal());
                        System.out.println("Digite qualquer coisa para voltar ao menu:");
                        input.nextLine();
                        break;
                    case 6:
                        System.out.println("Saindo...");
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Opção inválida. Digite um número válido.");
                input.next();
            }
        }
    }

    private static void menu(Sistema sistema, List<Usuario> usuarios){
        Scanner input = new Scanner(System.in);
        int op = 0;
        boolean sair = false;
            while (!sair) {
            System.out.println("Digite uma opção:");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Fazer Login");
            System.out.println("-1 - Sair do Programa");

            if (input.hasNextInt()) {
                op = input.nextInt();
                input.nextLine();

                switch (op) {
                    case 1:
                        Usuario novoUsuario = Usuario.cadastrarUsuario();
                        boolean emailJaCadastrado = false;

                        for (Usuario usuario : usuarios) {
                            if (novoUsuario.getEmailPessoal().equals(usuario.getEmailPessoal())) {
                                emailJaCadastrado = true;
                                System.out.println("O email inserido já foi cadastrado...");
                                break;
                            }
                        }

                        if (!emailJaCadastrado) {
                            usuarios.add(novoUsuario);
                            System.out.println("Usuário cadastrado com sucesso!");
                        }
                    break;
                    case 2:
                        System.out.print("Digite o email: ");
                        String emailUsuario = input.nextLine();
                        System.out.print("Digite a senha: ");
                        String senha = input.nextLine();

                        Usuario usuarioLogado = Usuario.fazerLogin(emailUsuario, senha, usuarios);

                        if (usuarioLogado != null) {
                            System.out.println("Login realizado com sucesso");
                            menuLogado(usuarioLogado, usuarios);
                        } else {
                            System.out.println("Usuário e/ou senha incorretos");
                        }
                        break;
                        
                    case -1:
                        System.out.println("Saindo do programa...");
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Opção inválida. Digite um número válido.");
                input.next();
            }
        }

        input.close();
    }
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner input = new Scanner(System.in);
        List<Usuario> usuarios = new ArrayList<>();

        menu(sistema, usuarios);
        input.close();
    }
}