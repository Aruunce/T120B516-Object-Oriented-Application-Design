package clientSide.Decorator;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

import clientSide.Tank;

public class IceTankDecorator extends TankDecorator {

    public IceTankDecorator(Tank decoratedTank) {
        super(decoratedTank);
    }

    @Override
    public void loadImage(int a) {
        tankImg = new Image[4];
        for (int i = a; i < tankImg.length + a; i++) {
            String imagePath = System.getProperty("user.dir") + "/Images/P" + (i - a + 1) + ".PNG";
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                tankImg[i - a] = new ImageIcon(imgFile.getAbsolutePath()).getImage();
            } else {
                System.err.println("Error: Image not found at path: " + imgFile.getAbsolutePath());
            }
        }
        updateTankImage();
    }
}