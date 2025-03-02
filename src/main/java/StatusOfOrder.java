
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StatusOfOrder {

    JFrame frame;
    ArrayList<Order> userOrderArrayList = new ArrayList<>();
    HashMap<String, Integer> meal_hashmap = new HashMap<>();

    public StatusOfOrder() {

        for (Order order : Data.getOrderArrayList()) {
            if (order.getUser().compareTo(Data.getCurent_user().getEmail()) == 0) {
                userOrderArrayList.add(order);
            }
        }

        frame = new JFrame("Status Of Order");
        frame.setLayout(new GridLayout(userOrderArrayList.size(), 1));
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        for (Order value : userOrderArrayList) {
            String[] mealsList = value.getMeals().split(" ");
            for (String name : mealsList) {
                meal_hashmap.put(name, 0);
            }
        }
        for (Order value : userOrderArrayList) {
            String[] mealsList = value.getMeals().split(" ");
            for (String name : mealsList) {
                meal_hashmap.put(name, meal_hashmap.get(name) + 1);
            }
        }

        for (Order order : userOrderArrayList) {

            String[] mealsList1 = order.getMeals().split(" ");
            for (String name : mealsList1) {
                meal_hashmap.put(name, 0);
            }

            String[] mealsList = order.getMeals().split(" ");
            for (String name : mealsList) {
                meal_hashmap.put(name, meal_hashmap.get(name) + 1);
            }

            JPanel panel = new MyJPanel("image/status.jpg");
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel label = new JLabel(order.getDate().toLocaleString() + " ");
            label.setFont(new Font(null, Font.ROMAN_BASELINE, 18));
            label.setForeground(Color.BLACK);
            meal_hashmap.forEach((meal, count) -> {
                label.setText(label.getText() + meal + " :" + count + ",");
            });
            label.setText(label.getText() + "total :" + order.getTotal() + " ");
            label.setText(label.getText() + "status :" + order.getStatus() + " ");
            panel.add(label);
            frame.add(panel);

        }
        if (userOrderArrayList.isEmpty()) {
            JPanel panel = new MyJPanel("image/status.jpg");
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel label = new JLabel("          you dont have any order          ");
            label.setFont(new Font(null, Font.ROMAN_BASELINE, 18));
            label.setForeground(Color.BLACK);
            panel.add(label);
            frame.add(panel);

        }
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setName("status Of Order");
        frame.setTitle("Status Of Order");
        frame.setIconImage(new ImageIcon("image/icon.png").getImage());
    }
}
