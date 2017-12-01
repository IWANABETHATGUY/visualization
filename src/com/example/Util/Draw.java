package com.example.Util;
// every param start with Center need to input a Circle's Center;

import com.sun.javafx.geom.QuadCurve2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Draw {
    public static final float ARROW_LENGTH = 20f;
    public static final float ARROW_HEIGHT = 5;
    private static final float CIRCLE_RADIUS = 20f;
    public static final float TWO_POINT_HV_LENGTH = 140;

    public static Line drawLine(float startX, float startY, float endX, float endY) {
        Line line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        return line;
    }

    public static Circle drawCircle(Point center) {
        Circle circle = new Circle();
        Polygon p = new Polygon();

        circle.setCenterX(center.getX());
        circle.setCenterY(center.getY());
        circle.setRadius(CIRCLE_RADIUS);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        circle.setFill(null);
        return circle;
    }

    public static Text drawIndexText(Point center,  String value) {
        // make the text position better
        Text result = new Text(center.getX() - 5, center.getY() + 7 , value);
        result.setFont(Font.font(20));
        return result;
    }

    public static Text drawCharacterText(Point center,  String value, boolean RECYCLE_FLAG) {
        // make the text position better
        Text result = new Text(center.getX() - 5, center.getY() + 5 , value);
        result.setFont(Font.font(15));
        if (RECYCLE_FLAG) {
            result.setStroke(Color.BLUE);
        } else {
            result.setStroke(Color.RED);
        }
        return result;
    }

    /**
     *
     * @param centerStartPoint
     * @param centerEndPoint
     * @return
     */
    public static Group drawArrowLine(Point centerStartPoint, Point centerEndPoint) {
        Group gg = new Group();
        float dis = Point.DistanceBetweenTwoPoint(centerStartPoint, centerEndPoint);
        Point re1 = Point.PercentPoint(centerStartPoint, centerEndPoint, CIRCLE_RADIUS / dis);
        Point re2 = Point.PercentPoint(centerStartPoint, centerEndPoint, (1 - CIRCLE_RADIUS / dis));

        Point need = Point.PercentPoint(re1, re2, 1 - ARROW_LENGTH / Point.DistanceBetweenTwoPoint(re1, re2));


        Line line = Draw.drawLine(re1.getX(), re1.getY(), re2.getX(), re2.getY());
        Polygon polygon = Draw.drawArrowFromLine(need, re2);

        gg.getChildren().add(line);
        gg.getChildren().add(polygon);


        return gg;
    }

    public static QuadCurve drawQuadLine(Point start, Point control, Point end) {
        Point act1 = new Point();
        Point act2 = new Point();
        if (start.getX() != end.getX()) {
            if (start.getX() < end.getX()) {
                act1.setX((float) (start.getX() + Math.sin(45) * CIRCLE_RADIUS));
                act1.setY((float) (start.getY() - Math.cos(45) * CIRCLE_RADIUS));


                act2.setX((float) (end.getX() - Math.sin(45) * CIRCLE_RADIUS));
                act2.setY((float) (end.getY() - Math.cos(45) * CIRCLE_RADIUS));
            } else {
                act1.setX((float) (start.getX() - Math.sin(45) * CIRCLE_RADIUS));
                act1.setY((float) (start.getY() + Math.cos(45) * CIRCLE_RADIUS));


                act2.setX((float) (end.getX() + Math.sin(45) * CIRCLE_RADIUS));
                act2.setY((float) (end.getY() + Math.cos(45) * CIRCLE_RADIUS));
            }
        } else {
            if (start.getY() > end.getY()) {
                act1.setX((float) (start.getX() - Math.sin(45) * CIRCLE_RADIUS));
                act1.setY((float) (start.getY() - Math.cos(45) * CIRCLE_RADIUS));

                act2.setX((float) (end.getX() - Math.sin(45) * CIRCLE_RADIUS));
                act2.setY((float) (end.getY() + Math.cos(45) * CIRCLE_RADIUS));
            } else {
                act1.setX((float) (start.getX() + Math.sin(45) * CIRCLE_RADIUS));
                act1.setY((float) (start.getY() + Math.cos(45) * CIRCLE_RADIUS));


                act2.setX((float) (end.getX() + Math.sin(45) * CIRCLE_RADIUS));
                act2.setY((float) (end.getY() - Math.cos(45) * CIRCLE_RADIUS));
            }
        }

        QuadCurve q = new QuadCurve(act1.getX(), act1.getY(), control.getX(), control.getY(), act2.getX(), act2.getY());
        q.setStroke(Color.BLACK);
        q.setFill(null);
        q.setStrokeWidth(2);
        return q;
    }

    /**
     *
     * @param act1 the helper to convert to two point of the triangle
     * @param act2 what ever the act2 is the target of the line
     * @return
     */
    public static Polygon drawArrowFromLine(Point act1, Point act2) {
        double y = act2.getY() - act1.getY();
        double x = act2.getX() - act1.getX();
        float angle = (float) Math.atan2(y, x);

        Point p1 = new Point((float) (act1.getX() - Math.sin(angle) * ARROW_HEIGHT) , (float)(act1.getY()
                + Math.cos(angle) * ARROW_HEIGHT));
        Point p2 = new Point((float) (act1.getX() + Math.sin(angle) * ARROW_HEIGHT) , (float)(act1.getY() - Math.cos(angle) * ARROW_HEIGHT));


        return new Polygon(p1.getX(), p1.getY(), p2.getX(), p2.getY(), act2.getX(), act2.getY());
    }



    public static Point PercentQuadCurve(Point start, Point end, Point control, float t){
        float temp = 1 - t;
        float x = temp * temp * start.getX() + 2 * t * temp* control.getX() + t * t * end.getX();
        float y = temp* temp * start.getY() + 2 * t * temp * control.getY() + t * t * end.getY();
        return new Point(x, y);
    }


    public static Group drawQuadArrowLine(Point centerStart, Point centerControl , Point centerEnd, float t) {
        Group gg = new Group();
        QuadCurve q = Draw.drawQuadLine(centerStart, centerControl, centerEnd);

        Point act1 = new Point((float)q.getStartX(),(float) q.getStartY());
        Point act2 = new Point((float)q.getEndX(), (float)q.getEndY());


        Point need = Draw.PercentQuadCurve(act1,act2, centerControl, t);


        Polygon polygon = Draw.drawArrowFromLine(need, act2);

        gg.getChildren().add(q);
        gg.getChildren().add(polygon);


        return gg;
    }

    public static QuadCurve Recycle(Point center) {
        Point act1 = new Point (center.getX() - CIRCLE_RADIUS, center.getY());
        Point act2 = new Point (center.getX() , center.getY() + CIRCLE_RADIUS);
        Point control = new Point(center.getX() - (float)(Math.sin(45) * CIRCLE_RADIUS * 3) , center.getY() +  (float)(Math.sin(45) * CIRCLE_RADIUS * 3));
        QuadCurve q = new QuadCurve(act1.getX(), act1.getY(), control.getX(), control.getY(), act2.getX(), act2.getY());
        q.setStrokeWidth(2);
        q.setStroke(Color.BLUE);
        q.setFill(null);
        return q;
    }

    public static Group drawRecycleArrowLine(Point center) {
        Group gg = new Group();
        QuadCurve q = Draw.Recycle(center);

        Point act1 = new Point((float)q.getStartX(),(float) q.getStartY());
        Point act2 = new Point((float)q.getEndX(), (float)q.getEndY());
        Point centerControl = new Point((float)q.getControlX(), (float)q.getControlY());

        Point need = Draw.PercentQuadCurve(act1,act2, centerControl, 0.9f);


        Polygon polygon = Draw.drawArrowFromLine(need, act2);

        gg.getChildren().add(q);
        gg.getChildren().add(polygon);

        return gg;
    }

    public static Point getControlPoint(Point centerStart, Point centerEnd) {
        if ((centerStart.getX() != centerEnd.getX()) && (centerStart.getY() != centerEnd.getY())) {
            return new Point((centerStart.getX() + centerEnd.getX()) / 2, (centerStart.getY() + centerEnd.getY()) / 2);
        } else {
            if (centerStart.getX() != centerEnd.getX()) {
                int timesOfHVLength = (int)(Math.abs(centerEnd.getX() - centerStart.getX())) / (int)(TWO_POINT_HV_LENGTH);
                if (centerStart.getX() < centerEnd.getX()) {

                    return new Point((centerStart.getX() + centerEnd.getX()) / 2, centerStart.getY() - timesOfHVLength * CIRCLE_RADIUS * 2);

                } else {

                    return new Point((centerStart.getX() + centerEnd.getX()) / 2, centerStart.getY() + timesOfHVLength * CIRCLE_RADIUS * 2);
                }
            } else {
                int timesOfHVLength = (int)(Math.abs(centerEnd.getY() - centerStart.getY())) / (int)(TWO_POINT_HV_LENGTH);
                if (centerStart.getY() > centerEnd.getY()) {

                    return new Point(centerStart.getX() - timesOfHVLength * CIRCLE_RADIUS * 2, (centerStart.getY() + centerEnd.getY()) / 2);

                } else {

                    return new Point(centerStart.getX() + timesOfHVLength * CIRCLE_RADIUS * 2, (centerStart.getY() + centerEnd.getY()) / 2);

                }
            }
        }
    }

    public static float getT(Point centerStart, Point centerEnd) {
        int timesOfHVLength = (int)(Math.abs(centerEnd.getX() - centerStart.getX())) / (int)(TWO_POINT_HV_LENGTH);
        return (float)(0.9 + 0.02 * (timesOfHVLength));
    }
    public static Point getCenterPoint(Point centerStart, Point centerEnd) {
        return new Point((centerStart.getX() + centerEnd.getX()) / 2, (centerStart.getY() + centerEnd.getY()) / 2);
    }
}
