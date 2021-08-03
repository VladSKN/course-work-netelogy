package ru.netology.graphics.ru.netology.graphics.image;

public class TextColorSchema implements ru.netology.graphics.image.TextColorSchema {

    @Override
    public char convert(int color) {
        final char[] symbols = {'#', '$', '@', '%', '*', '+', '-', '\''};
        return symbols[color / 32];
    }
}
