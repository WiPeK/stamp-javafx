package pl.stamp.controllers.imageview;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import pl.stamp.services.ImagesConverterService;
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

            int width = (int)this.imageView.getImage().getWidth();
            int height = (int)this.imageView.getImage().getHeight();

            if (event.isShiftDown()) {
                copiedPixels.clear();
                WritableImage dest = createWritableImage();
                clearSelectedAreaImageView();

                PixelReader pixelReader = this.imageView.getImage().getPixelReader();
                PixelWriter writer = dest.getPixelWriter();

                copiedPixels = PixelCopyService.getCopiedPixelsList(pixelReader, radius, selectedAreaCenterX, selectedAreaCenterY);

//                for(int x = (int)(clickedX - radius); x <= (int)(clickedX + radius); x++) {
//                    for (int y = (int)(clickedY - radius); y <= (int)(clickedY + radius); y++) {
//                        double squaredDx = Math.pow((x - clickedX), 2);
//                        double squaredDy = Math.pow((y - clickedY), 2);
//                        double rootDistance = Math.sqrt(squaredDx + squaredDy);
//
//                        if (rootDistance <= radius) {
//                            copiedPixels.add(pixelReader.getArgb(x, y));
//                        }
//
//                    }
//                }

                int listIndex = 0;
                for(int x = 0; x < (int)(radius*2); x++) {
                    for (int y = 0; y < (int) (radius*2); y++) {
                        double squaredDx = Math.pow((x - radius), 2);
                        double squaredDy = Math.pow((y - radius), 2);
                        double rootDistance = Math.sqrt(squaredDx + squaredDy);

                        if (rootDistance <= radius) {
                            writer.setArgb(x, y, copiedPixels.get(listIndex++));
                        }
                    }
                }

                this.selectedAreaImageView.setImage(dest);
            }
//-0.5radius
            if (!copiedPixels.isEmpty()) {
                WritableImage dest = ImagesConverterService.convertToWritable(this.imageView.getImage());
                PixelWriter writer = dest.getPixelWriter();

                int listIndex = 0;
                for(int x = (int)(selectedAreaCenterX - radius); x <= (int)(selectedAreaCenterX + radius); x++) {
                    for (int y = (int)(selectedAreaCenterY - radius); y <= (int)(selectedAreaCenterY + radius); y++) {
                        double squaredDx = Math.pow((x - selectedAreaCenterX), 2);
                        double squaredDy = Math.pow((y - selectedAreaCenterY), 2);
                        double rootDistance = Math.sqrt(squaredDx + squaredDy);

                        if (rootDistance <= radius) {
                            boolean isPixelInImage = x >= 0  && x <= width && y >= 0 && y <= height;
                            if (isPixelInImage) {
                                writer.setArgb(x, y, copiedPixels.get(listIndex++));
                            } else {
                                listIndex++;
                            }

                        }

                    }
                }
                this.imageView.setImage(dest);
            }

        });
    }

    private WritableImage createWritableImage() {
        return new WritableImage((int)(radius * 2), (int)(radius * 2));
    }

    private void clearSelectedAreaImageView() {
        this.selectedAreaImageView.setImage(null);
        this.selectedAreaImageView.setFitWidth((int)(radius * 2));
        this.selectedAreaImageView.setFitHeight((int)(radius * 2));
    }
}
