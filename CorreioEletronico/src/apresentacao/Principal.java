package apresentacao;

import negocio.Sistema;
import dados.Email;
import dados.Usuario;
import persistencia.UsuarioDAO;
import persistencia.EmailDAO;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame {
    Sistema sistema = Sistema.getInstance();
    DefaultTableModel tableModel;
    JTable emailTable;
    JScrollPane emailScrollPane;
    Usuario usuarioAtualizado;
    UsuarioDAO usuarioDAO;
    EmailDAO emailDAO;
    List<Email> listaEmails;

    /*public void atualizarListaEmails(){
        if (usuarioAtualizado != null) {
            tableModel.setRowCount(0);
            List<Email> listaEmails = usuarioAtualizado.getEmails();

            for (Email email : listaEmails) {
                tableModel.addRow(new Object[]{email.getRemetente(), email.getAssunto(), email.getDataFormatada(), email.getId()});
            }
        }
    }
    */

    public void atualizarListaEmails(){
        if(usuarioAtualizado != null){
        tableModel.setRowCount(0);
            for (Email email : listaEmails) {
                tableModel.addRow(new Object[]{email.getRemetente(), email.getAssunto(), email.getDataFormatada(), email.getId()});
            }
        }
    }

    Principal(Usuario usuario) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();
        emailDAO = new EmailDAO();
        
        String emailUsuarioLogado = usuario.getEmailPessoal();

        for (Usuario user : Sistema.getListaDeUsuarios()) {
            if (emailUsuarioLogado.equals(user.getEmailPessoal())) {
                usuarioAtualizado = user;
                listaEmails = usuarioDAO.listarEmails(usuarioAtualizado.getId(), usuarioAtualizado);
                break;
                }
        }

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Remetente");
        tableModel.addColumn("Assunto");
        tableModel.addColumn("Data");
        tableModel.addColumn("ID");

        emailTable = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        emailScrollPane = new JScrollPane(emailTable);

        final int DEFAULT_WIDTH = 800;
        final int DEFAULT_HEIGHT = 500;
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Correio Eletrônico");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icons/icons8-mail-96.png")));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLabel boasVindas = new JLabel("Seja bem-vindo, " + usuario.getNome());
        Font fonteBoasVindas = new Font("Arial", Font.BOLD, 18);
        boasVindas.setFont(fonteBoasVindas);

        JButton botaoEnviarEmail = new JButton("Novo Email");
        JButton botaoAtualizarListaEmails = new JButton("Atualizar emails");
        JButton botaoSair = new JButton("Sair");

        botaoEnviarEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogEnviarEmail dialog = new DialogEnviarEmail(usuario);
                dialog.setLocationRelativeTo(null);
                dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icons/icons8-mail-96.png")));
                dialog.setSize(600, 400);
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login;
                try {
                    login = new Login();
                    login.setVisible(true);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        botaoAtualizarListaEmails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listaEmails = usuarioDAO.listarEmails(usuarioAtualizado.getId(), usuarioAtualizado);
                atualizarListaEmails();
            }
        });

        ListSelectionModel selecionarEmail = emailTable.getSelectionModel();
        selecionarEmail.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Email selecionado = null;
                    int selectedRow = emailTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        DefaultTableModel tableModel = (DefaultTableModel) emailTable.getModel();
                        int indice = (Integer) tableModel.getValueAt(selectedRow, 3);
                
                        /*
                        for (Email email : usuarioAtualizado.getEmails()) {
                            if (indice == email.getId()) {
                                selecionado = email;
                                break;
                            }
                        }
                        */
                        
                        selecionado = emailDAO.buscarEmailPorId(indice);
                
                        if (selecionado != null) {
                            VisualizarEmail emailDialog = new VisualizarEmail(usuarioAtualizado, selecionado);

                            emailDialog.addWindowListener(new WindowAdapter() {
                                public void windowClosed(WindowEvent e) {
                                    listaEmails = usuarioDAO.listarEmails(usuarioAtualizado.getId(), usuarioAtualizado);
                                    atualizarListaEmails();
                                }
                            });

                            emailDialog.setLocationRelativeTo(null);
                            emailDialog.setSize(620, 550);
                            emailDialog.setVisible(true);
                        }
                    }
                }
            }
        });

        boasVindas.setBounds(10, 10, 400, 30);
        botaoEnviarEmail.setBounds(10, 50, 100, 30);
        botaoAtualizarListaEmails.setBounds(120, 50, 150, 30);
        botaoSair.setBounds(280, 50, 100, 30);
        emailScrollPane.setBounds(10, 90, 760, 350);

        add(boasVindas);
        add(botaoEnviarEmail);
        add(botaoAtualizarListaEmails);
        add(botaoSair);
        add(emailScrollPane);
    }
}
