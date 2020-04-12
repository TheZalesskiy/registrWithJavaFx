package sample.db;
import sample.config.Configs;
import sample.config.Const;
import sample.model.User;

import java.sql.*;


public class DatabaseHandler extends Configs {
    Connection dbConection;

// db conection
    public Connection getDbConection()
            throws ClassNotFoundException, SQLException {
        var connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConection;

    }
    //write db
    public void singUpUser(User user){
        var insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + "," +
                Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_LOCATION + "," + Const.USERS_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";
    //add
        try {
        PreparedStatement statement = getDbConection().prepareStatement(insert);
        statement.setString(1,user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getUserName());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getLocation());
        statement.setString(6, user.getGender());

        statement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    //read db
    public ResultSet getUser(User user){
        ResultSet restSet = null;
//        var select = "SELECT * FROM " + Const.USER_TABLE+ "WHERE" +
//                Const.USERS_USERNAME+"= ? AND " + Const.USERS_PASSWORD+"=?";
        var select = "select * from users where username =? AND password =?";
        //select
        try {
            PreparedStatement statement = getDbConection().prepareStatement(select);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());

           restSet = statement.executeQuery();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restSet;
    }
}

