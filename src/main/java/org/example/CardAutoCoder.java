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
        if (!c.equals(new Color(255, 255, 255))
        && (!c.equals(new Color(120, 120, 120)))) {
          colourSinMatrix.add(c);
        }
      }
    }
    return "";
  }
}
