package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.database.Database_Handler;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StdMarksController implements Initializable {
    @FXML
    private TextField eteaMarksField;

    @FXML
    private TextField interViewMarksField;

    @FXML
    private TextField fscMarksField;

    @FXML
    private TextField matricMarksField;

    @FXML
    private Button enterMarksField;

    @FXML
    private Text avgMarks;

    @FXML
    private TextField getStdIDField;

    public int calculateAverage(){
        int eteaMarks = Integer.parseInt(eteaMarksField.getText());
        int interViewMarks = Integer.parseInt(interViewMarksField.getText());
        int fscMarks = Integer.parseInt(fscMarksField.getText());
        int matricMarks = Integer.parseInt(matricMarksField.getText());
        System.out.println(eteaMarks+interViewMarks+fscMarks+matricMarks);
        int total = eteaMarks+interViewMarks+fscMarks+matricMarks;
        return total;
    }
    @FXML
    public void enterMarksField(ActionEvent event) throws SQLException, ClassNotFoundException {
        int idNumber = Integer.parseInt(getStdIDField.getText());
        Database_Handler database_handler = new Database_Handler();
        Connection connection = database_handler.get_Connection();
        PreparedStatement preparedStatement = connection.prepareStatement("update student set marks = " + calculateAverage() +
        " where id = " + idNumber);
        preparedStatement.executeUpdate();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
