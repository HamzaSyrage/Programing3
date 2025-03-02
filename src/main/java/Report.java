
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Report implements ActionListener {

    JFrame frame;
    MyJPanel side;
    JPanel panel;
    JPanel labelP;
    JPanel cancelP;
    JButton number_of_orders;
    JButton meal_that_most_ordered;
    JButton returns;
    JButton user_most_fantastic;
    JButton cancel;
    JLabel label;
    ArrayList<Meal> mealArrayList;
    ArrayList<User> userArrayList;
    ArrayList<Order> orderArrayList;
    Date date = new Date();
    HashMap<String, Integer> meal_hashmap = new HashMap<>();
    HashMap<String, Integer> user_hashmap = new HashMap<>();

    public Report() {
        this.mealArrayList = Data.getMealArrayList();
        this.userArrayList = Data.getUserArrayList();
        this.orderArrayList = Data.getOrderArrayList();
        for (Meal meal : mealArrayList) {
            meal_hashmap.put(meal.getName(), 0);
        }
        for (User meal : userArrayList) {
            user_hashmap.put(meal.getEmail(), 0);
        }
        for (Order value : orderArrayList) {
            String[] mealsList = value.getMeals().split(" ");
            for (String name : mealsList) {
                meal_hashmap.put(name, meal_hashmap.getOrDefault(name, 0) + 1);
            }
        }

        for (Order order : orderArrayList) {
            user_hashmap.put(order.getUser(), user_hashmap.get(order.getUser()) + 1);
        }

        frame = new JFrame("Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));
        frame.setSize(400, 200);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        panel = new MyJPanel("image/report3.jpg");
        panel.setLayout(null);

        Font font = new Font(null, Font.ITALIC, 18);
        number_of_orders = new JButton("number of orders");
        number_of_orders.setBackground(Color.WHITE);
        number_of_orders.setForeground(Color.BLACK);
        number_of_orders.setFont(font);
        number_of_orders.addActionListener(this);
        number_of_orders.setBounds(250, 150, 300, 50);
        panel.add(number_of_orders);

        meal_that_most_ordered = new JButton("the meal that most ordered");
        meal_that_most_ordered.addActionListener(this);
        meal_that_most_ordered.setBackground(Color.WHITE);
        meal_that_most_ordered.setForeground(Color.BLACK);
        meal_that_most_ordered.setFont(font);
        meal_that_most_ordered.setBounds(250, 300, 300, 50);
        panel.add(meal_that_most_ordered);

        returns = new JButton("returns");
        returns.addActionListener(this);
        returns.setBackground(Color.WHITE);
        returns.setForeground(Color.BLACK);
        returns.setFont(font);
        returns.setBounds(250, 450, 300, 50);
        panel.add(returns);

        user_most_fantastic = new JButton("user most fantastic");
        user_most_fantastic.addActionListener(this);
        user_most_fantastic.setBackground(Color.WHITE);
        user_most_fantastic.setForeground(Color.BLACK);
        user_most_fantastic.setFont(font);
        user_most_fantastic.setBounds(250, 600, 300, 50);
        panel.add(user_most_fantastic);

        panel.setBackground(Color.BLACK);

        side = new MyJPanel("image/report.jpg");
        side.setLayout(new GridLayout(2, 1));

        label = new JLabel();
        label.setFont(new Font(null, Font.BOLD, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);

        labelP = new MyJPanel("image/report.jpg");
        labelP.setLayout(new BorderLayout());
        labelP.add(label, BorderLayout.SOUTH);
        side.add(labelP);

        cancelP = new MyJPanel("image/report2.jpg");
        cancelP.setLayout(new FlowLayout(FlowLayout.CENTER));
        cancel = new JButton("cancel");
        cancel.setMaximumSize(new Dimension(150, 50));
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
        cancel.setFont(font);
        cancel.addActionListener(this);
        cancelP.add(cancel);
        side.add(cancelP);
        frame.add(side);
        frame.add(panel);
        frame.setVisible(true);
        frame.setName("Reports");
        frame.setTitle("Reports");
        frame.setIconImage(new ImageIcon("image/icon.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == meal_that_most_ordered) {
            AtomicReference<String> most_ordered_meal_name = new AtomicReference<>("");
            AtomicInteger most_ordered_meal_num = new AtomicInteger();
            meal_hashmap.forEach((k, v) -> {
                if (most_ordered_meal_num.get() < v) {
                    most_ordered_meal_name.set(k);
                    most_ordered_meal_num.set(v);
                }
            });
            label.setText("the most meal that has been ordered is :" + most_ordered_meal_name);
        }

        if (e.getSource() == number_of_orders) {
            ArrayList<Order> this_day = new ArrayList<>();
            for (Order order : orderArrayList) {
                if (order.getDate().getDay() == date.getDay()) {
                    this_day.add(order);
                }

            }
            label.setText("the number of order today is :" + this_day.size());
        }

        if (e.getSource() == returns) {
            float re = 0;
            for (Order order : orderArrayList) {
                if (order.getDate().getDay() == date.getDay()) {
                    re += order.getTotal();
                }
            }
            label.setText("the today returns is :" + re);
        }
        if (e.getSource() == user_most_fantastic) {
            AtomicReference<String> most_ordered_meal_name = new AtomicReference<>("");
            AtomicInteger most_ordered_meal_num = new AtomicInteger();
            user_hashmap.forEach((k, v) -> {
                if (most_ordered_meal_num.get() < v) {
                    most_ordered_meal_name.set(k);
                    most_ordered_meal_num.set(v);
                }
            });
            label.setText("user most fantastic is :" + most_ordered_meal_name);
        }
        if (e.getSource() == cancel) {
            frame.dispose();
            new AdminMainWindow();
        }
    }
}
