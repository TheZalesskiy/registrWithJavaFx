package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.db.DatabaseHandler;
import sample.model.User;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label id_text_hello;

    @FXML
    private ImageView imageButtonHome;

    @FXML
    private Button button_exit;


    @FXML
    private TextField name_text;



    @FXML
    void initialize() {
        var nameText = name_text.getText();
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();

        ResultSet resultSet = dbHandler.getUser(user);



        button_exit.setOnAction(actionEvent ->
                openNewScene("/sample/view/sample.fxml"));
    }

    public void openNewScene(String window) {

        button_exit.getScene().getWindow().hide();
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
