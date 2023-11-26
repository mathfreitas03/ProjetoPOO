package apresentacao;

import dados.Email;
import dados.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import negocio.Sistema;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class VisualizarEmail extends JFrame{
    Sistema sistema = Sistema.getInstance();
    Usuario usuario;

    public VisualizarEmail(Usuario usuario, Email mail){
    this.usuario = usuario;

        setTitle("Responder email");
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icons/icons8-mail-96.png")));
        setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220)); 
        pack();

        Font fonte = new Font("Arial", Font.BOLD, 18);

        JLabel labelRemetente = new JLabel("Remetente: ");
        JLabel labelAssunto = new JLabel("Assunto: " + mail.getAssunto());
        JLabel remetente = new JLabel(mail.getRemetente());
        remetente.setFont(fonte);
        
        labelRemetente.setFont(fonte);
        labelAssunto.setFont(fonte);

        JTextArea mensagemTextArea = new JTextArea(mail.getCorpo()/*.descriptografar(mail.getCorpo())*/);
        mensagemTextArea.setLineWrap(true);
        mensagemTextArea.setWrapStyleWord(true);
        mensagemTextArea.setEditable(false);

        JButton botaoResponderEmail = new JButton("Responder");
        JButton botaoExcluirEmail = new JButton("Excluir");

        labelRemetente.setBounds(10, 20, 200, 40);
        remetente.setBounds(115, 20, 200, 40);
        labelAssunto.setBounds(10, 50, 400, 40);
        mensagemTextArea.setBounds(10, 90, 580, 300);
        botaoResponderEmail.setBounds(10, 400, 100, 30);
        botaoExcluirEmail.setBounds(120, 400, 100, 30);

        botaoResponderEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                String destinatario = remetente.getText();

                DialogEnviarEmail novoEmail = new DialogEnviarEmail(usuario, destinatario);
                novoEmail.setLocationRelativeTo(null);
                novoEmail.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icons/icons8-mail-96.png")));
                novoEmail.setSize(600, 400);
            }
        });

        botaoExcluirEmail.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar este email?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                sistema.excluirEmail(mail.getId());
                dispose();
                }
            }
        });

        add(remetente);
        add(labelRemetente);
        add(labelAssunto);
        add(mensagemTextArea);
        add(botaoResponderEmail);
        add(botaoExcluirEmail);
    }
}
