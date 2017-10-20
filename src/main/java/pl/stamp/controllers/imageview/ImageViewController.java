package pl.stamp.controllers.imageview;

import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

/**
 * @author Krzysztof Adamczyk on 20.10.2017.
 */
public abstract class ImageViewController {

    protected ImageView imageView;

    protected Slider stempleSizeSlider;

    protected double radius;

    public ImageViewController(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setStempleSizeSlider(Slider stempleSizeSlider) {
        this.stempleSizeSlider = stempleSizeSlider;

        this.stempleSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            radius = newValue.doubleValue();
        });
    }

    public abstract void addEventListener();
}
