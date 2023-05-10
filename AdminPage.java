import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {

    public AdminPage() {
        setTitle("Admin Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));

        JLabel welcomeLabel = new JLabel("Welcome, Admin!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new LogoutAction());
        contentPane.add(logoutButton);

        setVisible(true);
    }

    private class LogoutAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new LoginPage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminPage::new);
    }
}
