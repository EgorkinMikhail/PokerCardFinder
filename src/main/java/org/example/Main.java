package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {

    if (args.length != 1) {
      System.err.println("Usage: java PNGInfo <path_to_png_file>");
      System.exit(1);
    }

    String filePath = args[0];
    File file = new File(filePath);

    if (!file.exists()) {
      System.err.println("File does not exist: " + filePath);
      System.exit(1);
    }

    try {
      BufferedImage image = ImageIO.read(file);
      if (image == null) {
        System.err.println("The file is not a valid PNG image: " + filePath);
        System.exit(1);
      }
      // 60x90
      // 590 - 680
      // 145 - 205
      BufferedImage subImage = image.getSubimage(145, 590, 55, 80);
      CardAutoCoder cardAutoCoder = new CardAutoCoder();
      String card = cardAutoCoder.cardAutoCode(subImage);
    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
      System.exit(1);
    }
  }
}