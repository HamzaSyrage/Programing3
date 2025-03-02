
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class User {

    private static int counter = 0;
    private int id;
    private String email;
    private String password;
    boolean isAdmin;

    public User(String email, String password, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.id = ++counter;
        writeInfo();
    }

    public User() {
        this.id = ++counter;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = Boolean.valueOf(isAdmin);
    }

    public boolean login(String email, String password) {
        if (email == this.email && password == this.password) {
            return true;
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public static void CreateNewUser(String email, String password, boolean isAdmin) {
        new User(email, password, isAdmin);
    }

    public void addInfo(String key, String value) {
        switch (key) {
            case "email":
                setEmail(value);
                break;
            case "password":
                setPassword(value);
                break;
            case "isAdmin":
                setIsAdmin(value);
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
        File userFile = new File("user/" + this.email + ".txt");
        try {
            userFile.createNewFile();

            FileWriter fos = new FileWriter(userFile);
            fos.write("email");
            fos.write("\n");
            fos.write(this.email);
            fos.write("\n");
            fos.write("password");
            fos.write("\n");
            fos.write(this.password + "");
            fos.write("\n");
            fos.write("isAdmin");
            fos.write("\n");
            fos.write(this.isAdmin + "");
            fos.write("\n");
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", isAdmin=" + isAdmin + '}';
    }

}
