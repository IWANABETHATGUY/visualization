package com.example.Util;

public class Point {
    private float x;
    private float y;
    public  int recycleCount = 0;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return two point distance
     * @param one (Point)
     * @param two (Point)
     * @return float
     */
    public static float DistanceBetweenTwoPoint(Point one , Point two) {
        float xValue = one.getX() - two.getX();
        double x = xValue * xValue;

        float yValue = one.getY() - two.getY();
        double y = yValue * yValue;
        double result = Math.sqrt(x + y);
        return new Double(result).floatValue();

    }

    public Point() {

    }

    public static Point PercentPoint(Point start, Point end,float t) {
        Point result = new Point();
        float x = t * (end.getX() - start.getX()) + start.getX();
        float y = t * (end.getY() - start.getY()) + start.getY();
        result.setX(x);
        result.setY(y);
        return result;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
