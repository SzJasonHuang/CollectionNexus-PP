package ui;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//ImageBackgroundPanel extends JPanel to properly setup Lebron Image
public class ImageBackgroundPanel extends JPanel {
    private Image backgroundImage;

    // REQUIRES: true = Files.exists(Path.get(source));
    public ImageBackgroundPanel(String source) {
        this.backgroundImage = new ImageIcon(source).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
