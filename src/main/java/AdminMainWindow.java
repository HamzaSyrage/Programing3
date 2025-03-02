
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainWindow {

    JFrame frame;
    JPanel centerPanel;
    JLabel label;
    JButton reports;
    JButton manageOrders;
    JButton manageMeals;
    JButton logoutButton;

    public AdminMainWindow() {
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
        centerPanel = new MyJPanel("image/admin window.jpg");
        centerPanel.add(label);

        reports = new JButton("Reports");
        reports.setFont(fontButton);
        reports.setBackground(Color.WHITE);
        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Report();
            }
        });
        reports.setBounds((screenSize.width / 2) - 120, screenSize.height / 3, 200, 50);

        manageOrders = new JButton("manage Orders");
        manageOrders.setSize(50, 50);
        manageOrders.setFont(fontButton);
        manageOrders.setBackground(Color.WHITE);
        manageOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ManageOrders();
            }
        });
        manageOrders.setBounds((screenSize.width / 2) - 120, (screenSize.height / 3) + 100, 200, 50);

        manageMeals = new JButton("manage Meals");
        manageMeals.setFont(fontButton);
        manageMeals.setBackground(Color.WHITE);
        manageMeals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageMeal();
            }
        });
        manageMeals.setBounds((screenSize.width / 2) - 120, (screenSize.height / 3) + 200, 200, 50);

        logoutButton = new JButton("logout");
        logoutButton.setFont(fontButton);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(Color.RED);
        logoutButton.setSize(50, 50);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login_Frame();
            }
        });
        logoutButton.setBounds((screenSize.width / 2) - 120, (screenSize.height / 3) + 300, 200, 50);

        centerPanel.setLayout(null);
        centerPanel.add(reports);
        centerPanel.add(manageOrders);
        centerPanel.add(manageMeals);
        centerPanel.add(logoutButton);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setName("Admin Main Window");
        frame.setTitle("Admin Main Window");
        frame.setIconImage(new ImageIcon("image/icon.png").getImage());
    }
}
