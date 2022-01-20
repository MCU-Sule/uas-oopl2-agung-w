package com.uas.agung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FirstController {
    public TextField id;
    public PasswordField password;
    public Button btnLogin;

    @FXML
    private void loginAction() throws IOException {
        if (id.getText().equals("1972050")&&password.getText().equals("a123")){
            Stage new_stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HelloApplication.class.getResource("main-view.fxml"));
            Locale locale=new Locale("EN");
            if (btnLogin.getText().equals("Masuk")){
                locale=new Locale("KO");
            }
            fxmlLoader.setResources(ResourceBundle.getBundle("bundle",locale));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            new_stage.setTitle("Login");
            new_stage.initModality(Modality.WINDOW_MODAL);
            new_stage.initOwner(password.getScene().getWindow());
            new_stage.setScene(scene);
            new_stage.show();
        }
    }
}
