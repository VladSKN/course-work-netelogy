package ru.netology.graphics.ru.netology.graphics.image;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverter implements ru.netology.graphics.image.TextGraphicsConverter {

    private int maxWidth;
    private int maxHeight;
    private double maxRatio;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        maximumRatio(img);
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();
        if (img.getWidth() > getMaxWidth()) {
            newWidth = getMaxWidth();
            newHeight = (newWidth * img.getHeight()) / img.getWidth();
        }

        if (newHeight > getMaxHeight()) {
            newHeight = getMaxHeight();
            newWidth = (newHeight * img.getWidth()) / img.getHeight();
        }
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        var bwRaster = bwImg.getRaster();
        ru.netology.graphics.ru.netology.graphics.image.TextColorSchema textColorSchema
                = new ru.netology.graphics.ru.netology.graphics.image.TextColorSchema();
        char[][] mas = new char[bwImg.getHeight()][bwImg.getWidth()];
        for (int w = 0; w < bwImg.getWidth(); w++) {
            for (int h = 0; h < bwImg.getHeight(); h++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = textColorSchema.convert(color);
                mas[h][w] = c;
            }
        }
        StringBuilder sb = new StringBuilder();
        printText(mas, sb);
        return sb.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    public void maximumRatio(BufferedImage img) throws BadImageSizeException {
        double ratio;
        if (img.getWidth() > img.getHeight()) {
            ratio = img.getWidth() / (double) img.getHeight();
        } else {
            ratio = img.getHeight() / (double) img.getWidth();
        }

        if (Math.abs(ratio) > Math.abs(getMaxRatio())) {
            throw new BadImageSizeException(getMaxRatio(), ratio);
        }
    }

    public void printText(char[][] mas, StringBuilder sb) {
        for (char[] ma : mas) {
            for (char c : ma) {
                sb.append(c).append(" ");
            }
            sb.append("\n");
        }
    }
}
