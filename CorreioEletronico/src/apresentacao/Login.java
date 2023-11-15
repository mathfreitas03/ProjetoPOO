package apresentacao;

import dados.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import negocio.Sistema;

public class Login extends JFrame{
    Sistema sistema = Sistema.getInstance();
    public Login() {
        Sistema sistema = Sistema.getInstance();
        final int DEFAULT_WIDTH = 800;
        final int DEFAULT_HEIGHT = 500;

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Correio Eletrônico");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icons/icons8-mail-96.png")));
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 10, 5);

        JLabel labelLogin = new JLabel("LOGIN");
        labelLogin.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(labelLogin, constraints);

        constraints.gridy = 1;
        JLabel userName = new JLabel("Email de usuário:");
        JTextField userEmailEntryField = new JTextField(15);
        constraints.gridwidth = 1;

        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(userName, constraints);
        layout.setConstraints(userEmailEntryField, constraints);

        constraints.gridy = 2;
        JLabel userPassword = new JLabel("Senha de usuário");
        JPasswordField userPasswordEntryField = new JPasswordField(15);
        layout.setConstraints(userPassword, constraints);
        layout.setConstraints(userPasswordEntryField, constraints);

        constraints.gridy = 3;
        JButton botaoCadastro = new JButton("Clique aqui para criar uma conta nova");
        botaoCadastro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                DialogCadastro dialog = new DialogCadastro();
                dialog.setLocationRelativeTo(null);
                dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icons/icons8-mail-96.png")));
                dialog.setSize(400, 250);
            }
        });

        constraints.gridy = 4;
        JButton botaoLogin = new JButton("Entrar");
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(botaoLogin, constraints);
        botaoLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String email = userEmailEntryField.getText();
                String senha = String.valueOf(userPasswordEntryField.getPassword());
                if(sistema.verificarLogin(email, senha)){
                    dispose();
                    Usuario usuario = new Usuario();
                    usuario.setEmailPessoal(email);
                    usuario.setSenha(senha);

                    for(Usuario u : Sistema.getListaDeUsuarios()){
                        if(email.equals(u.getEmailPessoal())){
                            usuario.setNome(u.getNome());
                        }
                    }
                    
                    Principal principal = new Principal(usuario);
                    principal.setVisible(true);
                    principal.atualizarListaEmails();
                    
                }
                else
                JOptionPane.showMessageDialog(panel, "Usuário ou senha incorretos");
            }
        });

        panel.add(labelLogin);
        panel.add(userName);
        panel.add(userEmailEntryField);
        panel.add(userPassword);
        panel.add(userPasswordEntryField);
        panel.add(botaoCadastro);
        panel.add(botaoLogin);

        add(panel, BorderLayout.CENTER);
    }
}