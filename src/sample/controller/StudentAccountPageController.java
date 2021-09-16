package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentAccountPageController {
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    private Button goStdLogInPage;
    @FXML
    void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/view/loginPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void goToCreateAccountPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/view/createStudentAccountPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void goStdLogInPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/view/loginStudentAccountPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
