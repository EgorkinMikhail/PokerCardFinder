package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        File folder = new File(args[0]);
        File[] listOfFiles = folder.listFiles();
        Solution solution = new Solution();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                if (file.getName().substring(file.getName().length() - 3).equalsIgnoreCase("png")) {
                    BufferedImage initialImage = readImageFromPath(file.getAbsolutePath());
                    for (int i = 0; i < 5; i++) {
                        BufferedImage card = solution.getCardByNum(initialImage, i);
                        if (solution.checkIsCardExist(card)) {
                            System.out.printf("%s%s", solution.getValue(card), solution.getSuit(card).charAt(0));
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    static BufferedImage readImageFromPath(String filePath) throws IOException {
        File file = new File(filePath);
        return ImageIO.read(file);
    }
}