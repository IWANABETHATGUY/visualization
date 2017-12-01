package com.example;

import com.example.Util.Container;
import com.example.Util.Draw;
import com.example.Util.Point;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.ws.soap.Addressing;
import java.util.*;

public class Main extends Application {
    private int characterSize;
    private   String characterSet;
    private   int stateCount;
    private   int[] stateSet ;
    private   int startStateNumber;
    private   int endStateCount;
    private   int[] endStateSet;
    private  int[][] stateTable;

    // CurrentStateSet
    private Set<Integer> curStateSet;

    // list of Point
    private List<Point> pointList;


    public  final float ARROW_LENGTH = 30f;
    private  final float CIRCLE_RADIUS = 20f;
    public  final float ARROW_HEIGHT = 5;
    public final float TWO_POINT_HV_LENGTH = 140;
    @Override
    public void start(Stage primaryStage) throws Exception{
//        InputFromUser();
//        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("DFA");
        float a = TWO_POINT_HV_LENGTH;
        Group g = new Group();
        Container container = new Container(g);

        Point point1 = new Point(40, 300);
        Point point2 = new Point(180, 300);
        Point point3 = new Point(180, 160);
        Point point4 = new Point(320, 300);
        Point point5 = new Point(320, 160);
        Point point6 = new Point(320, 20);

        Scene scene = new Scene(g, 800, 600);

        Circle circle1 = Draw.drawCircle(point1);
        Circle circle2 = Draw.drawCircle(point2);
        Circle circle3= Draw.drawCircle(point3);
        Circle circle4 = Draw.drawCircle(point4);
        Circle circle5 = Draw.drawCircle(point5);
        Circle circle6 = Draw.drawCircle(point6);


        Group q1 = Draw.drawQuadArrowLine(point6,Draw.getControlPoint(point6, point4), point4, Draw.getT(point4, point6));
        Group q2 = Draw.drawQuadArrowLine(point4, Draw.getControlPoint(point4, point6),point6, Draw.getT(point4, point6));
        Group q3 = Draw.drawQuadArrowLine(point4,Draw.getControlPoint(point4, point5),point5, Draw.getT(point4, point5));

        Text q2_title = Draw.drawCharacterText(new Point(180, 260), "b", false);
        Text q1_title = Draw.drawCharacterText(new Point(110, 280), "a", false);

        Text text1 = Draw.drawIndexText(point1, "0");
        Text text2 = Draw.drawIndexText(point2, "1");
        Text text3 = Draw.drawIndexText(point3, "2");
        Text text4 = Draw.drawIndexText(point4, "3");
        Text text5 = Draw.drawIndexText(point5, "4");
        Text text6 = Draw.drawIndexText(point6, "5");



//        Group gg = Draw.drawArrowLine(point1, point2);
//        Group gg2 = Draw.drawArrowLine(new Point(40, 300), new Point(320, 160));

        Group recycleArrow1 = Draw.drawRecycleArrowLine(new Point(180, 300));

        container.AddThing(circle1)
                .AddThing(circle2)
                .AddThing(circle3)
                .AddThing(circle4)
                .AddThing(text1)
                .AddThing(text2)
                .AddThing(text3)
                .AddThing(text4)
                .AddThing(text5)
                .AddThing(text6)
                .AddThing(q1)
                .AddThing(q2)
                .AddThing(q3)
                .AddThing(q1_title)
                .AddThing(q2_title)
                .AddThing(recycleArrow1)
                .AddThing(circle5)
                .AddThing(circle6);

        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

    // input from keyboard
    private void InputFromUser() {
        Scanner scanner = new Scanner(System.in);
        characterSize = scanner.nextInt();
        String cur = scanner.next();
        characterSet = cur;
        stateCount  = scanner.nextInt();
        stateSet = new int[stateCount];
        for (int i = 0 ; i < stateCount; i++) {
            stateSet[i] = scanner.nextInt();
        }
        startStateNumber = scanner.nextInt();
        endStateCount = scanner.nextInt();
        endStateSet = new int[endStateCount];
        for(int i = 0; i < endStateCount; i++) {
            endStateSet[i] = scanner.nextInt();
        }
        stateTable = new int[stateCount][characterSize];
        for (int i = 0; i < stateCount;i++) {
            for (int j = 0; j < characterSize;j++){
                stateTable[i][j] = scanner.nextInt();
            }
        }
    }

    // initData
    private void initData() {
        curStateSet = new HashSet<>();
        pointList = new ArrayList<>();

    }


}
