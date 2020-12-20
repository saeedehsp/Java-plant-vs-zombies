/***
 * In The Name of Allah
 ***/
package game.bufferstrategy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Timer;

/**
 * Program start.
 *
 * @author Masih Yeganeh
 */
public class Main {

    private static HashMap<String, Image> cachedImages = new HashMap<String, Image>();

    public static void main(String[] args) {
        // Initialize the global thread-pool
        ThreadPool.init();


        Sound sound = new Sound();
        Timer soundTimer = new Timer();
        soundTimer.schedule(sound, 0L, 16000L);
        Menu menu = new Menu( );
        MainMenu mainMenu = new MainMenu();
    }

    /**
     * loading gif images
     * @param filename
     * @return
     */
    static Image loadImage(String filename) {
        if (cachedImages.containsKey(filename)) return cachedImages.get(filename);

        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        try {
            URL imageURL = ClassLoader.getSystemResource("images/" + filename);
            if (imageURL != null) {
                if (imageURL.toString().toLowerCase().endsWith(".gif"))
                    image = (new ImageIcon(imageURL)).getImage();
                else
                    image = ImageIO.read(imageURL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cachedImages.put(filename, image);

        System.out.println("Loaded " + filename);  // TODO: Delete me

        return image;
    }
}
