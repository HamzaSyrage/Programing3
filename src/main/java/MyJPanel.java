
import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel {

    private Image image;

    public MyJPanel(String imagePath) {
        this.image = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}
