package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CardAutoCoder {

  public String cardAutoCode(BufferedImage subImage) {
    String[][] colourSinMatrix = new String[subImage.getHeight()][subImage.getWidth()];
    for (int x = 0; x < subImage.getWidth(); x++) {
      for (int y = 0; y < subImage.getHeight(); y++) {
        Color c = new Color(subImage.getRGB(x, y));
        if (!(c.getRed() > 100 && c.getGreen() > 100 && c.getBlue() > 100)) {
          colourSinMatrix[y][x] = "x";
        } else {
          colourSinMatrix[y][x] = "-";
        }
      }
    }

    for (int i = 0; i < colourSinMatrix.length; i++) {
      for (int j = 0; j < colourSinMatrix[i].length; j++) {
        System.out.print(colourSinMatrix[i][j]);
      }
      System.out.println();
    }
    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    return "dummy";
  }
}
// 7dJc6sAh5s
// 10s7dKh