package dev.davidebilardello;

public class Rect {
    int xPoint1, yPoint1, xPoint2, yPoint2;
    boolean isPvpEnabled;


    public Rect(int xPoint1, int yPoint1, int xPoint2, int yPoint2, boolean isPvpEnabled) {

        this.xPoint1 = xPoint1;
        this.xPoint2 = xPoint2;
        this.yPoint1 = yPoint1;
        this.yPoint2 = yPoint2;
        this.isPvpEnabled = isPvpEnabled;

    }

    public int getxPoint1() {
        return xPoint1;
    }

    public int getyPoint1() {
        return yPoint1;
    }

    public int getxPoint2() {
        return xPoint2;
    }

    public int getyPoint2() {
        return yPoint2;
    }

    public boolean isPvpEnabled() {
        return isPvpEnabled;
    }
}
