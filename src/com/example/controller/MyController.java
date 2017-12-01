package com.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MyController implements Initializable {
    @FXML
    private Button myButton;
    @FXML
    private TextField myTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void showDateTime(MouseEvent event){
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        String dateTimeString = df.format(now);
        myTextField.setText(dateTimeString);

    }
}
