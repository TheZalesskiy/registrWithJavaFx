package sample.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sample.db.DatabaseHandler;
import sample.model.User;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField singUp_login;

    @FXML
    private PasswordField singUp_password;

    @FXML
    private Button singUpButton;

    @FXML
    private TextField singUpSurnme;

    @FXML
    private TextField singUpName;

    @FXML
    private TextField singUpCountry;

    @FXML
    private RadioButton singUpMale;

    @FXML
    private RadioButton singUpFemale;

    @FXML
    void initialize() {
        singUpButton.setOnAction(actionEvent -> singUpNewUser());
    }
    private void singUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

             String firstName = singUpName.getText();
             String lastName = singUpSurnme.getText();
             String userName = singUp_login.getText();
             String password = singUp_password.getText();
             String location = singUpCountry.getText();
             String gender;
             if (singUpMale.isSelected())
                 gender = "Male";
             else
                 gender = "Female";

        User user = new User(firstName, lastName, userName, password, location, gender);

            dbHandler.singUpUser(user);
    }
}
