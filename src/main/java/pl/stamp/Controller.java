package pl.stamp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import pl.stamp.controllers.FileChooserController;
import pl.stamp.controllers.imageview.ImageViewCursorController;
import pl.stamp.controllers.imageview.ImageViewMarkerController;
import pl.stamp.listeners.SelectedImageListener;

import java.io.File;

public class Controller {

    /**
     * Main template of GUI
     */
    @FXML
    private BorderPane rootBorderPane;

    /**
     * Label for showing application title
     */
    @FXML
    private Label titleLabel;

    /**
     * Main image view for showing selected image
     */
    @FXML
    private ImageView imageView;

    /**
     * File chooser button
     */
    @FXML
    private Button fileChooserButton;

    /**
     * Saving button
     */
    @FXML
    private Button savingButton;

    /**
     * Slider in which user can choose stamp size
     */
    @FXML
    private Slider stempleSizeSlider;

    /**
     * Image view which showing selected area from main image
     */
    @FXML
    private ImageView selectedAreaImageView;

    /**
     * Selected image
     */
    private ObservableValue<File> selectedImage = new SimpleObjectProperty<>();

    /**
     * Image view controller
     */
    private ImageViewCursorController imageViewCursorController;

    private ImageViewMarkerController imageViewMarkerController;

    /**
     * Method called on window show
     */
    public void handleWindowShownEvent() {
        this.setTitleLabelText();
        this.setFileChooserManagement();
        this.setImageViewManagement();
    }

    /**
     * Adding title text to label on top application and centered them
     */
    private void setTitleLabelText() {
        this.titleLabel.setText(Main.getTITLE());
    }

    /**
     * Managing choosing file
     */
    private void setFileChooserManagement() {
        SelectedImageListener selectedImageListener = new SelectedImageListener(this.imageView);
        this.fileChooserButton.addEventFilter(MouseEvent.MOUSE_PRESSED,
                event -> {
                    selectedImage = new SimpleObjectProperty<>(FileChooserController.trySelectFile().orElse(selectedImage.getValue()));
                    if (selectedImage.getValue() != null) {
                        selectedImageListener.addSelectedImageToView(selectedImage.getValue());
                    }
                });
    }

    /**
     * Managing operations on ImageView node
     */
    private void setImageViewManagement() {
        this.imageViewCursorController = new ImageViewCursorController(this.imageView);
        this.imageViewCursorController.setStempleSizeSlider(this.stempleSizeSlider);
        this.imageViewCursorController.addEventListener();

        this.imageViewMarkerController = new ImageViewMarkerController(this.imageView);
        this.imageViewMarkerController.setStempleSizeSlider(this.stempleSizeSlider);
        this.imageViewMarkerController.addEventListener();
        this.imageViewMarkerController.setSelectedAreaImageView(this.selectedAreaImageView);
    }

}
