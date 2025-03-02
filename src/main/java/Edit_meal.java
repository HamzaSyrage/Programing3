
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Edit_meal extends JFrame implements ActionListener {

    private JPanel center;
    private JLabel top_label;
    private JLabel name_label;
    private JLabel count_label;
    private JLabel price_label;
    private JLabel recipe_label;
    private JLabel image_label;
    private JTextField name_textField;
    private JTextField count_textField;
    private JTextField price_textField;
    private JTextField recipe_textField;
    private JButton save_button;
    private JButton cancel_button;
    private JButton choose_file_button;
    private JFileChooser fileChooser;
    private Meal meal;
    private String imageName;

    public Edit_meal(Meal meal) {
        this.meal = meal;
        imageName = meal.getImage();
        initialize();
    }

    public void initialize() {
        this.setName("Add meal");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);

        center = new MyJPanel("image/meal.jpg");
        center.setLayout(null);
        Font font = new Font(null, Font.ITALIC, 24);

        top_label = new JLabel("Edit meal");
        top_label.setFont(font);
        top_label.setForeground(Color.BLACK);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        top_label.setBounds((dimension.width / 2) - 50, 25, 150, 100);
        center.add(top_label);

        name_label = new JLabel("name :");
        name_label.setFont(font);
        name_label.setForeground(Color.BLACK);
        name_label.setBounds(480, 100, 100, 50);
        name_textField = new JTextField();
        name_textField.setBounds(560, 110, 500, 30);

        count_label = new JLabel("count :");
        count_label.setFont(font);
        count_label.setForeground(Color.BLACK);
        count_label.setBounds(480, 150, 110, 50);
        count_textField = new JTextField();
        count_textField.setBounds(560, 160, 500, 30);

        price_label = new JLabel("price :");
        price_label.setFont(font);
        price_label.setForeground(Color.BLACK);
        price_label.setBounds(490, 200, 200, 50);
        price_textField = new JTextField();
        price_textField.setBounds(560, 210, 500, 30);

        recipe_label = new JLabel("recipe :");
        recipe_label.setFont(font);
        recipe_label.setForeground(Color.BLACK);
        recipe_label.setBounds(480, 250, 90, 50);
        recipe_textField = new JTextField();
        recipe_textField.setBounds(560, 260, 500, 30);

        image_label = new JLabel("image :");
        image_label.setFont(font);
        image_label.setForeground(Color.BLACK);
        image_label.setBounds(480, 300, 100, 50);

        choose_file_button = new JButton("choose image");
        choose_file_button.setFont(font);
        choose_file_button.setBackground(Color.WHITE);
        choose_file_button.setForeground(Color.BLACK);
        choose_file_button.setBounds(560, 300, 150, 50);
        choose_file_button.addActionListener(this);

        save_button = new JButton("save");
        save_button.setFont(font);
        save_button.setForeground(Color.BLACK);
        save_button.setBackground(Color.WHITE);
        save_button.setBounds(600, 400, 100, 50);
        save_button.addActionListener(this);

        cancel_button = new JButton("cancel");
        cancel_button.setFont(font);
        cancel_button.setForeground(Color.BLACK);
        cancel_button.setBackground(Color.WHITE);
        cancel_button.setBounds(900, 400, 150, 50);
        cancel_button.addActionListener(this);

        center.add(name_label);
        center.add(name_textField);
        center.add(count_label);
        center.add(count_textField);
        center.add(price_label);
        center.add(price_textField);
        center.add(recipe_label);
        center.add(recipe_textField);
        center.add(image_label);
        center.add(choose_file_button);
        center.add(save_button);
        center.add(cancel_button);

        this.add(center, BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        name_textField.setText(meal.getName());
        count_textField.setText(meal.getCount() + "");
        price_textField.setText(meal.getPrice() + "");
        recipe_textField.setText(meal.getRecipes());
        choose_file_button.setText(imageName);
        this.setVisible(true);
        this.setName("Edit Meal");
        this.setTitle("Edit Meal");
        this.setIconImage(new ImageIcon("image/icon.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choose_file_button) {
            fileChooser = new JFileChooser(new File("image"));
            int r = fileChooser.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                imageName = fileChooser.getSelectedFile().getName();
                choose_file_button.setText(imageName);

            }
        }
        if (e.getSource() == save_button) {
            String name = name_textField.getText();
            try {
                int count = Integer.valueOf(count_textField.getText().trim());
                float price = Float.valueOf(price_textField.getText().trim());
                String recipe = recipe_textField.getText().trim();
                if (recipe.trim().isBlank() || imageName.trim().isBlank() || count_textField.getText().trim().isBlank() || price_textField.getText().trim().isBlank()) {
                    throw new Exception("Invalid data");
                }

                this.meal.delete();
                new Meal(name, count, price, imageName, recipe);
                this.dispose();
                new ManageMeal();

            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, "Invalid data!",
                        "Invalid data", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == cancel_button) {
            name_textField.setText("");
            count_textField.setText("");
            price_textField.setText("");
            recipe_textField.setText("");
            this.dispose();
            new ManageMeal();
        }
    }
}
