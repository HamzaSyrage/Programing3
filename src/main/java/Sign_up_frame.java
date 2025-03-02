
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign_up_frame extends JFrame implements ActionListener {

    private MyJPanel center;
    private JLabel top_label;
    private JLabel mail_label;
    private JLabel password_label;
    private JLabel check_pass_label;
    private JTextField mail_textfield;
    private JPasswordField password_passwodrfield;
    private JPasswordField check_password_passwodrfield;
    private JButton save_button;
    private JButton cancel_button;
    private JCheckBox admin_checkbox;

    public Sign_up_frame() {
        Data.validate();
        initialize();
    }

    public void initialize() {
        this.setName("sign up");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout(0, 0));
        this.setLocationRelativeTo(null);
        this.setSize(400, 200);

        center = new MyJPanel("image/Sign up.jpg");
        center.setLayout(null);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        top_label = new JLabel("Sing up");
        top_label.setBounds((dimension.width / 2) - 35, 125, 150, 100);
        top_label.setForeground(Color.WHITE);
        top_label.setFont(new Font(null, Font.BOLD, 30));
        center.add(top_label);
        Font font = new Font(null, Font.BOLD, 22);
        mail_label = new JLabel("email :");
        mail_label.setFont(font);
        mail_label.setForeground(Color.WHITE);
        mail_label.setBounds(475, 200, 150, 50);
        mail_textfield = new JTextField();
        mail_textfield.setBounds(560, 210, 500, 30);

        password_label = new JLabel("password :");
        password_label.setFont(font);
        password_label.setForeground(Color.WHITE);
        password_label.setBounds(430, 270, 150, 50);
        password_passwodrfield = new JPasswordField();
        password_passwodrfield.setBounds(560, 280, 500, 30);
        password_passwodrfield.setEchoChar('*');

        check_pass_label = new JLabel("check the password :");
        check_pass_label.setFont(font);
        check_pass_label.setForeground(Color.WHITE);
        check_pass_label.setBounds(320, 340, 250, 50);
        check_password_passwodrfield = new JPasswordField();
        check_password_passwodrfield.setBounds(560, 350, 500, 30);
        check_password_passwodrfield.setEchoChar('*');

        admin_checkbox = new JCheckBox("is admin");
        admin_checkbox.setForeground(Color.BLACK);
        admin_checkbox.setBackground(Color.white);
        admin_checkbox.setFont(new Font(null, Font.BOLD, 16));
        admin_checkbox.setBounds(750, 400, 90, 50);

        save_button = new JButton("save");
        save_button.setFont(font);
        save_button.setBounds(600, 450, 100, 50);
        save_button.addActionListener(this);
        save_button.setBackground(Color.WHITE);

        cancel_button = new JButton("cancel");
        cancel_button.setFont(font);
        cancel_button.setBounds(900, 450, 120, 50);
        cancel_button.addActionListener(this);
        cancel_button.setBackground(Color.WHITE);

        center.add(mail_label);
        center.add(mail_textfield);
        center.add(password_label);
        center.add(password_passwodrfield);
        center.add(check_pass_label);
        center.add(check_password_passwodrfield);
        center.add(admin_checkbox);
        center.add(cancel_button);
        center.add(save_button);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(center, BorderLayout.CENTER);
        this.setVisible(true);
        this.setName("Sign up");
        this.setTitle("Sign up");
        this.setIconImage(new ImageIcon("image/icon.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save_button) {
            try {
                String password = password_passwodrfield.getText();
                String check_password = check_password_passwodrfield.getText();
                if (password.compareTo(check_password) == 0) {
                    if (mail_textfield.getText().trim().isBlank() || mail_textfield.getText().trim().compareTo(mail_textfield.getText()) != 0 || password_passwodrfield.getText().trim().isBlank() || password_passwodrfield.getText().trim().compareTo(password_passwodrfield.getText()) != 0 || Data.getUserHashMap().containsKey(mail_textfield.getText().trim())) {
                        throw new Exception("Invalid Email Or Password");
                    }
                    new User(mail_textfield.getText().trim(), password, admin_checkbox.isSelected());
                    this.dispose();
                    new Login_Frame();

                } else {
                    JOptionPane.showMessageDialog(this, "please correct the password!",
                            "check password error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println(ex);
                if (Data.getUserHashMap().containsKey(mail_textfield.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "user already exist!",
                            "Failed to Signup", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Email Or Password!",
                            "Failed to Signup", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        if (e.getSource() == cancel_button) {
            mail_textfield.setText("");
            password_passwodrfield.setText("");
            check_password_passwodrfield.setText("");
            this.dispose();
            new Login_Frame();
        }
    }
}
