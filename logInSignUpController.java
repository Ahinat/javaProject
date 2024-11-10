package com.example.realestatemanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.util.Pair;
import org.json.JSONObject;

public class logInSignUpController implements Initializable {

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

    // Create Account Parameters
    public TextField createGmail;
    public PasswordField createNewPassword;
    public PasswordField createReEnteredPassword;
    public TextField userOTP;
    public TextField firstName;
    public TextField lastName;
    public DatePicker birthDay;
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public RadioButton genderOther;
    public ComboBox<String> countryCodeMobileNumber;
    public TextField mobileNumber;
    public ComboBox<String> securityQuestionBox;
    public TextField securityAnswer;
    public TextArea userBio;

    // Login Parameters
    public TextField loginGmail;
    public PasswordField loginPassword;

    // Stage and scene to show
    private Stage stage;
    private Scene scene;



    public void onSignInClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signInPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
    }

    public void onSignUpClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createAccountPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();

//        addItemsInCountryCodeMobileNumber();
    }

    public void addItemsInSecurityQuestionBox(){
        securityQuestionBox.getItems().addAll(
                "What's your favorite food?",
                "What's your favorite movie?",
                "What's your favorite place?",
                "What's the name of the street your grew in?",
                "What's your favorite animal?",
                "What's your pet name?",
                "What's your bestfriend's name?",
                "What do you hate the most?"
        );
    }

    public void addItemsInCountryCodeMobileNumber(){
        countryCodeMobileNumber.getItems().addAll(
                "+1",
                "+44",
                "+880",
                "+91",
                "+978"
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
        if(countryCodeMobileNumber != null){
            addItemsInCountryCodeMobileNumber();
        }
    }

    public void onsignUpInCAClicked(ActionEvent actionEvent) {
        // Ensuring that all credentials are filled and meet certain conditions
        if(createGmail.getText().isEmpty()){
            showError("You must enter your email!");
        } else if(createNewPassword.getText().isEmpty()){
            showError("You must enter your password!");
        } else if(createReEnteredPassword.getText().isEmpty()){
            showError("You must re-enter your password!");
        } else if(createNewPassword.getText().equals(createReEnteredPassword.getText())){
            if(createNewPassword.getText().length() < 8){
                showWarning("Password is too short!");
            } else {
                createAccount.setVisible(false);
                verifyAccountCA.setVisible(true);
            }
        } else {
            showError("Your passwords do not match!");
        }
    }

    public void showError(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Something went wrong!");
        alert.setContentText(text);

        alert.showAndWait();
    }

    public void showWarning(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Something went wrong!");
        alert.setContentText(text);

        alert.showAndWait();
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
        if(firstName.getText().isEmpty()){
            showError("Please enter your first name!");
        } else if(lastName.getText().isEmpty()){
            showError("Please enter your last name!");
        } else if(birthDay.getValue() == null){
            showError("Please enter a birthday!");
        } else if(!genderMale.isSelected() && !genderFemale.isSelected() && !genderOther.isSelected()){
            showError("Please select gender!");
        } else if(countryCodeMobileNumber.getValue().isEmpty()){
            showError("Please enter your country code!");
        } else if(mobileNumber.getText().isEmpty()){
            showError("Please enter your mobile number!");
        } else{
            psDetails1.setVisible(false);
            psDetails2.setVisible(true);
        }
    }

    public void onSecondNextClicked(ActionEvent actionEvent) throws IOException {
        if(securityQuestionBox.getValue().isEmpty()){
            showError("Please select/enter your security question!");
        } else if(securityAnswer.getText().isEmpty()){
            showError("Please enter your security answer!");
        } else {
            registerUser(actionEvent);
        }
    }

    public void registerUser(ActionEvent actionEvent) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String password = createNewPassword.getText();

        LocalDate birthDayValue = birthDay.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String Birthday = birthDayValue.format(formatter);

        String gender = "";
        if(genderMale.isSelected()) gender = "Male";
        else if(genderFemale.isSelected()) gender = "Female";
        else if(genderOther.isSelected()) gender = "Other";

        String email = createGmail.getText();
        String MobileNumber = countryCodeMobileNumber.getSelectionModel().getSelectedItem() + mobileNumber.getText();
        String SecurityQuestion = securityQuestionBox.getSelectionModel().getSelectedItem();
        String SecurityAnswer = securityAnswer.getText();
        String Bio = userBio.getText();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("FirstName", firstNameText);
        jsonObject.put("LastName", lastNameText);
        jsonObject.put("Password", password);
        jsonObject.put("Birthday", Birthday);
        jsonObject.put("Gender", gender);
        jsonObject.put("Email", email);
        jsonObject.put("MobileNumber", MobileNumber);
        jsonObject.put("SecurityQuestion", SecurityQuestion);
        jsonObject.put("SecurityAnswer", SecurityAnswer);
        jsonObject.put("Bio", Bio);

        String jsonInputString = jsonObject.toString();

        String targetUrl = "http://localhost:8080/api/yourpropertyuser/register";

        try {
            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            if(pair.getKey() == 201){
                // Changing user session info
                YourPropertyUserSession.getInstance().loginUser(email, password);

                // Shifting scene to user dashboard
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileDashboardPage.fxml")));
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Profile Dashboard");
                stage.setScene(scene);
                stage.show();
            } else if(pair.getKey() == 409){
                showError("You already have an active profile!");
            } else if(pair.getKey() == 500){
                showError("An error occurred!");
            }
        } catch (Exception e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
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

    public void addCustomCountryCode(ActionEvent actionEvent) {

    }

    public void onMaleSelected(ActionEvent actionEvent) {
        genderMale.setSelected(true);
        genderFemale.setSelected(false);
        genderOther.setSelected(false);
    }

    public void onFemaleSelected(ActionEvent actionEvent) {
        genderMale.setSelected(false);
        genderFemale.setSelected(true);
        genderOther.setSelected(false);
    }

    public void onOtherSelected(ActionEvent actionEvent) {
        genderMale.setSelected(false);
        genderFemale.setSelected(false);
        genderOther.setSelected(true);
    }

    public void onLoginSIClicked(ActionEvent actionEvent) {
        if(loginGmail.getText().isEmpty()) {
            showError("Please enter your email!");
        } else if (loginPassword.getText().isEmpty()) {
            showError("Please enter your password!");
        } else {
            loginUser(actionEvent);
        }
    }

    public void loginUser(ActionEvent actionEvent){
        String email = loginGmail.getText();
        String password = loginPassword.getText();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Email", email);
        jsonObject.put("Password", password);

        String jsonInputString = jsonObject.toString();
        String targetUrl = "http://localhost:8080/api/yourpropertyuser/login";

        try {
            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            System.out.println(pair.getValue());

            if(pair.getKey() == 200){
                // Changing user session info
                YourPropertyUserSession.getInstance().loginUser(email, password);

                // Shifting the scene to user dashboard
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileDashboardPage.fxml")));
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Profile Dashboard");
                stage.setScene(scene);
                stage.show();
            } else if(pair.getKey() == 401){
                showError("Invalid email or password!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // We will need to add another function to get security question for the user in the signIn segment
    // It should be modified further
}