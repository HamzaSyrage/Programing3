
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageMeal {

    private JFrame frame;
    private JPanel panel;
    private JButton close;
    private JButton newMeale;
    private ArrayList<Meal> mealArrayList;

    public ManageMeal() {
        Data.validate();
        init();
    }

    public void init() {
        Data.validate();
        this.mealArrayList = Data.getMealArrayList();

        frame = new JFrame("Manage Meal");
        frame.setLayout(new GridLayout(mealArrayList.size() + 1, 1));
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Font font = new Font(null, Font.TRUETYPE_FONT, 18);
        for (Meal meal : mealArrayList) {
            JPanel panel = new MyJPanel("image/status.jpg");
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel label = new JLabel("Name : " + meal.getName() + ", Count : " + meal.getCount() + ", Price : " + meal.getPrice());
            label.setForeground(Color.BLACK);
            label.setFont(font);
            panel.add(label);

            JButton recipes = new JButton("Recipes");
            recipes.setFont(font);
            recipes.setForeground(Color.BLACK);
            recipes.setBackground(Color.WHITE);
            recipes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, meal.getRecipes(), "Recipes", JOptionPane.PLAIN_MESSAGE);
                }
            });

            JButton edit = new JButton("Edit");
            edit.setBackground(Color.WHITE);
            edit.setFont(font);
            edit.setForeground(Color.BLACK);
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new Edit_meal(meal);
                }
            });
            JButton delete = new JButton("Delete");
            delete.setBackground(Color.WHITE);
            delete.setFont(font);
            delete.setForeground(Color.BLACK);
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int o = JOptionPane.showConfirmDialog(null, "are you sure about that?!", "delete", JOptionPane.WARNING_MESSAGE);

                    if (o == 0) {
                        meal.delete();
                        frame.dispose();
                        init();
                    }
                }
            });
            panel.add(recipes);
            panel.add(edit);
            panel.add(delete);
            frame.add(panel);
        }
        panel = new MyJPanel("image/status.jpg");
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        close = new JButton("close");
        close.setBackground(Color.WHITE);
        close.setForeground(Color.BLACK);
        close.setFont(font);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        newMeale = new JButton("add new meal");
        newMeale.setBackground(Color.WHITE);
        newMeale.setFont(font);
        newMeale.setForeground(Color.BLACK);
        newMeale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Add_meal();
            }
        });
        panel.add(newMeale);
        panel.add(close);
        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setName("Mange Meal");
        frame.setTitle("Mange Meal");
        frame.setIconImage(new ImageIcon("image/icon.png").getImage());
    }
}
