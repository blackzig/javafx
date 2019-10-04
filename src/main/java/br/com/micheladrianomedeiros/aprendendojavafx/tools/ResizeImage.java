/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.micheladrianomedeiros.aprendendojavafx.tools;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author zigui https://www.mkyong.com/java/how-to-resize-an-image-in-java/
 */
public class ResizeImage {

    public static BufferedImage returnImageResize(String localFile, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizeImagePng = null;
        try {

            BufferedImage originalImage = ImageIO.read(new File(localFile));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage(originalImage, type, IMG_WIDTH, IMG_HEIGHT);
            ImageIO.write(resizeImageJpg, "jpg", new File(localFile));

            resizeImagePng = resizeImage(originalImage, type, IMG_WIDTH, IMG_HEIGHT);
            ImageIO.write(resizeImagePng, "png", new File(localFile));

            BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type, IMG_WIDTH, IMG_HEIGHT);
            ImageIO.write(resizeImageHintJpg, "jpg", new File(localFile));

            BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type, IMG_WIDTH, IMG_HEIGHT);
            ImageIO.write(resizeImageHintPng, "png", new File(localFile));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return resizeImagePng;

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

}
