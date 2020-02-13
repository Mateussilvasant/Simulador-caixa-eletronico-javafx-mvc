package br.com.bancoms.util;

public class Metrics {

    public static int originalWidth;
    public static int originalHeight;
    public static double XWindow;
    public static double YWindow;

    public Metrics(double originalWidth, double originalHeight) {
        this.setOriginalHeight((int) originalHeight);
        this.setOriginalWidth((int) originalWidth);
    }

    public int getPX(double size, boolean option) {
        if (option) {
            return (int) Math.round(getWidth() * size);
        }
        return (int) Math.round(getHeight() * size);
    }

    public int getPX(double size) {
        return (int) Math.round((getHeight() + getWidth()) * size);
    }

    public double getX(double position) {
        return getWidth() * position;
    }

    public double getY(double position) {
        return getHeight() * position;
    }

    public int getWidth() {
        return originalWidth;
    }

    private void setOriginalWidth(int originalWidth) {
        this.originalWidth = originalWidth;
    }

    public int getHeight() {
        return originalHeight;
    }

    private void setOriginalHeight(int originalHeight) {
        this.originalHeight = originalHeight;
    }

    public void setXWindow(double xWindow) {
        this.XWindow = xWindow;
    }

    public double getXWindow() {
        return XWindow;
    }

    public void setYWindow(double yWindow) {
        this.YWindow = yWindow;
    }

    public double getYWindow() {
        return YWindow;
    }
}
