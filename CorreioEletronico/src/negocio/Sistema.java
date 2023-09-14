package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dados.Email;
import dados.Usuario;

public class Sistema {
    public static void main (String[] args){
        Sistema sistema = new Sistema();
        Scanner input = new Scanner(System.in);
        List<Usuario> usuarios = new ArrayList<>();
        int op=0;

        while(op!=-1){
            System.out.println("Digite uma opcao");
            System.out.println("1 - Cadastrar Usuario");
            System.out.println("-1 - Sair do Programa");
            op = input.nextInt();

            switch (op) {
                case 1:
                    Usuario novoUsuario = Usuario.cadastrarUsuario();
                    usuarios.add(novoUsuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;
                case -1:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    input.next();
            }
        }
    }
}
