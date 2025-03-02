
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainWindow {

    JFrame frame;
    JPanel centerPanel;
    JLabel label;
    JButton addOrderButton;
    JButton statusOfOrderButton;
    JButton logoutButton;

    public UserMainWindow() {
        Data.validate();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame("main Window");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        Font font = new Font(null, Font.ITALIC, 30);
        label = new JLabel("welcome to our restaurant");
        label.setBounds((screenSize.width / 2) - 200, screenSize.height / 5, 400, 100);
        label.setFont(font);
        label.setForeground(Color.BLACK);

        Font fontButton = new Font(null, Font.ITALIC, 18);
        centerPanel = new MyJPanel("image/user window.jpg");
        centerPanel.add(label);

        addOrderButton = new JButton("add new order");
        addOrderButton.setFont(fontButton);
        addOrderButton.setBackground(Color.WHITE);
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Select_meal();
            }
        });
        addOrderButton.setBounds((screenSize.width / 2) - 120, screenSize.height / 3, 200, 50);

        statusOfOrderButton = new JButton("status of orders");
        statusOfOrderButton.setSize(50, 50);
        statusOfOrderButton.setFont(fontButton);
        statusOfOrderButton.setBackground(Color.WHITE);
        statusOfOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatusOfOrder();
            }
        });
        statusOfOrderButton.setBounds((screenSize.width / 2) - 120, (screenSize.height / 3) + 100, 200, 50);

        logoutButton = new JButton("logout");
        logoutButton.setForeground(Color.RED);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setSize(50, 50);
        logoutButton.setFont(fontButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login_Frame();
            }
        });
        logoutButton.setBounds((screenSize.width / 2) - 120, (screenSize.height / 3) + 200, 200, 50);

        centerPanel.setLayout(null);
        centerPanel.add(addOrderButton);
        centerPanel.add(statusOfOrderButton);
        centerPanel.add(logoutButton);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setName("User Main Window");
        frame.setTitle("User Main Window");
        frame.setIconImage(new ImageIcon("image/icon.png").getImage());
    }
}
