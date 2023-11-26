package apresentacao;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import exceptions.InvalidNickException;
import exceptions.NotEnoughCaractersException;
import exceptions.UserAlreadySignedException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import negocio.Sistema;

public class DialogCadastro extends JDialog {
    Sistema sistema = Sistema.getInstance();

    public DialogCadastro() {
        setTitle("Cadastro");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        JPanel panel = new JPanel(null);
       
        JLabel emailLabel = new JLabel("Email de usuário: ");
        JTextField emailTextField = new JTextField(20);
        JLabel userNameLabel = new JLabel("Nome de usuário: ");
        JTextField userNameTextField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Senha de usuário");
        JLabel instrucoes = new JLabel("Instruções:");
        JLabel instrucoesEmail = new JLabel("O email deve ser feito na forma 'usuario@email.com'");
        JLabel instrucoesSenha = new JLabel("Sua senha deve ter no mínimo oito caracteres");
        JPasswordField passwordTextField = new JPasswordField(20);
        JButton closeButton = new JButton("Fechar");
        JButton cadastrarButton = new JButton("Cadastrar");

        emailLabel.setBounds(10, 10, 150, 20);
        emailTextField.setBounds(160, 10, 200, 20);
        userNameLabel.setBounds(10, 40, 150, 20);
        userNameTextField.setBounds(160, 40, 200, 20);
        passwordLabel.setBounds(10, 70, 150, 20);
        passwordTextField.setBounds(160, 70, 200, 20);
        instrucoes.setBounds(10, 140, 200, 20);
        instrucoesEmail.setBounds(10, 160, 400, 20);
        instrucoesSenha.setBounds(10, 180, 400, 20);
        cadastrarButton.setBounds(10, 100, 150, 30);
        closeButton.setBounds(170, 100, 150, 30);

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String nome = userNameTextField.getText();
                String email = emailTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());

                try {
                    if (sistema.cadastrarUsuario(email, password, nome)) {
                        JOptionPane.showMessageDialog(panel, "Cadastro realizado com sucesso");
                        userNameTextField.setText("");
                        passwordTextField.setText("");
                        emailTextField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Não foi possível realizar o cadastro. Verifique os requisitos.");
                        userNameTextField.setText("");
                        passwordTextField.setText("");
                        emailTextField.setText("");
                    }
                } catch (InvalidNickException e1) {
                    JOptionPane.showMessageDialog(panel, "E-mail inválido. Utilize um e-mail no formato correto.");
                } catch (NotEnoughCaractersException e2) {
                    JOptionPane.showMessageDialog(panel, "Senha muito curta. A senha deve ter pelo menos 8 caracteres.");
                } catch (UserAlreadySignedException e3) {
                    JOptionPane.showMessageDialog(panel, "Usuário já cadastrado com este e-mail.");
                }
            }
        });

        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(userNameLabel);
        panel.add(userNameTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(instrucoes);
        panel.add(instrucoesEmail);
        panel.add(instrucoesSenha);
        panel.add(cadastrarButton);
        panel.add(closeButton);
        add(panel);
    }
}
