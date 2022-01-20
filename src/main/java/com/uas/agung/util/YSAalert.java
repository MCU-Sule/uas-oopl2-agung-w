package com.uas.agung.util;

import javafx.scene.control.Alert;

import java.util.Objects;

public class YSAalert {
    public void showAlertErr(String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ERROR");
        alert.show();
        alert.setContentText(content);
    }
    public void showAlertInf(String content, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Objects.requireNonNullElse(title, "Information"));
        alert.setHeaderText("Information");
        alert.show();
        alert.setContentText(content);
    }
    public void showAlertCon(String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.show();
        alert.setContentText(content);
    }
    public Alert showLoading(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Harap Tunggu");
        alert.setHeaderText("loading...");
        return alert;
    }
}
