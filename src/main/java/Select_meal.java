
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Select_meal extends JFrame {

    static private int quantity = 1;
//    private JFrame frame;
    private JPanel top;
    private JPanel center;
    private JComboBox comboBox;
    private JPanel pay_panel;
    private JLabel tips_label;
    private JSlider tips_slider;
    private JLabel tip_label;
    private JLabel total;
    private JLabel total_price_label;
    private JLabel payment;
    private JRadioButton cash;
    private JRadioButton visa;
    private JLabel statement;
    private JCheckBox safari;
    private JButton save;
    private JButton cancel;
    private ArrayList<Meal> selectedMeal = new ArrayList();
    private ArrayList<Float> total_price = new ArrayList<Float>();
    private HashMap<String, Integer> MealsHash = new HashMap();
    private Font font = new Font(null, Font.TRUETYPE_FONT, 18);

    public Select_meal() {
        Data.validate();
        String[] meal_name = new String[Data.getMealArrayList().size()];

        for (int i = 0; i < Data.getMealArrayList().size(); i++) {
            if (Data.getMealArrayList().get(i).getCount() > 0) {
                meal_name[i] = Data.getMealArrayList().get(i).getName();

            }
        }
        HashSet<String> hs = new HashSet();
        hs.addAll(Arrays.asList(meal_name));
        hs.removeAll(Arrays.asList("", null));
        meal_name = new String[hs.size()];
        int i = 0;
        for (String h : hs) {
            meal_name[i] = h;
            i++;
        }
        comboBox = new JComboBox<String>(meal_name);
        comboBox.setBackground(Color.WHITE);
        comboBox.setFont(font);
        initialize();
    }

    public void updateTotal() {
        float tips = tips_slider.getValue();
        float price = 0;
        if (!safari.isSelected()) {
            price += 14.99;
        }
        for (Meal meal : selectedMeal) {
            price += MealsHash.get(meal.getName()) * meal.getPrice();
        }

        price += tips;
        total_price_label.setText(price + "");
        total_price_label.revalidate();
        total_price_label.repaint();
    }

    public void addMeals() {
        center.removeAll();
        for (Meal m : selectedMeal) {
            center.add(meal_panel(m));
        }
        center.repaint();
        center.revalidate();
        updateTotal();
    }
//اضافة وجبة على الواجهة يلي بالنص

    public JPanel meal_panel(Meal meal) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ImageIcon originalIcon = new ImageIcon("image/" + meal.getImage());
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 75, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel image = new JLabel(resizedIcon);

        JLabel name = new JLabel(meal.getName() + " , ");
        name.setFont(font);
        name.setForeground(Color.BLACK);
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

        JLabel count = new JLabel("0");
        count.setFont(font);
        count.setForeground(Color.BLACK);
        if (!MealsHash.containsKey(meal.getName())) {
            MealsHash.put(meal.getName(), 1);
            count.setText(1 + "");
        } else {
            count.setText(MealsHash.get(meal.getName()) + "");
        }
        JButton delete = new JButton("delete");
        delete.setFont(font);
        delete.setForeground(Color.BLACK);
        delete.setBackground(Color.WHITE);
        JButton increase = new JButton("+");
        increase.setFont(font);
        increase.setBackground(Color.WHITE);
        increase.setForeground(Color.BLACK);
        JLabel price = new JLabel("x $" + meal.getPrice());
        price.setFont(font);
        price.setForeground(Color.BLACK);
        JButton decrease = new JButton("-");
        decrease.setFont(font);

        decrease.setForeground(Color.BLACK);
        decrease.setBackground(Color.WHITE);

        //addActionListener الخاص بالبزرار الزيادة و النقص
        increase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count.setText((Integer.valueOf(count.getText()) + 1) + "");
                int val = Integer.valueOf(count.getText());
                MealsHash.put(meal.getName(), val);
                updateTotal();

                if (Integer.valueOf(count.getText()) == 1) {
                    decrease.setEnabled(false);
                } else {
                    decrease.setEnabled(true);
                }

                if (Integer.valueOf(count.getText()) == meal.getCount()) {
                    increase.setEnabled(false);
                } else {
                    increase.setEnabled(true);
                }
                quantity = Integer.valueOf(count.getText());
            }

        });

        decrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count.setText((Integer.valueOf(count.getText()) - 1) + "");
                int val = Integer.valueOf(count.getText());
                MealsHash.put(meal.getName(), val);
                updateTotal();
                if (Integer.valueOf(count.getText()) == 1) {
                    decrease.setEnabled(false);
                } else {
                    decrease.setEnabled(true);
                }

                if (Integer.valueOf(count.getText()) == meal.getCount()) {
                    increase.setEnabled(false);
                } else {
                    increase.setEnabled(true);
                }

            }
        });
        if (Integer.valueOf(count.getText()) == 1) {
            decrease.setEnabled(false);
        } else {
            decrease.setEnabled(true);
        }

        if (Integer.valueOf(count.getText()) == meal.getCount()) {
            increase.setEnabled(false);
        } else {
            increase.setEnabled(true);
        }

        //addActionListener الخاص بزر الحذف
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMeal.removeIf((m) -> m.getName() == meal.getName());
                MealsHash.remove(meal.getName());
                addMeals();
            }
        });

        //اضافة اسعار الوجبات على المصفوفة
        total_price.add(meal.getPrice() * Integer.valueOf(count.getText()));
        panel.add(delete);
        panel.add(image);
        panel.add(name);
        panel.add(recipes);
        panel.add(decrease);
        panel.add(count);
        panel.add(increase);
        panel.add(price);
        panel.setBackground(new Color(0, 0, 0, 0));
        return panel;
    }

    public void initialize() {
        this.setName("select meal");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 200);
        this.setLayout(new GridLayout(3, 1));
        this.setLocationRelativeTo(null);

        //انشاء ال top
        top = new MyJPanel("image/select top.jpg");
        top.setLayout(new FlowLayout(FlowLayout.CENTER));
        top.add(comboBox);

        //انشاء ال center و الحباشات تبعو
        center = new MyJPanel("image/select1.jpg");
        center.setLayout(new GridLayout(Data.getMealArrayList().size(), 1));
        center.setBackground(new Color(226, 185, 127));
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                for (int i = 0; i < Data.getMealArrayList().size(); i++) {
                    if (Data.getMealArrayList().get(i).getName() == comboBox.getSelectedItem()) {
                        index = i;

                    }
                }

                if (index != -1) {
                    Meal meal_selected = Data.getMealArrayList().get(index);
                    if (!selectedMeal.contains(Data.getMealArrayList().get(index))) {
                        selectedMeal.add(Data.getMealArrayList().get(index));
                    }
                    addMeals();
                    //من شان اضافة السعر ضرب الكمية على المصفوفة
                    total_price.add(Data.getMealArrayList().get(index).getPrice() * quantity);
                }

                pay_panel.revalidate();
                pay_panel.repaint();
            }
        });

        //السطر الاول بواجهة الدفع 
        Font font1 = new Font(null, Font.BOLD, 18);
        Color pcolor = new Color(51, 49, 45);
        pay_panel = new JPanel();
        pay_panel.setBackground(pcolor);
        pay_panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        Color color = new Color(0, 0, 0, 0);

        tips_label = new JLabel("Tips :");
        tips_label.setForeground(Color.WHITE);
        tips_label.setFont(font1);

        tips_slider = new JSlider(0, 100, 0);
        tips_slider.setFont(font1);
        tips_slider.setBackground(color);
        tips_slider.setFocusable(false);

        tip_label = new JLabel(tips_slider.getValue() + "");
        tip_label.setFont(font1);
        tip_label.setForeground(Color.WHITE);

        tips_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tip_label.setText(tips_slider.getValue() + "");
                updateTotal();
                pay_panel.revalidate();
                pay_panel.repaint();
            }
        });
        total = new JLabel("total");
        total.setFont(font1);
        total.setForeground(Color.WHITE);
        float s = 0;
        for (int i = 0; i < total_price.size(); i++) {
            s += total_price.get(i);
        }

        total_price_label = new JLabel("$" + s);
        total_price_label.setFont(font1);
        total_price_label.setForeground(Color.WHITE);

        pay_panel.add(tips_label);
        pay_panel.add(tips_slider);
        pay_panel.add(tip_label);
        pay_panel.add(total);
        pay_panel.add(total_price_label);

        payment = new JLabel("payment :");
        payment.setForeground(Color.WHITE);
        payment.setFont(font1);

        cash = new JRadioButton("cash");
        cash.setForeground(Color.WHITE);
        cash.setBackground(pcolor);
        cash.setFont(font1);

        visa = new JRadioButton("visa");
        visa.setForeground(Color.WHITE);
        visa.setBackground(pcolor);
        visa.setFont(font1);

        ButtonGroup payment_group = new ButtonGroup();
        payment_group.add(cash);
        payment_group.add(visa);

        cash.setSelected(true);
        visa.addActionListener((e) -> {
            JFrame visaFrame = new JFrame();
            visaFrame.setName("visa info");
            visaFrame.setTitle("visa info");
            visaFrame.setIconImage(new ImageIcon("image/icon.png").getImage());
            visaFrame.setLocationRelativeTo(null);
            visaFrame.setFocusableWindowState(true);
            visaFrame.setVisible(true);
            visaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            visaFrame.setLayout(new GridLayout(3, 1));
            visaFrame.add(new JLabel("enter your visa passcode here :             "));
            JPasswordField pf = new JPasswordField();
            pf.setMinimumSize(new Dimension(100, 25));
            visaFrame.add(pf);
            JButton okbtn = new JButton("ok");
            okbtn.addActionListener((ee) -> {
                visaFrame.dispose();
            });
            visaFrame.add(okbtn);
            visaFrame.pack();
        });

        pay_panel.add(payment);
        pay_panel.add(cash);
        pay_panel.add(visa);

        statement = new JLabel("statement :");
        statement.setFont(font1);
        statement.setForeground(Color.WHITE);

        safari = new JCheckBox("safari");
        safari.setFont(font1);
        safari.setForeground(Color.WHITE);
        safari.setBackground(pcolor);
        safari.setSelected(true);
        safari.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                updateTotal();
                pay_panel.revalidate();
                pay_panel.repaint();
            }
        });
        pay_panel.add(statement);
        pay_panel.add(safari);

        save = new JButton("save");
        save.setBackground(Color.WHITE);
        save.setForeground(Color.BLACK);
        save.setFont(font1);
        save.setSize(new Dimension(100, 50));

        cancel = new JButton("cancel");
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
        cancel.setFont(font1);
        cancel.setSize(new Dimension(100, 50));

        cancel.addActionListener((e) -> {
            this.dispose();
            new UserMainWindow();
        }
        );
        pay_panel.add(save);
        pay_panel.add(cancel);
        save.addActionListener((e) -> {
            try {

                String user = Data.getCurent_user().getEmail();
                String meals = "";
                for (Meal meal : selectedMeal) {
                    for (int i = 0; i < MealsHash.get(meal.getName()); i++) {
                        meals += meal.getName() + " ";
                    }
                }
                float tips = tips_slider.getValue();
                float price = 0;

                for (Meal meal : selectedMeal) {
                    price += MealsHash.get(meal.getName()) * meal.getPrice();

                }
                for (Meal meal : selectedMeal) {
                    int index = Data.getMealArrayList().indexOf(meal);
                    if (index != -1) {
                        Data.getMealArrayList().get(index).order(MealsHash.get(meal.getName()));
                        Data.getMealArrayList().get(index).writeInfo();
                    }
                }

                price += tips;

                boolean isSafari = safari.isSelected();
                boolean isCash = cash.isSelected();

                if (meals.isBlank() || meals.isEmpty()) {
                    throw new Exception("please order first");
                }
                new Order(user, meals, tips, price, isCash, isSafari);
                this.dispose();
                new UserMainWindow();
                JOptionPane.showMessageDialog(this, "your order is holding right now",
                        "order added", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Failed to Order", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.add(top);
        this.add(center);
        this.add(pay_panel);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setName("Select Meal");
        this.setTitle("Select Meal");
        this.setIconImage(new ImageIcon("image/icon.png").getImage());

    }
}
