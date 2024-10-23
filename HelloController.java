package com.example.demoproject1;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class HelloController implements Initializable {

    // Create Account pages
    public AnchorPane createAccount;
    public AnchorPane welcomeLayer;
    public AnchorPane verifyAccountCA;
    public AnchorPane psDetails1;
    public AnchorPane psDetails2;

    // Sign in pages
    public AnchorPane signIn;
    public AnchorPane helloLayer;
    public AnchorPane forgotPassword;
    public AnchorPane verifyAccount;
    public AnchorPane createPassword;
    public AnchorPane createSecurityQuestion;

    // Parameters
    public ComboBox<String> securityQuestionBox;

    // Stage and scene to show
    private Stage stage;
    private Scene scene;



    public void onSignInClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
    }

    public void onSignUpClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view2.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }

    public void addItemsInSecurityQuestionBox(){
        securityQuestionBox.getItems().addAll(
                "What's your favorite food?",
                "What's your favorite movie?",
                "What's your favorite place",
                "What's the name of the street your grew in?",
                "What's your favorite animal?",
                "What's your pet name?",
                "What's your bestfriend's name?",
                "What do you hate the most?"
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(0.7));
        transition1.setNode(helloLayer);
        transition1.setToX(434.0);
        transition1.play();

        TranslateTransition transition2 = new TranslateTransition();
        transition2.setDuration(Duration.seconds(0.7));
        transition2.setNode(welcomeLayer);
        transition2.setToX(-434.0);
        transition2.play();

        addItemsInSecurityQuestionBox();
    }

    public void onsignUpInCAClicked(ActionEvent actionEvent) {
        createAccount.setVisible(false);
        verifyAccountCA.setVisible(true);
    }

    public void onVerifyCAClicked(ActionEvent actionEvent) {
        verifyAccountCA.setVisible(false);
        welcomeLayer.setVisible(false);
        psDetails1.setVisible(true);
    }

    public void onForgetPassword(ActionEvent actionEvent) {
        signIn.setVisible(false);
        forgotPassword.setVisible(true);
    }

    public void onFirstNextClicked(ActionEvent actionEvent) {
        psDetails1.setVisible(false);
        psDetails2.setVisible(true);
    }

    public void onSecondNextClicked(ActionEvent actionEvent) {

    }

    public void onVerifyClicked(ActionEvent actionEvent) {
        verifyAccount.setVisible(false);
        helloLayer.setVisible(false);
        createPassword.setVisible(true);
    }

    public void onForgetSecurity(ActionEvent actionEvent) {
        forgotPassword.setVisible(false);
        verifyAccount.setVisible(true);
    }

    public void onContinueClicked(ActionEvent actionEvent) {
        createPassword.setVisible(false);
        createSecurityQuestion.setVisible(true);
    }

    public void onSecurityContinueClicked(ActionEvent actionEvent) {

    }

    // This function will be used for a user to add his own custom security question
    // This will be individually work for each user and store their data
    // This must be modified after adding the database
    public void addCustomSecurityQuestion(ActionEvent actionEvent) {
//        if(securityQuestionBox.getItems().contains(securityQuestionBox.getValue())){
//            return;
//        }
//        else{
//            securityQuestionBox.getItems().add(securityQuestionBox.getValue());
//        }
    }

    // We will need to add another function to get security question for the user in the signIn segment
    // It should be modified further
}