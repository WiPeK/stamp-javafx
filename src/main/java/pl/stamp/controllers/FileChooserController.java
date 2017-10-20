package pl.stamp.controllers;

import javafx.stage.FileChooser;
import pl.stamp.builders.FileChooserBuilder;

import java.io.File;
import java.util.Optional;

/**
 * @author Krzysztof Adamczyk on 19.10.2017.
 */
public class FileChooserController {

    public static Optional<File> trySelectFile() {
        FileChooser fileChooser = FileChooserBuilder.getFileChooser();
        File result = fileChooser.showOpenDialog(null);
        return Optional.of(result);
    }
}
