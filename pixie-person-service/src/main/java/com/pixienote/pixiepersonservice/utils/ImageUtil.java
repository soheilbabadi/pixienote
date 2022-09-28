package com.pixienote.pixiepersonservice.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtil {


    public static byte[] thumbnail(MultipartFile file) throws IOException {
        int targetWidth = 150;
        int targetHeight = 150;
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        var tumb = thumbnail(originalImage);

        ByteArrayOutputStream bass = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", bass);
        byte[] bytes = bass.toByteArray();
        return bytes;

    }

    public static BufferedImage thumbnail(BufferedImage originalImage) throws IOException {
        int targetWidth = 150;
        int targetHeight = 150;

        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }


}
