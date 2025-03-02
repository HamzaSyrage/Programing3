
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Order {

    private static int counter = 0;
    private int id;
    private String user;
    private String meals;
    private float tip;
    private float price;
    private float total;
    private boolean isCash;
    private boolean isSafari;
    private String status;
    private long time;
    private Date date;

    public Order(String user, String meals, float tip, float price, boolean isCash, boolean isSafari) {
        this.user = user;
        this.meals = meals;
        this.tip = tip;
        this.price = price;
        this.isCash = isCash;
        this.isSafari = isSafari;
        this.total = tip + price;
        this.date = new Date();
        this.time = date.getTime();
        this.id = ++counter;
        status = "holding";
        writeInfo();
    }

    public Order() {
        this.id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getMeals() {
        return meals;
    }

    public float getTip() {
        return tip;
    }

    public float getPrice() {
        return price;
    }

    public float getTotal() {
        return total;
    }

    public boolean isIsCash() {
        return isCash;
    }

    public boolean isIsSafari() {
        return isSafari;
    }

    public String getStatus() {
        return status;
    }

    public long getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public void setTip(float tip) {
        this.tip = tip;
    }

    public void setTip(String tip) {
        this.tip = Float.valueOf(tip);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = Float.valueOf(price);
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setTotal(String total) {
        this.total = Float.valueOf(total);
    }

    public void setIsCash(boolean isCash) {
        this.isCash = isCash;
    }

    public void setIsCash(String isCash) {
        this.isCash = Boolean.valueOf(isCash);
    }

    public void setIsSafari(boolean isSafari) {
        this.isSafari = isSafari;
    }

    public void setIsSafari(String isSafari) {
        this.isSafari = Boolean.valueOf(isSafari);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(long time) {
        this.date = new Date(time);
        this.time = time;
    }

    public void setTime(String time) {
        this.date = new Date(Long.valueOf(time));
        this.time = Long.valueOf(time);
    }

    public void addInfo(String key, String value) {
        switch (key) {
            case "user":
                setUser(value);
                break;
            case "meals":
                setMeals(value);
                break;
            case "tip":
                setTip(value);
                break;
            case "price":
                setPrice(value);
                break;
            case "total":
                setTotal(value);
                break;
            case "isCash":
                setIsCash(value);
                break;
            case "isSafari":
                setIsSafari(value);
                break;
            case "status":
                setStatus(value);
                break;
            case "time":
                setTime(value);
                break;

        }
    }

    public void writeInfo() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                writeIntoFile();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public synchronized void writeIntoFile() {
        File orderFile = new File("order/" + this.time + ".txt");
        try {
            orderFile.createNewFile();

            FileWriter fos = new FileWriter(orderFile);

            fos.write("user");
            fos.write("\n");
            fos.write(this.user);
            fos.write("\n");
            fos.write("meals");
            fos.write("\n");
            fos.write(this.meals + "");
            fos.write("\n");
            fos.write("tip");
            fos.write("\n");
            fos.write(this.tip + "");
            fos.write("\n");
            fos.write("price");
            fos.write("\n");
            fos.write(this.price + "");
            fos.write("\n");
            fos.write("total");
            fos.write("\n");
            fos.write(this.total + "");
            fos.write("\n");
            fos.write("isCash");
            fos.write("\n");
            fos.write(this.isCash + "");
            fos.write("\n");
            fos.write("isSafari");
            fos.write("\n");
            fos.write(this.isSafari + "");
            fos.write("\n");
            fos.write("status");
            fos.write("\n");
            fos.write(this.status);
            fos.write("\n");
            fos.write("time");
            fos.write("\n");
            fos.write(this.time + "");
            fos.write("\n");
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public String toString() {
        return "Order{" + "user=" + user + ", meals=" + meals + ", tip=" + tip + ", price=" + price + ", total=" + total + ", isCash=" + isCash + ", isSafari=" + isSafari + ", status=" + status + ", date=" + date + '}';
    }

}
