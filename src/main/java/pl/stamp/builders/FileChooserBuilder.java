package pl.stamp.builders;

import javafx.stage.FileChooser;

import java.io.File;

/**
 * @author Krzysztof Adamczyk on 19.10.2017.
 */
public class FileChooserBuilder {

    public static FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select BMP file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("BMP File", "*.bmp"));

        return fileChooser;
    }
}
