package pl.stamp.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.stamp.services.CursorImageService;

/**
 * @author Krzysztof Adamczyk on 20.10.2017.
 */
public class ImageViewController {

    private ImageView imageView;

    private Slider stempleSizeSlider;

    private double radius;

    public ImageViewController(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setStempleSizeSlider(Slider stempleSizeSlider) {
        this.stempleSizeSlider = stempleSizeSlider;

        this.stempleSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            radius = newValue.doubleValue();
        });
    }

    public void addEventListener() {
        this.imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (event.isShiftDown()) {
                this.imageView.getScene().setCursor(CursorImageService.getCircleCursorImage(radius));
            }
        });

        this.imageView.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            if (event.isShiftDown()) {
                this.imageView.getScene().setCursor(CursorImageService.getCircleCursorImage(radius));
            } else {
                this.setDefaultCursor(this.imageView.getScene());
            }
        });

        this.imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.setDefaultCursor(this.imageView.getScene()));
    }

    private void setDefaultCursor(Scene scene) {
        scene.setCursor(Cursor.DEFAULT);
    }

}
