package pl.stamp.services;

import javafx.scene.image.PixelReader;

import java.util.ArrayList;
import java.util.List;

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
                    copiedPixels.add(pixelReader.getArgb(x, y));
                }

            }
        }

        return copiedPixels;
    }
}
