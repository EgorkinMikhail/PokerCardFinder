package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CardAutoCoder {

  public String cardAutoCode(BufferedImage subImage) {
    List<Color> colourSinMatrix = new ArrayList<>();
    for (int x = 0; x < subImage.getWidth(); x++) {
      for (int y = 0; y < subImage.getHeight(); y++) {
        Color c = new Color(subImage.getRGB(x, y));
        if (!(c.getRed() > 100 && c.getGreen() > 100 && c.getBlue() > 100)
        && (!c.equals(new Color(120, 120, 120)))) {
          colourSinMatrix.add(c);
        }
      }
    }
    return "dummy";
  }
}
// 7dJc6sAh5s
// 10s7dKh