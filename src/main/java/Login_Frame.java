
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Frame extends JFrame implements ActionListener {

    private JPanel top;
    private MyJPanel center;
    private JLabel top_label;
    private JLabel mail_label;
    private JLabel password_label;
    private JTextField mail_textfield;
    private JPasswordField password_passwodrfield;
    private JButton login_button;
    private JButton sign_up_button;

    public Login_Frame() {
        Data.validate();
        this.initialize();
    }

    public void initialize() {

        this.setName("Login frame");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout(0, 0));
        this.setLocationRelativeTo(null);
        this.setSize(400, 200);

        center = new MyJPanel("image/login.jpg");
        center.setLayout(null);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        top_label = new JLabel("Login");
        top_label.setBounds((dimension.width / 2), 125, 150, 100);
        top_label.setForeground(Color.WHITE);
        top_label.setFont(new Font(null, Font.BOLD, 30));

        center.add(top_label);
        Font font = new Font(null, Font.BOLD, 22);
        mail_label = new JLabel("email :");
        mail_label.setFont(font);
        mail_label.setForeground(Color.WHITE);
        mail_label.setBounds(470, 200, 150, 50);
        mail_textfield = new JTextField();
        mail_textfield.setBounds(560, 210, 500, 30);

        password_label = new JLabel("password :");
        password_label.setFont(font);
        password_label.setForeground(Color.WHITE);
        password_label.setBounds(425, 270, 150, 50);
        password_passwodrfield = new JPasswordField();
        password_passwodrfield.setBounds(560, 280, 500, 30);
        password_passwodrfield.setEchoChar('*');

        login_button = new JButton("login");
        login_button.setFont(font);
        login_button.setBounds(600, 350, 100, 50);
        login_button.addActionListener(this);
        login_button.setBackground(Color.WHITE);

        sign_up_button = new JButton("sign up");
        sign_up_button.setFont(new Font(null, Font.BOLD, 20));
        sign_up_button.setBounds(900, 350, 120, 50);
        sign_up_button.addActionListener(this);
        sign_up_button.setBackground(Color.WHITE);

        center.add(mail_label);
        center.add(mail_textfield);
        center.add(password_label);
        center.add(password_passwodrfield);
        center.add(login_button);
        center.add(sign_up_button);

        this.add(center, BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setName("Login");
        this.setTitle("Login");
        this.setIconImage(new ImageIcon("image/icon.png").getImage());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login_button) {
            if (Data.getUserHashMap().containsKey(mail_textfield.getText())) {
                if (Data.getUserHashMap().containsValue(password_passwodrfield.getText())) {

                    int index = -1;
                    for (int i = 0; i < Data.getUserArrayList().size(); i++) {
                        if (Data.getUserArrayList().get(i).getEmail().compareTo(mail_textfield.getText()) == 0) {

                            index = i;
                        }
                    }
                    if (index != -1) {
                        Data.setCurent_user(Data.getUserArrayList().get(index));
                    }
                    if (!Data.getCurent_user().isIsAdmin()) {
                        this.dispose();
                        new UserMainWindow();
                    } else {
                        this.dispose();
                        new AdminMainWindow();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Password!",
                            "Failed to Login", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Email",
                        "Failed to Login", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == sign_up_button) {
            this.dispose();
            new Sign_up_frame();
        }
    }

}
