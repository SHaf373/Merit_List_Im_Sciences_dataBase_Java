package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.database.Database_Handler;
import sample.objects.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminLoggedInPageController implements Initializable {

    @FXML
    private TableView<?> stdListTable;

    @FXML
    private TableColumn<Student, Integer> idCol;

    @FXML
    private TableColumn<Student, String> nameCol;

    @FXML
    private TableColumn<Student, String> fatherNameCol;

    @FXML
    private TableColumn<Student, String> dateOfBirthCol;

    @FXML
    private TableColumn<Student, String> genderCol;

    @FXML
    private TableColumn<Student, String> cityCol;

    @FXML
    private TableColumn<Student, String> phoneNoCol;

    @FXML
    private TableColumn<Student, String> emailCol;
    @FXML
    private TableColumn<Student, String> programCol;

    public void getData(){
//
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fatherNameCol.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        programCol.setCellValueFactory(new PropertyValueFactory<>("program"));
        stdListTable.setItems(getDataFromMySQL());
    }
    public ObservableList getDataFromMySQL(){
        ObservableList observableList = FXCollections.observableArrayList();

        try{
            Database_Handler database_handler = new Database_Handler();
            Connection connection = database_handler.get_Connection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               // System.out.println(resultSet.getString(2));

                observableList.add(new Student(Integer.parseInt(resultSet.getString(1)),
                        (resultSet.getString(2)+ " " +resultSet.getString(3)),
                        resultSet.getString(4),resultSet.getDate(5),resultSet.getString(6),resultSet.getString(7)
                ,resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
            }
            System.out.println(observableList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return observableList;
    }

    public void updateData(){

    }
    public void enterMark(){

    }

    @FXML
    public void enterStdMarksBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/stdMarks.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();

    }

}
