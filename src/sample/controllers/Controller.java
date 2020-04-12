package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.db.DatabaseHandler;
import sample.model.User;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_fild;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authSinginButton;

    @FXML
    private Button loginSingUpButton;

    @FXML
    void initialize() {
        authSinginButton.setOnAction(actionEvent -> {
            var loginText = login_fild.getText().trim();
            var loginPassword = password_field.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else System.out.println("Login and password is empty");

        });

        loginSingUpButton.setOnAction(actionEvent -> {
            openNewScene("/sample/view/singUp.fxml");
        });

    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

         int counter = 0;
         try{
         while (result.next()) {
             counter++;
         }
         }catch (SQLException e ) {
             e.printStackTrace();
         }
             if (counter >= 1){
                 openNewScene("/sample/view/app.fxml");

             }else {
                 Shake userLoginAnim = new Shake(login_fild);       //animations
                 Shake userPassAnim = new Shake(password_field);
                 userLoginAnim.playAnim();
                 userPassAnim.playAnim();
             }
         }
         //open new window
         public void openNewScene(String window){

                 loginSingUpButton.getScene().getWindow().hide();
                 FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(window));
                 try {
                     loader.load();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 Parent root = loader.getRoot();
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root));
                 stage.showAndWait();

         }

    }

