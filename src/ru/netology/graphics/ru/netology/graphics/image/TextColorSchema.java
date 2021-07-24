package ru.netology.graphics.ru.netology.graphics.image;

public class TextColorSchema implements ru.netology.graphics.image.TextColorSchema {

    @Override
    public char convert(int color) {
        if ((color >= 0)&&(color <= 32)){
            return '#';
        }
        if ((color >= 32)&&(color <= 64)){
            return '$';
        }
        if ((color >= 64)&&(color <= 96)){
            return '@';
        }
        if ((color >= 96)&&(color <= 128)){
            return '%';
        }
        if ((color >= 128)&&(color <= 160)){
            return '*';
        }
        if ((color >= 160)&&(color <= 192)){
            return '+';
        }
        if ((color >= 192)&&(color <= 224)){
            return '-';
        }
        if ((color >= 224)&&(color <= 255)){
            return '\'';
        }
        return 0;
    }
}
