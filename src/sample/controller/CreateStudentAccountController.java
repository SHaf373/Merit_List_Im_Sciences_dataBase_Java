package sample.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.database.Database_Handler;
import sun.plugin.javascript.navig.Array;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateStudentAccountController implements Initializable {
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    private TextField stdFirstNameField;

    @FXML
    private TextField stdAgeField;

    @FXML
    private TextField stdFatherNameField;

    @FXML
    private TextField stdLastNameField;

    @FXML
    private TextField stdEmaildField;

    @FXML
    private Button createStdAccountBtn;

    @FXML
    private ComboBox<String> selectProgramComboBox;

    @FXML
    private RadioButton maleRadioBtn;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton femaleRadioBtn;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNoField;
    @FXML
    private TextField addressField;

    @FXML
    void selectItem(ActionEvent event) {
        String s = selectProgramComboBox.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/view/StudentAccountPage.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] array = {"BSSE","BCS","BBA","BS Accounting & Finance"};
        ObservableList<String> allPrograms = FXCollections.observableArrayList(array);
        selectProgramComboBox.setItems(allPrograms);

//        gender.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//            @Override
//            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
//                RadioButton rb = (RadioButton)gender.getSelectedToggle();
//                if(rb!=null){
//                    String s = rb.getText();
//                    System.out.println(s);
//                }
//            }
//        });

        ;

        createStdAccountBtn.setOnAction(event -> {
            String stdFirstName = stdFirstNameField.getText();
            String stdLastName = stdLastNameField.getText();
            String stdFatherName = stdFatherNameField.getText();
            String stdDateOfBirth = dateOfBirth.getValue().toString();
            System.out.println(stdDateOfBirth);
            String stdAddress = addressField.getText();
            String stdEmail = stdEmaildField.getText();
            String phoneNo = phoneNoField.getText();
            String password = passwordField.getText();
            String getProgram = "";
            try {
                getProgram = selectProgramComboBox.getSelectionModel().getSelectedItem().toString();
            }catch (Exception e){
                System.out.println("Please select a program");
            }
            String stdGender = "";
            if(gender.getSelectedToggle()!=null){
                String s = ((RadioButton) gender.getSelectedToggle()).getText();
                if(s.equals("Male")){
                    stdGender = "Male";
                } else {
                    stdGender = "Female";
                }
            }else {
                System.out.println("Please select gender");
            }



//Database
            Database_Handler database_handler = new Database_Handler();
            try {
                PreparedStatement preparedStatement = database_handler.get_Connection().prepareStatement("INSERT INTO " +
                        "student (first_name,last_name,father_name,date_of_birth,gender,address,phone_no,email,program,password) " +
                        "values (?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1,stdFirstName);
                preparedStatement.setString(2, stdLastName);
                preparedStatement.setString(3, stdFatherName);
                preparedStatement.setString(4, stdDateOfBirth);
                preparedStatement.setString(5, stdGender);
                preparedStatement.setString(6, stdAddress);
                preparedStatement.setString(7, phoneNo);
                preparedStatement.setString(8, stdEmail);
                preparedStatement.setString(9, getProgram);
                preparedStatement.setString(10, password);
                preparedStatement.executeUpdate();
                System.out.println("Data added");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
    }
}
