import org.example.App;
import org.example.Solution;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class TestSuits {
    String file = "dummy.png";

    @Test
    void testCheckIsCardExist() throws IOException {
        Solution solution = new Solution();
        BufferedImage initialImage = ImageIO.read(Objects.requireNonNull(App.class.getClassLoader().getResourceAsStream(file)));
        for (int i = 0; i < 5; i++) {
            if (solution.checkIsCardExist(initialImage.getSubimage(142 + i * 72, 584, 66, 91))) {
                System.out.printf("card %s exists ", i);
                System.out.println();
            } else {
                System.out.printf("card %s not exists ", i);
                System.out.println();
            }
        }
    }
}
