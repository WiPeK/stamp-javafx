package pl.stamp.listeners;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Krzysztof Adamczyk on 20.10.2017.
 */
public class SelectedImageListener {

    private ImageView imageView;

    public SelectedImageListener(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * Adding selectedImage to image view node
     * @param selectedImage selected image
     */
    public void addSelectedImageToView(File selectedImage) {
        try {
            Image image = new Image(new FileInputStream(selectedImage));
            this.imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
