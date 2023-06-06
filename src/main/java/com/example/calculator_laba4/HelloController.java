package com.example.calculator_laba4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Label result;

    @FXML
    private Button clear;

    @FXML
    private TextField x;

    @FXML
    private TextField y;

    private boolean check = true;
    private String expression;
//
//    public void number(String number) {
//        result.setText(result.getText() + number);
//    }

    public void computeProcess(ActionEvent event) {
        if(check) {
            result.setText("");
            check = false;
        }

        Button button = (Button)event.getSource();
        String value = button.getText();

        if (result.getText().length() < 25) {
            if ("0123456789xy.()".contains(value)) {
                result.setText(result.getText() + value);
            } else {
                result.setText(result.getText() + " " + value + " ");
            }
        }
    }

    public void operatorProcess(ActionEvent event) {
        String temp = result.getText();
        String valueX = x.getText();
        String valueY = y.getText();
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == 'x') {
                temp = temp.substring(0, i) + valueX + temp.substring(i + 1);
            } else if (temp.charAt(i) == 'y') {
                temp = temp.substring(0, i) + valueY + temp.substring(i + 1);
            }
        }
        expression = temp;

        if (divisionByZero(expression)) {
            result.setText("Ошибка");
        }
        else {
            result.setText(Calculator.compute(expression));
        }
        check = true;
    }

    public void clear(ActionEvent event) {
        if (event.getSource() == clear) {
            result.setText("0");
            check = true;
        }
    }

    public void clearLast(ActionEvent event) {
        result.setText(result.getText().substring(0, result.getText().length() - 1));
    }

    public boolean divisionByZero(String exp) {
        for (int i = 0; i < exp.length() - 2; i++) {
             if (exp.charAt(i) == '/' && exp.charAt(i + 2) == '0') {
                 return true;
             }
        }
        return false;
    }

    public void exit() {
        System.exit(0);
    }


}