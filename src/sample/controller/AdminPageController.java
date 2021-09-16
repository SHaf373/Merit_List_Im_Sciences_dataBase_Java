package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class AdminPageController {
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    public void goToLoginPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/view/loginPage.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
