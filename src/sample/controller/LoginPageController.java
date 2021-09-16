package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    void goToStudentAccountLoginPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/view/StudentAccountPage.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void goToAdminLoginPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/view/adminAccountPage.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
