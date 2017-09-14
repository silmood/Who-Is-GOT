package io.bunsan.who_is;

public class Character {
    private int nameResId;
    private int imageResId;

    public Character(int nameResId, int imageResId) {
        this.nameResId = nameResId;
        this.imageResId = imageResId;
    }

    public int getNameResId() {
        return nameResId;
    }

    public int getImageResId() {
        return imageResId;
    }
}