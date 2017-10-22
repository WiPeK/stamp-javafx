package pl.stamp.controllers.imageview;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import pl.stamp.services.PixelCopyService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Adamczyk on 20.10.2017.
 */
public class ImageViewMarkerController extends ImageViewController {

    private ImageView selectedAreaImageView;

    private List<Integer> copiedPixels = new ArrayList<>();

    public ImageViewMarkerController(ImageView imageView) {
        super(imageView);
    }

    public void setSelectedAreaImageView(ImageView selectedAreaImageView) {
        this.selectedAreaImageView = selectedAreaImageView;
    }

    @Override
    public void addEventListener() {
        this.imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            int selectedAreaCenterX = (int)event.getX();
            int selectedAreaCenterY = (int)event.getY();

            if (event.isShiftDown()) {
                copiedPixels.clear();
                clearSelectedAreaImageView();

                copiedPixels = PixelCopyService
                        .getCopiedPixelsList(
                                this.imageView.getImage().getPixelReader(),
                                radius,
                                selectedAreaCenterX,
                                selectedAreaCenterY);

                WritableImage dest = PixelCopyService.getImageWithCopiedPixels(copiedPixels, radius);
                this.selectedAreaImageView.setImage(dest);
            }

            if (!copiedPixels.isEmpty()) {
                WritableImage dest =
                        PixelCopyService.putSelectedAreaToImage(
                                this.imageView.getImage(),
                                copiedPixels,
                                radius,
                                selectedAreaCenterX,
                                selectedAreaCenterY);

                this.imageView.setImage(dest);
            }
        });
    }

    private void clearSelectedAreaImageView() {
        this.selectedAreaImageView.setImage(null);
        this.selectedAreaImageView.setFitWidth((int)(radius * 2));
        this.selectedAreaImageView.setFitHeight((int)(radius * 2));
    }
}
