package apresentacao;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import negocio.Sistema;
import dados.Usuario;
import exceptions.EmailToYourselfException;

public class DialogEnviarEmail extends JDialog {
    Sistema sistema = Sistema.getInstance();
    Usuario usuario;

    public DialogEnviarEmail(Usuario usuario) {

    this.usuario = usuario;

        setTitle("Novo Email");
        setLayout(null);

        JLabel destinatarioLabel = new JLabel("Para: ");
        JTextField destinatarioTextField = new JTextField(30);

        JLabel assuntoLabel = new JLabel("Assunto: ");
        JTextField assuntoTextField = new JTextField(30);

        JLabel mensagemLabel = new JLabel("Mensagem:");
        JTextArea mensagemTextArea = new JTextArea(40, 60);
        mensagemTextArea.setLineWrap(true);
        mensagemTextArea.setWrapStyleWord(true); 

        JButton closeButton = new JButton("Fechar");
        JButton enviarButton = new JButton("Enviar Email");

        destinatarioLabel.setBounds(10, 10, 80, 20);
        destinatarioTextField.setBounds(100, 10, 200, 20);

        assuntoLabel.setBounds(10, 40, 80, 20);
        assuntoTextField.setBounds(100, 40, 200, 20);

        mensagemLabel.setBounds(10, 70, 80, 20);
        mensagemTextArea.setBounds(100, 70, 400, 200);

        enviarButton.setBounds(10, 300, 200, 30);
        closeButton.setBounds(220, 300, 100, 30);

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        enviarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destinatario = destinatarioTextField.getText();
                String assunto = assuntoTextField.getText();
                String mensagem = mensagemTextArea.getText();

                try {
                if (destinatario.equals(usuario.getEmailPessoal())) {
                    throw new EmailToYourselfException("Não é possível enviar um email para si mesmo");
                } else {
                    sistema.enviarEmail(usuario.getEmailPessoal(), destinatario, assunto, mensagem);
                    JOptionPane.showMessageDialog(null, "Email enviado com sucesso");
                }
                } catch (EmailToYourselfException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        add(destinatarioLabel);
        add(destinatarioTextField);
        add(assuntoLabel);
        add(assuntoTextField);
        add(mensagemLabel);
        add(mensagemTextArea);
        add(enviarButton);
        add(closeButton);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public DialogEnviarEmail(Usuario usuario, String destinatario){
        this.usuario = usuario;

        setTitle("Novo Email");
        setLayout(null);

        JLabel destinatarioLabel = new JLabel("Para: ");
        JTextField destinatarioTextField = new JTextField(destinatario);
        destinatarioTextField.setEditable(false);

        JLabel assuntoLabel = new JLabel("Assunto: ");
        JTextField assuntoTextField = new JTextField(30);

        JLabel mensagemLabel = new JLabel("Mensagem:");
        JTextArea mensagemTextArea = new JTextArea(80, 60);
        mensagemTextArea.setLineWrap(true);
        mensagemTextArea.setWrapStyleWord(true); 

        JButton closeButton = new JButton("Fechar");
        JButton enviarButton = new JButton("Enviar Email");

        destinatarioLabel.setBounds(10, 10, 80, 20);
        destinatarioTextField.setBounds(100, 10, 200, 20);
        assuntoLabel.setBounds(10, 40, 80, 20);
        assuntoTextField.setBounds(100, 40, 200, 20);
        mensagemLabel.setBounds(10, 70, 80, 20);
        mensagemTextArea.setBounds(100, 70, 400, 200);
        enviarButton.setBounds(10, 300, 200, 30);
        closeButton.setBounds(220, 300, 100, 30);

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        enviarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String assunto = assuntoTextField.getText();
                String mensagem = mensagemTextArea.getText();

                if(destinatario.equals(usuario.getEmailPessoal())){
                    JOptionPane.showMessageDialog(null, "Não é possível enviar um email para si mesmo");
                }
                else{
                    sistema.enviarEmail(usuario.getEmailPessoal(), destinatario, assunto, mensagem);
                    JOptionPane.showMessageDialog(null, "Email enviado com sucesso");
                }
            }
        });

        add(destinatarioLabel);
        add(destinatarioTextField);
        add(assuntoLabel);
        add(assuntoTextField);
        add(mensagemLabel);
        add(mensagemTextArea);
        add(enviarButton);
        add(closeButton);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
