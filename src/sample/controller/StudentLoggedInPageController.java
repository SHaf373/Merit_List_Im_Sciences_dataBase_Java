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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.database.Database_Handler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentLoggedInPageController {


    //-------------
    @FXML
    private Text stdAccountNameText;
    @FXML
    private Text idNumberText;
    @FXML
    private Text stdLastName;

    @FXML
    private Text programText;

    @FXML
    private Text marksText;

    @FXML
    private Text phoneNoText;

    @FXML
    private Text dateOfBirthText;

    @FXML
    private Text genderText;

    @FXML
    private Text adressText;

    @FXML
    void stdLogOutBtn(ActionEvent event) throws IOException {

    }
//
//    String password = "";
//
//    @FXML
//    String getInfoLogInBtn(ActionEvent event){
//        this.password = stdLogInPasswordField.getText();
//        System.out.println(password);
//        return password;
//    }
    public void showPage() throws IOException {

         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/sample/view/StudentLoggedInPage.fxml"));
         loader.setRoot(loader.getRoot());
         loader.load();
         Parent root = loader.getRoot();
         loader.getController();
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.showAndWait();
    }


    public void fetchData(String password) throws SQLException, ClassNotFoundException {
        LoginStudentAccountPage studentAccountPage = new LoginStudentAccountPage();
        Database_Handler database_handler = new Database_Handler();
        Connection connection = database_handler.get_Connection();
        LoginStudentAccountPage obj = new LoginStudentAccountPage();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from student where password = ? ");
        preparedStatement.setString(1,password);
        ResultSet resultSet = preparedStatement.executeQuery();
      //  idNumberText.setText(resultSet.getString(1));
      //  programText.setText(resultSet.getString("program"));
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
            idNumberText.setText("ID: " + resultSet.getString(1));
            stdAccountNameText.setText(resultSet.getString(2));
            stdLastName.setText(resultSet.getString(3));
            phoneNoText.setText(resultSet.getString("phone_no"));
            programText.setText(resultSet.getString("program"));
            genderText.setText(resultSet.getString("gender"));
            adressText.setText(resultSet.getString("address"));
            dateOfBirthText.setText(resultSet.getString("date_of_birth"));

        }
    }
    String password = "";
    @FXML
    void stdLogInButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        Database_Handler database_handler = new Database_Handler();
        Connection connection = database_handler.get_Connection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from student where password =?");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));

//            idNumberText.setText("ID: " + resultSet.getString(1));
//            stdAccountNameText.setText(resultSet.getString(2));
//            stdLastName.setText(resultSet.getString(3));
//            phoneNoText.setText(resultSet.getString("phone_no"));
//            programText.setText(resultSet.getString("program"));
//            genderText.setText(resultSet.getString("gender"));
//            adressText.setText(resultSet.getString("address"));
//            dateOfBirthText.setText(resultSet.getString("date_of_birth"));

        }


    }

}
