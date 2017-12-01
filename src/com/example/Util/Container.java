package com.example.Util;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.Observable;

public class Container {
    private Group container;
    public Container(Group g) {
        container = g;
    }

    public Container AddThing(Node e) {
        ObservableList<Node> child = container.getChildren();
        child.add(e);
        return this;
    }
}
