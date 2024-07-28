import org.example.Solution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestSolution {

    Solution solution = new Solution();

    @Test
    public void stt() throws Exception {
        Path cards = Paths.get(this.getClass().getResource("cards").toURI());
        try (Stream<Path> paths = Files.walk(cards)) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach((Path f) -> {
                        String fileName = f.getFileName().toString().replace(".png", "");
                        BufferedImage bufferedImage = readImageFromPath(f.toString());
                        String encodedStr = getEncodedStr(bufferedImage);
                        Assertions.assertEquals(fileName, encodedStr);
                        System.out.println("Checking: " + fileName + " - OK");
                    });
        }
    }

    String getEncodedStr(BufferedImage img) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            BufferedImage card = solution.getCardByNum(img, i);
            if (!solution.checkIsCardExist(card)) {
                break;
            }
            String suit;
            String value;
            try {
                suit = solution.getSuit(card);
                value = solution.getValue(card);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            encoded.append(value).append(suit.charAt(0));
        }
        return encoded.toString();
    }

    static BufferedImage readImageFromPath(String filePath) {
        File file = new File(filePath);
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
