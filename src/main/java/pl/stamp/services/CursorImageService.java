package pl.stamp.services;

import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Krzysztof Adamczyk on 20.10.2017.
 */
public class CursorImageService {

    public static ImageCursor getCircleCursorImage(double radius) {
        Circle circle = new Circle();
        circle.setRadius(radius);
        circle.setStroke(Color.BLACK);

        SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);

        Image image = circle.snapshot(sp, null);

        return new ImageCursor(image);
    }
}
