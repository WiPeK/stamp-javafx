package pl.stamp.services;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Krzysztof Adamczyk on 21.10.2017.
 */
public class ImageSaverService {

    public static void saveImage(File file, Image image) {
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "bmp", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
