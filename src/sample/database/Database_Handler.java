package sample.database;

import java.sql.*;

public class Database_Handler {
    Connection dbConnection;

    public Connection get_Connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mldb","root","zafar4321");
        return dbConnection;
    }

    public boolean validate(String email, String password) throws SQLException, ClassNotFoundException {
        Database_Handler database_handler = new Database_Handler();
        Connection connection = database_handler.get_Connection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student where email = ? and password = ?");
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return true;
        }
        return false;
    }

}
