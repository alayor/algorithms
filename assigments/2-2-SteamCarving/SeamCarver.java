import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private final Picture _picture;

    public SeamCarver(Picture picture) {
        _picture = picture;
    }

    public Picture picture() {
        return _picture;
    }

    public int height() {
        return _picture.height();
    }

    public int width() {
        return _picture.width();
    }

    public double energy(int x, int y) {
        double deltaX = getDeltaX(x, y);
        double deltaY = getDeltaY(x, y);
        return Math.sqrt(deltaX + deltaY);
    }

    private double getDeltaX(int x, int y) {
        if(0 < x && x < _picture.width() - 1) {
            Color colorFront = _picture.get(x + 1, y);
            Color colorBack = _picture.get(x - 1, y);
            return getColorDifference(colorFront, colorBack);
        }
        return 0.0;
    }

    private double getColorDifference(Color colorFront, Color colorBack) {
        double deltaRed = Math.pow(colorFront.getRed() - colorBack.getRed(), 2);
        double deltaGreen = Math.pow(colorFront.getGreen() - colorBack.getGreen(), 2);
        double deltaBlue = Math.pow(colorFront.getBlue() - colorBack.getBlue(), 2);
        return deltaRed + deltaGreen + deltaBlue;
    }

    private double getDeltaY(int x, int y) {
        if(0 < y && y < _picture.height() - 1) {
            Color colorUp = _picture.get(x, y - 1);
            Color colorDown = _picture.get(x, y + 1);
            return getColorDifference(colorUp, colorDown);
        }
        return 0.0;
    }

    public static void main(String[] args) {
        SeamCarverTest.runTests();
    }
}