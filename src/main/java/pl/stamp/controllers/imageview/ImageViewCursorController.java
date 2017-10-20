package pl.stamp.controllers.imageview;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.stamp.services.CursorImageService;

/**
 * @author Krzysztof Adamczyk on 20.10.2017.
 */
public class ImageViewCursorController extends ImageViewController {

    public ImageViewCursorController(ImageView imageView) {
        super(imageView);
    }

    @Override
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
