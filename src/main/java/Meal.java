
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Meal {

    private static int counter = 0;
    private int id;
    private String name;
    private int count;
    private float price;
    private String image;
    private String recipes;

    public Meal(String name, int count, float price, String image, String recipes) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.image = image;
        this.recipes = recipes;
        this.id = ++counter;
        writeInfo();
    }

    public Meal() {
        this.id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getRecipes() {
        return recipes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void order(int count) {
        this.count -= count;
    }

    public void setCount(String count) {
        this.count = Integer.valueOf(count);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = Float.valueOf(price);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }

    public void addInfo(String key, String value) {
        switch (key) {
            case "name":
                setName(value);
                break;
            case "count":
                setCount(value);
                break;
            case "price":
                setPrice(value);
                break;
            case "image":
                setImage(value);
                break;
            case "recipes":
                setRecipes(value);
                break;
        }
    }

    public boolean delete() {
        File mealFile = new File("meal/" + this.name + ".txt");
        return mealFile.delete();
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
        File mealFile = new File("meal/" + this.name + ".txt");

        try {
            mealFile.createNewFile();

            FileWriter fos = new FileWriter(mealFile);

            fos.write("name");
            fos.write("\n");
            fos.write(this.name);
            fos.write("\n");
            fos.write("count");
            fos.write("\n");
            fos.write(this.count + "");
            fos.write("\n");
            fos.write("price");
            fos.write("\n");
            fos.write(this.price + "");
            fos.write("\n");
            fos.write("image");
            fos.write("\n");
            fos.write(this.image);
            fos.write("\n");
            fos.write("recipes");
            fos.write("\n");
            fos.write(this.recipes);
            fos.write("\n");
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public String toString() {
        return "Meal{" + "id=" + id + ", name=" + name + ", count=" + count + ", price=" + price + ", image=" + image + ", recipes=" + recipes + '}';
    }

}
