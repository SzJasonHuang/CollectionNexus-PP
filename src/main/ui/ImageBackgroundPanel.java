package ui;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageBackgroundPanel extends JPanel{
    private Image backgroundImage;

    public ImageBackgroundPanel(String path) {
        this.backgroundImage = new ImageIcon(path).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

    

