package pl.stamp.services;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Krzysztof Adamczyk on 22.10.2017.
 */
public class PixelCopyService {

    public static List<Integer> getCopiedPixelsList(PixelReader pixelReader, double radius, int selectedAreaCenterX, int selectedAreaCenterY) {
        List<Integer> copiedPixels = new ArrayList<>();

        for(int x = (int)(selectedAreaCenterX - radius); x <= (int)(selectedAreaCenterX + radius); x++) {
            for (int y = (int)(selectedAreaCenterY - radius); y <= (int)(selectedAreaCenterY + radius); y++) {
                double squaredDx = Math.pow((x - selectedAreaCenterX), 2);
                double squaredDy = Math.pow((y - selectedAreaCenterY), 2);
                double rootDistance = Math.sqrt(squaredDx + squaredDy);

                if (rootDistance <= radius) {
                    try {
                        copiedPixels.add(pixelReader.getArgb(x, y));
                    } catch (IndexOutOfBoundsException e) {
                        copiedPixels.add(null);
                    }
                }
            }
        }

        return copiedPixels;
    }

    public static WritableImage getImageWithCopiedPixels(List<Integer> copiedPixels, double radius) {
        WritableImage dest = createWritableImage(radius);
        PixelWriter writer = dest.getPixelWriter();

        int listIndex = 0;
        for(int x = 0; x < (int)(radius*2); x++) {
            for (int y = 0; y < (int) (radius*2); y++) {
                double squaredDx = Math.pow((x - radius), 2);
                double squaredDy = Math.pow((y - radius), 2);
                double rootDistance = Math.sqrt(squaredDx + squaredDy);
                // todo check pixel is in image if not then put something like empty pixel or null
                if (rootDistance <= radius) {
                    Integer pixel = copiedPixels.get(listIndex++);
                    if (pixel == null) {
                        continue;
                    }
                    writer.setArgb(x, y, pixel);
                }
            }
        }

        return dest;
    }

    public static WritableImage putSelectedAreaToImage(Image image, List<Integer> copiedPixels, double radius, int selectedAreaCenterX, int selectedAreaCenterY) {
        WritableImage dest = ImagesConverterService.convertToWritable(image);
        PixelWriter writer = dest.getPixelWriter();

        int width = (int)image.getWidth();
        int height = (int)image.getHeight();

        int listIndex = 0;
        for(int x = (int)(selectedAreaCenterX - radius); x <= (int)(selectedAreaCenterX + radius); x++) {
            for (int y = (int)(selectedAreaCenterY - radius); y <= (int)(selectedAreaCenterY + radius); y++) {
                double squaredDx = Math.pow((x - selectedAreaCenterX), 2);
                double squaredDy = Math.pow((y - selectedAreaCenterY), 2);
                double rootDistance = Math.sqrt(squaredDx + squaredDy);

                if (rootDistance <= radius) {
                    boolean isPixelInImage = x >= 0  && x < width && y >= 0 && y < height;
                    if (isPixelInImage) {
                        try {
                            writer.setArgb(x, y, copiedPixels.get(listIndex));
                        } catch (NullPointerException e) {
                            continue;
                        }
                    }
                    listIndex++;
                }
            }
        }

        return dest;
    }

    private static WritableImage createWritableImage(double radius) {
        return new WritableImage((int)(radius * 2), (int)(radius * 2));
    }
}
