
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data {

    private static User curent_user;
    private static File userFile;
    private static File mealFile;
    private static File orderFile;
    private static ArrayList<Meal> mealArrayList;
    private static ArrayList<User> userArrayList;
    private static ArrayList<Order> orderArrayList;
    private static String[] userList;
    private static HashMap<String, String> userHashMap;
    private static String[] mealList;
    private static String[] orderList;

    public Data() {
        validate();
    }

    public static void validate() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                readFromFile();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public static synchronized void readFromFile() {
        File imageFile = new File("image");
        imageFile.mkdir();

        userFile = new File("user");
        mealFile = new File("meal");
        orderFile = new File("order");

        userFile.mkdir();
        mealFile.mkdir();
        orderFile.mkdir();

        mealArrayList = new ArrayList();
        userArrayList = new ArrayList();
        orderArrayList = new ArrayList();

        userList = userFile.list();
        for (int i = 0; i < userList.length; i++) {
            File m = new File(userFile, userList[i]);
            User user = new User();
            try (Scanner sc = new Scanner(m)) {
                while (sc.hasNext()) {
                    String key = sc.nextLine();
                    String value = sc.nextLine();
                    user.addInfo(key, value);
                }
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            }
            userArrayList.add(user);
        }

        userHashMap = new HashMap();

        for (User u : userArrayList) {
            userHashMap.put(u.getEmail(), u.getPassword());
        }

        mealList = mealFile.list();
        for (int i = 0; i < mealList.length; i++) {
            File m = new File(mealFile, mealList[i]);
            Meal meal = new Meal();
            try (Scanner sc = new Scanner(m)) {
                while (sc.hasNext()) {
                    String key = sc.nextLine();
                    String value = sc.nextLine();
                    meal.addInfo(key, value);
                }
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            }
            mealArrayList.add(meal);
        }

        orderList = orderFile.list();
        for (int i = 0; i < orderList.length; i++) {
            File m = new File(orderFile, orderList[i]);
            Order order = new Order();
            try (Scanner sc = new Scanner(m)) {
                while (sc.hasNext()) {
                    String key = sc.nextLine();
                    String value = sc.nextLine();
                    order.addInfo(key, value);
                }
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            }
            orderArrayList.add(order);
        }
    }

    public static User getCurent_user() {
        return curent_user;
    }

    public static void setCurent_user(User curent_user) {
        Data.curent_user = curent_user;

    }

    public static File getUserFile() {
        return userFile;
    }

    public static void setUserFile(File userFile) {
        Data.userFile = userFile;

    }

    public static File getMealFile() {
        return mealFile;
    }

    public static void setMealFile(File mealFile) {
        Data.mealFile = mealFile;
    }

    public static File getOrderFile() {
        return orderFile;
    }

    public static void setOrderFile(File orderFile) {
        Data.orderFile = orderFile;
    }

    public static ArrayList<Meal> getMealArrayList() {
        return mealArrayList;
    }

    public static void setMealArrayList(ArrayList<Meal> mealArrayList) {
        Data.mealArrayList = mealArrayList;
    }

    public static ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public static void setUserArrayList(ArrayList<User> userArrayList) {
        Data.userArrayList = userArrayList;
    }

    public static ArrayList<Order> getOrderArrayList() {
        return orderArrayList;
    }

    public static void setOrderArrayList(ArrayList<Order> orderArrayList) {
        Data.orderArrayList = orderArrayList;
    }

    public static String[] getUserList() {
        return userList;
    }

    public static void setUserList(String[] userList) {
        Data.userList = userList;
    }

    public static HashMap<String, String> getUserHashMap() {
        return userHashMap;
    }

    public static void setUserHashMap(HashMap<String, String> userHashMap) {
        Data.userHashMap = userHashMap;
    }

    public static String[] getMealList() {
        return mealList;
    }

    public static void setMealList(String[] mealList) {
        Data.mealList = mealList;
    }

    public static String[] getOrderList() {
        return orderList;
    }

    public static void setOrderList(String[] orderList) {
        Data.orderList = orderList;
    }
}
