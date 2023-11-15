package apresentacao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;

public abstract class Base extends JFrame {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 500;

    private JPanel basePanel;

    public JPanel getBasePanel() {
        return basePanel;
    }

    public Base() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Correio Eletrônico");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/apresentacao/icons/icons8-mail-96.png")));
        setLocationRelativeTo(null);

    }
}
