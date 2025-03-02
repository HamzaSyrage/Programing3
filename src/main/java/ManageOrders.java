
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ManageOrders {

    JFrame frame;
    ArrayList<Order> orderArrayList = new ArrayList<>();
    HashMap<String, Integer> meal_hashmap = new HashMap<>();

    public ManageOrders() {
        Data.validate();
        this.orderArrayList = Data.getOrderArrayList();

        frame = new JFrame("Status Of Order");
        frame.setLayout(new GridLayout(orderArrayList.size(), 1));
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Font font = new Font(null, Font.TRUETYPE_FONT, 18);
        for (Order value : orderArrayList) {
            String[] mealsList = value.getMeals().split(" ");
            for (String name : mealsList) {
                meal_hashmap.put(name, 0);
            }
        }
        for (Order value : orderArrayList) {
            String[] mealsList = value.getMeals().split(" ");
            for (String name : mealsList) {
                meal_hashmap.put(name, meal_hashmap.get(name) + 1);
            }
        }

        for (Order order : orderArrayList) {
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
            JLabel label = new JLabel(order.getUser() + " " + order.getDate().toLocaleString() + " ");
            meal_hashmap.forEach((meal, count) -> {
                label.setText(label.getText() + meal + " :" + count + ",");

            });
            label.setText(label.getText() + "total :" + order.getTotal() + " ");
            label.setFont(font);
            label.setForeground(Color.BLACK);
            panel.add(label);

            String[] statusArr = new String[]{"holding", "finished", "canceled"};
            JComboBox comboBox = new JComboBox<String>(statusArr);
            comboBox.setFont(font);
            comboBox.setForeground(Color.BLACK);
            comboBox.setBackground(new Color(245, 245, 220));
            String ss = order.getStatus();
            if (ss.compareTo("holding") == 0) {
                comboBox.setSelectedIndex(0);

                panel.revalidate();
            }
            if (ss.compareTo("finished") == 0) {
                comboBox.setSelectedIndex(1);

                panel.revalidate();
            }
            if (ss.compareTo("canceled") == 0) {
                comboBox.setSelectedIndex(2);

                panel.revalidate();
            }

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (comboBox.getSelectedIndex() == 0) {
                        order.setStatus("holding");
                        order.writeInfo();
                        panel.revalidate();
                    }
                    if (comboBox.getSelectedIndex() == 1) {
                        order.setStatus("finished");
                        order.writeInfo();
                        panel.revalidate();
                    }
                    if (comboBox.getSelectedIndex() == 2) {
                        order.setStatus("canceled");
                        order.writeInfo();
                        panel.revalidate();
                    }
                }
            });

            panel.add(comboBox);
            panel.revalidate();
            frame.add(panel);

        }

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setName("Mange Order");
        frame.setTitle("Mange Order");
        frame.setIconImage(new ImageIcon("image/icon.png").getImage());
    }
}
