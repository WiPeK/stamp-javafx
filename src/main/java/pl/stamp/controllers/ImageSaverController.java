package pl.stamp.controllers;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.stamp.services.FileChooserService;
import pl.stamp.services.ImageSaverService;

import java.io.File;
import java.util.Optional;

/**
 * @author Krzysztof Adamczyk on 21.10.2017.
 */
public class ImageSaverController {

    private Button savingButton;

    private ImageView imageView;

    public ImageSaverController(Button savingButton) {
        this.savingButton = savingButton;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void addEventListener() {
        this.savingButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Optional<File> fileToSave = FileChooserService.trySaveFile();
            fileToSave.ifPresent(file -> ImageSaverService.saveImage(file, imageView.getImage()));
        });
    }
}
