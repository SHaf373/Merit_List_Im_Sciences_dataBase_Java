package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.Database_Handler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginStudentAccountPage implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField stdLogInEmailField;

    @FXML
    private PasswordField stdLogInPasswordField;

    @FXML
    private Button stdLogInButton;


    @FXML
    void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/view/StudentAccountPage.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    String password = "";
    @FXML
    void stdLogInButton(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/sample/view/StudentLoggedInPage.fxml"));
//        scene = new Scene(root);
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//        password = stdLogInPasswordField.getText();
//        System.out.println(password);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stdLogInButton.setOnAction(event -> {
            String emailId = stdLogInEmailField.getText().trim();
            String password = stdLogInPasswordField.getText().trim();
            Database_Handler database_handler = new Database_Handler();
            boolean flag = false;
            try {
                flag = database_handler.validate(emailId,password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(!flag){
                System.out.println("Not match");
            }else {
                System.out.println("Matched");
                StudentLoggedInPageController object = new StudentLoggedInPageController();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/StudentLoggedInPage.fxml"));
                try {
                    Parent root = loader.load();
                    StudentLoggedInPageController studentLoggedInObj = loader.getController();
                    studentLoggedInObj.fetchData(password);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
