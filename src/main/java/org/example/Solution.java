package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Solution {

    public String getSuit(BufferedImage card) throws IOException {
        long minVal = Long.MAX_VALUE;
        String suitName = "unknown";
        for (String s : new String[]{"clubs", "diamonds", "hearts", "spades"}) {
            BufferedImage cardSuit = getSuitBufferedImage(card);
            BufferedImage etalonSuit = readImageFromResource("suits/" + s + ".png");
            long v = picDiff(cardSuit, etalonSuit, 0, 0);
            if (v < minVal) {
                minVal = v;
                suitName = s;
            }
        }
        return suitName;
    }

    public String getValue(BufferedImage card) throws IOException {
        BufferedImage value = getValueBufferedImage(card);
        long minVal = Long.MAX_VALUE;
        String findValue = "unknown";
        for (String s : new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}) {
            BufferedImage reference = readImageFromResource("values/" + s + ".png");
            int beginX = findBeginX(value);
            int beginXRef = findBeginX(reference);
            int beginY = findBeginY(value);
            int beginYRef = findBeginY(reference);
            long v = picDiff(value, reference, beginX - beginXRef, beginY - beginYRef);
            if (v < minVal) {
                minVal = v;
                findValue = s;
            }
        }
        return findValue;
    }

    private static int findBeginX(BufferedImage img) {
        return findBegin(img, img.getWidth(), img.getHeight(), true);
    }

    private static int findBeginY(BufferedImage img) {
        return findBegin(img, img.getHeight(), img.getWidth(), false);
    }

    private static int findBegin(BufferedImage img, Integer firstMaxSteps, Integer secondMaxSteps, boolean xReturn) {
        for (int i = 0; i < firstMaxSteps; i++) {
            for (int j = 0; j < secondMaxSteps; j++) {
                if (xReturn) {
                    if (img.getRGB(i, j) != -1 && !isBackground(img.getRGB(i, j))) {
                    return i;
                    }
                } else {
                    if (img.getRGB(j, i) != -1 && !isBackground(img.getRGB(j, i))) {
                        return i;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public boolean checkIsCardExist(BufferedImage card) {
        return (isBackground(card.getRGB(7, 7)) || card.getRGB(7, 7) == -1) &&
                (isBackground(card.getRGB(7, card.getHeight() - 8)) || card.getRGB(7, card.getHeight() - 8) == -1) &&
                (isBackground(card.getRGB(card.getWidth() - 8, 7)) || card.getRGB(card.getWidth() - 8, 7) == -1) &&
                (isBackground(card.getRGB(card.getWidth() - 8, card.getHeight() - 8)) || card.getRGB(card.getWidth() - 8, card.getHeight() - 8) == -1);
    }

    private static BufferedImage getSuitBufferedImage(BufferedImage card) {
        return card.getSubimage(25, 50, 33, 33);
    }

    private static BufferedImage getValueBufferedImage(BufferedImage card) {
        return card.getSubimage(6, 7, 32, 25);
    }

    private static long picDiff(BufferedImage examined, BufferedImage reference, int offsetX, int offsetY) {
        long diff = 0;
        for (int i = 0; i < examined.getWidth(); i++) {
            for (int j = 0; j < examined.getHeight(); j++) {
                if ((i + offsetX) < 0 || (i + offsetX) >= examined.getWidth()
                        || (j + offsetY) < 0 || (j + offsetY) >= examined.getHeight()) {
                    continue;
                }
                boolean isBackgroundExamined = examined.getRGB(i + offsetX, j + offsetY) == -1
                        || isBackground(examined.getRGB(i + offsetX, j + offsetY));
                boolean isBackground = reference.getRGB(i, j) == -1;
                if (isBackgroundExamined != isBackground) {
                    diff++;
                }
            }
        }
        return diff;
    }

    static boolean isBackground(int rgb) {
        int c1 = (rgb >> 16) & 0x000000FF;
        int c3 = (rgb) & 0x000000FF;
        return c1 == c3 && c1 == 120;
    }

    private static void saveToFile(BufferedImage subImage, String name) throws IOException {
        File outputfile = new File(name);
        ImageIO.write(subImage, "png", outputfile);
    }

    static BufferedImage readImageFromResource(String name) throws IOException {
        return ImageIO.read(Objects.requireNonNull(App.class.getClassLoader().getResourceAsStream(name)));
    }

    public BufferedImage getCardByNum(BufferedImage initialImage, int cardNum) {
        return initialImage.getSubimage(142 + cardNum * 72, 584, 66, 91);
    }

}
