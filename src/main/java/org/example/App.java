package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        BufferedImage initialImage = readImageFromPath(args[0]);
        for (int i = 0; i < 5; i++) {
            BufferedImage card = solution.getCardByNum(initialImage, i);
            if (solution.checkIsCardExist(card)) {
                System.out.printf("%s%s", solution.getValue(card), solution.getSuit(card).charAt(0));
            }
        }
        System.out.println();
    }

    static BufferedImage readImageFromPath(String filePath) throws IOException {
        File file = new File(filePath);
        return ImageIO.read(file);
    }
}
