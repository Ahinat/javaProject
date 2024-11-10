package com.example.realestatemanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    // different panes
    public AnchorPane dashboardLayer;
    public ScrollPane profileSettingsLayer;
    public AnchorPane manageProfile;
    public AnchorPane manageBuyerProfile;
    public AnchorPane manageRenterProfile;
    public AnchorPane manageSellerProfile;

    // profile details
    public ImageView profilePictureInProfileDetails;
    public Label userNameInProfileDetails;
    public Label userEmailInProfileDetails;
    public Label firstName;
    public Label lastName;
    public Label birthday;
    public Label gender;
    public Label password;
    public Label mobileNumber;
    public Label securityQuestion;
    public Label securityAnswer;
    public Label bio;

    // update details options
    public RadioButton newMaleSelected;
    public RadioButton newFemaleSelected;
    public RadioButton newOtherSelected;
    public TextField newFirstName;
    public TextField newLastName;
    public TextField newEmail;
    public DatePicker newBirthday;
    public TextField newPassword;
    public TextField reEnteredNewPassword;
    public ComboBox<String> newCountryCode;
    public TextField newMobileNumber;
    public ComboBox<String> newSecurityQuestionBox;
    public TextField newSecurityAnswer;
    public TextArea newBio;
    public TextField newFacebookLink;
    public TextField newGoogleLink;
    public TextField newTwitterLink;
    public File selectedProfilePicture;
    public Button selectImageButton;
    public Label chosenFilePath;

    // Dashboard display
    public ImageView profilePicture;
    public Label userName;
    public Label userEmail;

    // Create buyer profile
    public AnchorPane createBuyerProfileFields;
    public TextField buyerMinimumBudget;
    public TextField buyerMaximumBudget;
    public CheckBox buyerProfileApartmentChecked, buyerProfileHousesChecked, buyerProfileLandsChecked, buyerProfileResidentialUnitsChecked, buyerProfileCommercialUnitsChecked, buyerProfileBusinessPlacesChecked;
    public FlowPane buyerPreferredLocationFlowPane;
    public TextField buyerPreferredLocationField;

    // Create renter profile
    public ScrollPane createRenterProfileFields;
    public TextField renterMinimumBudget, renterMaximumBudget;
    public CheckBox renterProfileApartmentChecked, renterProfileHousesChecked, renterProfileLandsChecked, renterProfileResidentialUnitsChecked, renterProfileCommercialUnitsChecked, renterProfileBusinessPlacesChecked;
    public Pane preferredLocationAndCreateButtonPane;
    public TextField renterPreferredLocationField;
    public FlowPane renterPreferredLocationFlowPane;
    public DatePicker renterPreferredMoveInDate;
    public ComboBox<String> preferredRentalTermComboBox;
    public RadioButton PetPolicyYes, PetPolicyNo;
    public RadioButton furnishedPolicyYes, furnishedPolicyNo;
    public RadioButton terraceAvailabilityYes, terraceAvailabilityNo;
    public RadioButton parkingAvailabilityYes, parkingAvailabilityNo;
    public RadioButton basementPreferenceYes, basementPreferrenceNo;
    public CheckBox SecuritySystem, Cameras, GatedEntry;
    public RadioButton garagePreferenceYes, garagePreferrenceNo;
    public RadioButton flatLand, hillyLand, slopedLand;
    public CheckBox electricityUtilityLand, waterUtilityLand, sewageUtilityLand;
    public RadioButton pavedRoadAccessLand, unpavedRoadAccessLand;
    public VBox showPreferredFeaturesVbox;
    public Pane apartmentFeatures, houseFeatures, landFeatures, residentialUnitFeatures, commercialUnitFeatures, businessPlacesFeatures;
    public CheckBox poolAmenitiyResidentialUnit, gymAmenityResidentialUnit, playgroundAmenityResidentialUnit;
    public RadioButton closetStorageResidentialUnit, basementStorageResidentialUnit, builtInStorageResidentialUnit;
    public RadioButton noiseInsulatedResidentialUnit, noiseExposeResidentialUnit;
    public CheckBox retailPurposeCommercialUnit, officePurposeCommercialUnit, medicalPurposeCommercialUnit, restaurantPurposeCommercialUnit;
    public RadioButton openPlanFloorLayoutCommercialUnit, modularFloorLayoutCommercialUnit, separateFloorLayoutCommercialUnit;
    public CheckBox elevatorCommercialUnit, brailleSignageCommercialUnit, accessibleWashroomCommercialUnit;
    public CheckBox storePurposeBusinessPlace, garagePurposeBusinessPlace;
    public RadioButton standardVentilationBusinessPlace, highPoweredFansVentilationBusinessPlace, airFiltrationVentilationBusinessPlace;
    public CheckBox standardPowerSupplyBusinessPlace, highVoltagePowerBusinessPlace, generatorPowerSupplyBusinessPlace;

    // Create seller profile
    public AnchorPane createSellerProfileFields;
    public TextField sellerLicenseNumber;
    public CheckBox sellerProfileApartmentChecked, sellerProfileHousesChecked, sellerProfileLandsChecked, sellerProfileResidentialUnitsChecked, sellerProfileCommercialUnitsChecked, sellerProfileBusinessPlacesChecked;
    public CheckBox sellerOpenCommunicationWithBuyer, sellerOpenCommunicationWithRenter, sellerOpenCommunicationWithAgent;


    private List<String> buyerPreferredLocations = new ArrayList<>();
    private List<String> renterPreferredLocations = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void onLogoClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Your Property");
        stage.setScene(scene);
        stage.show();
    }

    public void onLogOutClicked(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        ButtonType userConfirmation = alert.showAndWait().get();

        if(ButtonType.OK.equals(userConfirmation)) {
            YourPropertyUserSession.getInstance().logoutUser();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Your Property");
            stage.setScene(scene);
            stage.show();
        } else {
            alert.close();
        }
    }

    public void onProfileSettingsClicked(ActionEvent actionEvent) {
        dashboardLayer.setVisible(false);
        manageProfile.setVisible(false);
        profileSettingsLayer.setVisible(true);

        refreshUserProfileDetails();
        addItemsInSecurityQuestionBox();
        addItemsInCountryCodeMobileNumber();
    }

    public void refreshUserProfileDetails(){
        // Fetch user email
        String user_Email = YourPropertyUserSession.getInstance().getUserEmail();

        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, user_Email);

            String firstName1 = jsonUser.optString("FirstName", "");
            String lastName1 = jsonUser.optString("LastName", "");
            String birthday1 = jsonUser.optString("Birthday", "");
            String gender1 = jsonUser.optString("Gender", "");
            String password1 = jsonUser.optString("Password", "");
            String mobileNumber1 = jsonUser.optString("MobileNumber", "");
            String securityQuestion1 = jsonUser.optString("SecurityQuestion", "");
            String securityAnswer1 = jsonUser.optString("SecurityAnswer", "");
            String bio1 = jsonUser.optString("Bio","");
            String profilePicture1 = jsonUser.optString("ProfilePicture", "");

            userNameInProfileDetails.setText(firstName1 + " " + lastName1);
            userEmailInProfileDetails.setText(user_Email);
            firstName.setText(firstName1);
            lastName.setText(lastName1);
            birthday.setText(birthday1);
            gender.setText(gender1);
            password.setText(password1.replaceAll(".", "*"));
            mobileNumber.setText(mobileNumber1);
            securityQuestion.setText(securityQuestion1);
            securityAnswer.setText(securityAnswer1);
            bio.setText(bio1);
            if(profilePicture1 != null) profilePictureInProfileDetails.setImage(new Image(profilePicture1));

            userName.setText(firstName1 + " " + lastName1);
            userEmail.setText(user_Email);
            if(profilePicture1 != null) profilePicture.setImage(new Image(profilePicture1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPasswordInProfileSettingsClicked(MouseEvent mouseEvent) {
        password.setText(YourPropertyUserSession.getInstance().getUserPassword());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardLayer.setVisible(true);
        profileSettingsLayer.setVisible(false);
        manageProfile.setVisible(false);

        // Fetch user email
        String user_Email = YourPropertyUserSession.getInstance().getUserEmail();

        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, user_Email);

            String firstName1 = jsonUser.optString("FirstName", "");
            String lastName1 = jsonUser.optString("LastName", "");
            String profilePicture1 = jsonUser.optString("ProfilePicture", "");

            userName.setText(firstName1 + " " + lastName1);
            userEmail.setText(user_Email);
            if(profilePicture1 != null) profilePicture.setImage(new Image(profilePicture1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDashboardOptionClicked(MouseEvent mouseEvent) {
        dashboardLayer.setVisible(true);
        profileSettingsLayer.setVisible(false);
        manageProfile.setVisible(false);
    }

    public void onNewMaleSelected(ActionEvent actionEvent) {
        newMaleSelected.setSelected(true);
        newFemaleSelected.setSelected(false);
        newOtherSelected.setSelected(false);
    }

    public void onNewFemaleSelected(ActionEvent actionEvent) {
        newMaleSelected.setSelected(false);
        newFemaleSelected.setSelected(true);
        newOtherSelected.setSelected(false);
    }

    public void onNewOtherSelected(ActionEvent actionEvent) {
        newMaleSelected.setSelected(false);
        newFemaleSelected.setSelected(false);
        newOtherSelected.setSelected(true);
    }

    public void addCustomSecurityQuestion(ActionEvent actionEvent) {

    }

    public void addItemsInSecurityQuestionBox(){
        newSecurityQuestionBox.getItems().addAll(
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
        newCountryCode.getItems().addAll(
                "+1",
                "+44",
                "+880",
                "+91",
                "+978"
        );
    }

    public void addItemsInRentalTermChoice(){
        preferredRentalTermComboBox.getItems().addAll(
                "Weekly",
                "Biweekly",
                "Monthly",
                "Yearly"
        );
    }

    public void chooseProfilePicture(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Assuming 'selectImageButton' is in a scene with a Stage
        Stage stage = (Stage) selectImageButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile != null){
            selectedProfilePicture = selectedFile;
            chosenFilePath.setText(selectedFile.getName());
        }
    }

    public void onUpdateDetailsClicked(ActionEvent actionEvent) {
        // Fetch user email
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        // Getting the input values
        String firstName = newFirstName.getText();
        String lastName = newLastName.getText();

        String birthday;
        if(newBirthday.getValue() != null){
            LocalDate birthdayValue = newBirthday.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            birthday = birthdayValue.format(formatter);
        } else birthday = "";

        String gender;
        if(newMaleSelected.isSelected()) gender = "Male";
        else if(newFemaleSelected.isSelected()) gender = "Female";
        else if(newOtherSelected.isSelected()) gender = "Other";
        else gender = "";

        String email = newEmail.getText();
        String password = newPassword.getText();
        String mobileNumber;
        if(newCountryCode.getSelectionModel().getSelectedItem() != null)
            mobileNumber = newCountryCode.getSelectionModel().getSelectedItem() + newMobileNumber.getText();
        else mobileNumber = "";
        String securityQuestion = newSecurityQuestionBox.getValue();
        String securityAnswer = newSecurityAnswer.getText();

        String bio = newBio.getText();
        String facebookLink = newFacebookLink.getText();
        String googleLink = newGoogleLink.getText();
        String twitterLink = newTwitterLink.getText();

        String profilePicture;
        if(selectedProfilePicture != null){
            profilePicture = selectedProfilePicture.getAbsolutePath();
        } else profilePicture = "";

        // Changing user session email
        if(!email.isEmpty()) YourPropertyUserSession.getInstance().setUserEmail(email);

        // Create json object and generate jsonInputString
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("FirstName", firstName);
        jsonObject.put("LastName", lastName);
        jsonObject.put("Birthday", birthday);
        jsonObject.put("Gender", gender);
        jsonObject.put("Email", email);
        jsonObject.put("Password", password);
        jsonObject.put("MobileNumber", mobileNumber);
        jsonObject.put("SecurityQuestion", securityQuestion);
        jsonObject.put("SecurityAnswer", securityAnswer);
        jsonObject.put("Bio", bio);
        jsonObject.put("Facebook", facebookLink);
        jsonObject.put("Google", googleLink);
        jsonObject.put("Twitter", twitterLink);
        jsonObject.put("ProfilePicture", profilePicture);

        String jsonInputString = jsonObject.toString();

        String targetUrl = "http://localhost:8080/api/yourpropertyuser";

        try{
            Pair<Integer, String> pair = HTTPClient.sendPutRequestForEditProfileDetails(targetUrl, jsonInputString, userEmail);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Profile Details");
            alert.setHeaderText(null);
            alert.setContentText(pair.getValue());
            alert.showAndWait();

            refreshUserProfileDetails();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onDeleteAccountClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete your account");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete your account?");

        ButtonType userConfirmation = alert.showAndWait().get();

        if(ButtonType.OK.equals(userConfirmation)) {
            // Fetch user email
            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            // Get user info from database
            String targetUrl = "http://localhost:8080/api/yourpropertyuser";
            try {
                JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);
                Long userId = jsonUser.getLong("UserId");

                HTTPClient.deleteUserAccount(userId);

                // Update the user login session
                YourPropertyUserSession.getInstance().logoutUser();

                // Redirect to homepage
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Your Property");
                stage.setScene(scene);
                stage.show();
            } catch(Exception e){
                e.printStackTrace();
            }
        } else {
            alert.close();
        }
    }

    public void onManageProfileClicked(ActionEvent actionEvent) {
        profileSettingsLayer.setVisible(false);
        manageProfile.setVisible(true);

        manageBuyerProfile.setVisible(true);
        manageRenterProfile.setVisible(false);
    }

    public void onCreateBuyerProfileClicked(ActionEvent actionEvent) {
        try {
            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            BigDecimal minBudget = new BigDecimal(buyerMinimumBudget.getText());
            BigDecimal maxBudget = new BigDecimal(buyerMaximumBudget.getText());

            // Collect preferred property types
            List<String> preferredPropertyTypes = new ArrayList<>();
            if(buyerProfileApartmentChecked.isSelected()) preferredPropertyTypes.add("Apartment");
            if(buyerProfileHousesChecked.isSelected()) preferredPropertyTypes.add("Houses");
            if(buyerProfileLandsChecked.isSelected()) preferredPropertyTypes.add("Lands");
            if(buyerProfileResidentialUnitsChecked.isSelected()) preferredPropertyTypes.add("Residential Units");
            if(buyerProfileCommercialUnitsChecked.isSelected()) preferredPropertyTypes.add("Commercial Units");
            if(buyerProfileBusinessPlacesChecked.isSelected()) preferredPropertyTypes.add("Business Places");

            // Create JSON Object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", userEmail);
            jsonObject.put("minBudget", minBudget);
            jsonObject.put("maxBudget", maxBudget);
            jsonObject.put("propertyTypes", new JSONArray(preferredPropertyTypes));
            jsonObject.put("locations", new JSONArray(buyerPreferredLocations));

            String jsonInputString = jsonObject.toString();

            String targetUrl = "http://localhost:8080/api/yourpropertybuyer/register";

            try {
                Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
                if(pair.getKey() == 200){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Create your buyer profile");
                    alert.setHeaderText(null);
                    alert.setContentText(pair.getValue());
                    alert.showAndWait();
                } else if(pair.getKey() == 409){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("You already have an active buyer profile!");

                    alert.showAndWait();
                } else if(pair.getKey() == 500){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("An error occurred!");

                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println("An error occurred!");
                e.printStackTrace();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to submit profile: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void onBuyerAddPreferredLocationClicked(ActionEvent actionEvent) {
        String location = buyerPreferredLocationField.getText().trim();

        if (!location.isEmpty() && !isLocationInFlowPane(location, buyerPreferredLocationFlowPane)) {
            buyerPreferredLocations.add(location);
            HBox locationPane = createLocationPane(location, buyerPreferredLocationFlowPane);
            buyerPreferredLocationFlowPane.getChildren().add(locationPane);
            buyerPreferredLocationField.clear();
        }
    }

    // Method to create a location pane with a delete button
    private HBox createLocationPane(String location, FlowPane parentPane) {
        // Label for location name
        Label locationLabel = new Label(location);
        locationLabel.setStyle("-fx-font-size: 14;");
        locationLabel.setStyle("-fx-text-fill:  #7c4683");

        // Cross button to remove the location
        ImageView deleteButton = createDeleteIcon();
        if (deleteButton != null) {
            deleteButton.setOnMouseClicked(e -> parentPane.getChildren().remove(deleteButton.getParent()));
        }

        // HBox to contain the label and delete button
        HBox locationPane = new HBox(locationLabel, deleteButton);
        locationPane.setAlignment(Pos.CENTER_LEFT);
        locationPane.setSpacing(5);
        locationPane.setPadding(new Insets(5));
        locationPane.setStyle("-fx-background-color: #ecdbee; -fx-border-radius: 5; -fx-background-radius: 5;");

        return locationPane;
    }

    // Helper method to load and create the delete icon ImageView
    private ImageView createDeleteIcon() {
        try {
            // Load the image from your file path or resource
            Image deleteImage = new Image(getClass().getResource("/images/cancel.png").toExternalForm());
            ImageView deleteIcon = new ImageView(deleteImage);
            deleteIcon.setFitWidth(16); // Set width of icon
            deleteIcon.setFitHeight(16); // Set height of icon
            deleteIcon.setPreserveRatio(true);
            return deleteIcon;
        } catch (Exception e) {
            System.err.println("Delete icon image not found: " + e.getMessage());
            return null;
        }
    }

    // Helper method to check if a location already exists in the FlowPane
    private boolean isLocationInFlowPane(String location, FlowPane flowPane) {
        return flowPane.getChildren().stream().anyMatch(node -> ((Label) ((HBox) node).getChildren().get(0)).getText().equals(location));
    }

    public void onCancelCreateBuyerClicked(MouseEvent mouseEvent) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createBuyerProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(575);
        translateTransition.play();

        createBuyerProfileFields.setVisible(false);
    }

    public void onCreateNewBuyerProfileClicked(ActionEvent actionEvent) {
        createBuyerProfileFields.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createBuyerProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(0);
        translateTransition.play();
    }

    public void onCreateNewRenterProfileClicked(ActionEvent actionEvent) {
        addItemsInRentalTermChoice();
        createRenterProfileFields.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createRenterProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(0);
        translateTransition.play();
    }

    public void onRenterAddPreferredLocationClicked(ActionEvent actionEvent) {
        String location = renterPreferredLocationField.getText().trim();

        if (!location.isEmpty() && !isLocationInFlowPane(location, renterPreferredLocationFlowPane)) {
            renterPreferredLocations.add(location);
            HBox locationPane = createLocationPane(location, renterPreferredLocationFlowPane);
            renterPreferredLocationFlowPane.getChildren().add(locationPane);
            renterPreferredLocationField.clear();
        }
    }

    public void activeTransitionOnY(Node c, double move){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(c);
        transition.setToY(move);
        transition.play();
    }

    public void onCreateRenterProfileClicked(ActionEvent actionEvent) {
        try{
            ObjectNode renterProfileData = objectMapper.createObjectNode();

            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            BigDecimal minBudget = new BigDecimal(renterMinimumBudget.getText());
            BigDecimal maxBudget = new BigDecimal(renterMaximumBudget.getText());

            LocalDate moveInDatevalue = renterPreferredMoveInDate.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String moveInDate = moveInDatevalue.format(formatter);

            String rentalTerm = preferredRentalTermComboBox.getSelectionModel().getSelectedItem();

            // Collect preferred property types
            List<String> preferredPropertyTypes = new ArrayList<>();
            if(renterProfileApartmentChecked.isSelected()) preferredPropertyTypes.add("Apartment");
            if(renterProfileHousesChecked.isSelected()) preferredPropertyTypes.add("Houses");
            if(renterProfileLandsChecked.isSelected()) preferredPropertyTypes.add("Lands");
            if(renterProfileResidentialUnitsChecked.isSelected()) preferredPropertyTypes.add("Residential Units");
            if(renterProfileCommercialUnitsChecked.isSelected()) preferredPropertyTypes.add("Commercial Units");
            if(renterProfileBusinessPlacesChecked.isSelected()) preferredPropertyTypes.add("Business Places");

            ArrayNode preferredPropertyType = objectMapper.createArrayNode();
            preferredPropertyTypes.forEach(preferredPropertyType::add);

            ArrayNode preferredLocations = objectMapper.createArrayNode();
            renterPreferredLocations.forEach(preferredLocations::add);

            renterProfileData.put("email", userEmail);
            renterProfileData.put("minBudget", minBudget);
            renterProfileData.put("maxBudget", maxBudget);
            renterProfileData.put("moveInDate", moveInDate);
            renterProfileData.put("rentalTerm", rentalTerm);
            renterProfileData.put("propertyTypes", preferredPropertyType);
            renterProfileData.put("locations", preferredLocations);

            if(renterProfileApartmentChecked.isSelected()) {
                Boolean petPolicy = null, furnished = null, terrace = null, parking = null;
                if(PetPolicyYes.isSelected()) petPolicy = true;
                else if(PetPolicyNo.isSelected()) petPolicy = false;
                if(furnishedPolicyYes.isSelected()) furnished = true;
                else if(furnishedPolicyNo.isSelected()) furnished = false;
                if(terraceAvailabilityYes.isSelected()) terrace = true;
                else if(terraceAvailabilityNo.isSelected()) terrace = false;
                if(parkingAvailabilityYes.isSelected()) parking = true;
                else if(parkingAvailabilityNo.isSelected()) parking = false;

                ObjectNode apartmentFeatures = objectMapper.createObjectNode();
                apartmentFeatures.put("petPolicy", petPolicy);
                apartmentFeatures.put("furnished", furnished);
                apartmentFeatures.put("parkingAvailability", parking);
                apartmentFeatures.put("terraceAvailability", terrace);

                renterProfileData.put("ApartmentFeatures", apartmentFeatures);
            }

            if(renterProfileHousesChecked.isSelected()) {
                String basementPreferrence = "";
                if(basementPreferenceYes.isSelected()) basementPreferrence = "Finished";
                else if(basementPreferrenceNo.isSelected()) basementPreferrence = "Unfinished";

                List<String> securityFeatures = new ArrayList<>();
                if(SecuritySystem.isSelected()) securityFeatures.add("Security System");
                if(Cameras.isSelected()) securityFeatures.add("Camera");
                if(GatedEntry.isSelected()) securityFeatures.add("Gated Entry");

                ArrayNode securityFeaturesArray = objectMapper.createArrayNode();
                securityFeatures.forEach(securityFeaturesArray::add);

                Boolean garage = null;
                if(garagePreferenceYes.isSelected()) garage = true;
                else if(garagePreferrenceNo.isSelected()) garage = false;

                ObjectNode houseFeatures = objectMapper.createObjectNode();
                houseFeatures.put("basement", basementPreferrence);
                houseFeatures.put("securityFeatures", securityFeaturesArray);
                houseFeatures.put("garage", garage);

                renterProfileData.put("HouseFeatures", houseFeatures);
            }

            if(renterProfileLandsChecked.isSelected()) {
                String topographyPreferrence = "";
                if(flatLand.isSelected()) topographyPreferrence = "Flat";
                else if(hillyLand.isSelected()) topographyPreferrence = "Hilly";
                else if(slopedLand.isSelected()) topographyPreferrence = "Sloped";

                String roadAccessPreferrence = "";
                if(pavedRoadAccessLand.isSelected()) roadAccessPreferrence = "Paved";
                else if(unpavedRoadAccessLand.isSelected()) roadAccessPreferrence = "Unpaved";

                List<String> utilities = new ArrayList<>();
                if(electricityUtilityLand.isSelected()) utilities.add("Electricity");
                if(waterUtilityLand.isSelected()) utilities.add("Water");
                if(sewageUtilityLand.isSelected()) utilities.add("Sewage");

                ArrayNode utilitiesArray = objectMapper.createArrayNode();
                utilities.forEach(utilitiesArray::add);

                ObjectNode landFeatures = objectMapper.createObjectNode();
                landFeatures.put("topography", topographyPreferrence);
                landFeatures.put("utilities", utilitiesArray);
                landFeatures.put("roadAccess", roadAccessPreferrence);

                renterProfileData.put("LandFeatures", landFeatures);
            }

            if(renterProfileResidentialUnitsChecked.isSelected()) {
                List<String> amenities = new ArrayList<>();
                if(poolAmenitiyResidentialUnit.isSelected()) amenities.add("Pool");
                if(gymAmenityResidentialUnit.isSelected()) amenities.add("Gym");
                if(playgroundAmenityResidentialUnit.isSelected()) amenities.add("Kid's Playground");

                ArrayNode amenitiesArray = objectMapper.createArrayNode();
                amenities.forEach(amenitiesArray::add);

                String storageSpace = "";
                if(closetStorageResidentialUnit.isSelected()) storageSpace = "Closet";
                else if(basementStorageResidentialUnit.isSelected()) storageSpace = "Basement";
                else if(builtInStorageResidentialUnit.isSelected()) storageSpace = "Built-In";

                Boolean noiseInsulation = null;
                if(noiseInsulatedResidentialUnit.isSelected()) noiseInsulation = true;
                else if(noiseExposeResidentialUnit.isSelected()) noiseInsulation = false;

                ObjectNode residentialFeatures = objectMapper.createObjectNode();
                residentialFeatures.put("amenities", amenitiesArray);
                residentialFeatures.put("storageSpace", storageSpace);
                residentialFeatures.put("noiseInsulation", noiseInsulation);

                renterProfileData.put("ResidentialUnitFeatures", residentialFeatures);
            }

            if(renterProfileCommercialUnitsChecked.isSelected()) {
                List<String> commercialPurpose = new ArrayList<>();
                if(retailPurposeCommercialUnit.isSelected()) commercialPurpose.add("Retail");
                if(officePurposeCommercialUnit.isSelected()) commercialPurpose.add("Office");
                if(medicalPurposeCommercialUnit.isSelected()) commercialPurpose.add("Medical");
                if(restaurantPurposeCommercialUnit.isSelected()) commercialPurpose.add("Restaurant");

                ArrayNode commercialPurposeArray = objectMapper.createArrayNode();
                commercialPurpose.forEach(commercialPurposeArray::add);

                String floorLayout = "";
                if(openPlanFloorLayoutCommercialUnit.isSelected()) floorLayout = "Open Plan";
                else if(modularFloorLayoutCommercialUnit.isSelected()) floorLayout = "Modular";
                else if(separateFloorLayoutCommercialUnit.isSelected()) floorLayout = "Separate";

                List<String> accessibility = new ArrayList<>();
                if(elevatorCommercialUnit.isSelected()) accessibility.add("Elevator");
                if(brailleSignageCommercialUnit.isSelected()) accessibility.add("Braille Signage");
                if(accessibleWashroomCommercialUnit.isSelected()) accessibility.add("Accessible Washroom");

                ArrayNode accessibilityArray = objectMapper.createArrayNode();
                accessibility.forEach(accessibilityArray::add);

                ObjectNode commercialFeatures = objectMapper.createObjectNode();
                commercialFeatures.put("accessibilities", accessibilityArray);
                commercialFeatures.put("floorLayout", floorLayout);
                commercialFeatures.put("purposes", commercialPurposeArray);

                renterProfileData.put("CommercialUnitFeatures", commercialFeatures);
            }

            if(renterProfileBusinessPlacesChecked.isSelected()) {
                List<String> businessPurpose = new ArrayList<>();
                if(storePurposeBusinessPlace.isSelected()) businessPurpose.add("Store");
                if(garagePurposeBusinessPlace.isSelected()) businessPurpose.add("Garage");

                ArrayNode businessPurposeArray = objectMapper.createArrayNode();
                businessPurpose.forEach(businessPurposeArray::add);

                String ventilation = "";
                if(standardVentilationBusinessPlace.isSelected()) ventilation = "Standard";
                else if(highPoweredFansVentilationBusinessPlace.isSelected()) ventilation = "High Powered Fans";
                else if(airFiltrationVentilationBusinessPlace.isSelected()) ventilation = "Air Filtration";

                List<String> powerSupply = new ArrayList<>();
                if(standardPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Standard");
                if(highVoltagePowerBusinessPlace.isSelected()) powerSupply.add("High Voltage");
                if(generatorPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Backup Generator");

                ArrayNode powerSupplyArray = objectMapper.createArrayNode();
                powerSupply.forEach(powerSupplyArray::add);

                ObjectNode businessFeatures = objectMapper.createObjectNode();
                businessFeatures.put("powerSupplies", powerSupplyArray);
                businessFeatures.put("ventilation", ventilation);
                businessFeatures.put("purposes", businessPurposeArray);

                renterProfileData.put("BusinessPlaceFeatures", businessFeatures);
            }

            String jsonInputString = renterProfileData.toString();

            String targetUrl = "http://localhost:8080/api/yourpropertyrenter/register";

            try {
                Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
                if(pair.getKey() == 200){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Create your renter profile");
                    alert.setHeaderText(null);
                    alert.setContentText(pair.getValue());
                    alert.showAndWait();
                } else if(pair.getKey() == 409){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("You already have an active renter profile!");

                    alert.showAndWait();
                } else if(pair.getKey() == 500){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("An error occurred!");

                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println("An error occurred!");
                e.printStackTrace();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to submit profile: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void onCancelCreateRenterClicked(MouseEvent mouseEvent) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createRenterProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(575);
        translateTransition.play();

        createRenterProfileFields.setVisible(false);
    }

    public void onManageRenterProfileOptionClicked(MouseEvent mouseEvent) {
        manageRenterProfile.setVisible(true);
        manageBuyerProfile.setVisible(false);
        manageSellerProfile.setVisible(false);

        showPreferredFeatures();
    }

    public void showPreferredFeatures() {
        // Clear current pane order in vboxContainer
        showPreferredFeaturesVbox.getChildren().clear();

        // Add panes to vboxContainer based on checkbox state and in desired order
        if(renterProfileApartmentChecked.isSelected()) {
            apartmentFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(apartmentFeatures);
        } else {
            apartmentFeatures.setVisible(false);
        }

        if(renterProfileHousesChecked.isSelected()) {
            houseFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(houseFeatures);
        } else {
            houseFeatures.setVisible(false);
        }

        if(renterProfileLandsChecked.isSelected()) {
            landFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(landFeatures);
        } else {
            landFeatures.setVisible(false);
        }

        if(renterProfileResidentialUnitsChecked.isSelected()) {
            residentialUnitFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(residentialUnitFeatures);
        } else {
            residentialUnitFeatures.setVisible(false);
        }

        if(renterProfileCommercialUnitsChecked.isSelected()) {
            commercialUnitFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(commercialUnitFeatures);
        } else {
            commercialUnitFeatures.setVisible(false);
        }

        if(renterProfileBusinessPlacesChecked.isSelected()) {
            businessPlacesFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(businessPlacesFeatures);
        } else {
            businessPlacesFeatures.setVisible(false);
        }

        // Add the createRenterProfile pane at the end of the vboxContainer
        showPreferredFeaturesVbox.getChildren().add(preferredLocationAndCreateButtonPane);
    }

    public void onManageBuyerProfileOptionClicked(MouseEvent mouseEvent) {
        manageBuyerProfile.setVisible(true);
        manageRenterProfile.setVisible(false);
        manageSellerProfile.setVisible(false);
    }


    public void onCreateNewSellerProfileClicked(ActionEvent actionEvent) {
        createSellerProfileFields.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createSellerProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(0);
        translateTransition.play();
    }

    public void onCancelCreateSellerClicked(MouseEvent mouseEvent) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createSellerProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(575);
        translateTransition.play();

        createSellerProfileFields.setVisible(false);
    }

    public void onCreateSellerProfileClicked(ActionEvent actionEvent) {
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        String sellerLicense = sellerLicenseNumber.getText();

        // Collect owned property types
        List<String> ownedPropertyTypes = new ArrayList<>();
        if(sellerProfileApartmentChecked.isSelected()) ownedPropertyTypes.add("Apartment");
        if(sellerProfileHousesChecked.isSelected()) ownedPropertyTypes.add("Houses");
        if(sellerProfileLandsChecked.isSelected()) ownedPropertyTypes.add("Lands");
        if(sellerProfileResidentialUnitsChecked.isSelected()) ownedPropertyTypes.add("Residential Units");
        if(sellerProfileCommercialUnitsChecked.isSelected()) ownedPropertyTypes.add("Commercial Units");
        if(sellerProfileBusinessPlacesChecked.isSelected()) ownedPropertyTypes.add("Business Places");

        ArrayNode propertyTypesArray = objectMapper.createArrayNode();
        ownedPropertyTypes.forEach(propertyTypesArray::add);

        // Collect communication interests
        List<String> openedCommunication = new ArrayList<>();
        if(sellerOpenCommunicationWithBuyer.isSelected()) openedCommunication.add("Buyer");
        if(sellerOpenCommunicationWithRenter.isSelected()) openedCommunication.add("Renter");
        if(sellerOpenCommunicationWithAgent.isSelected()) openedCommunication.add("Agent");

        ArrayNode openedCommunicationArray = objectMapper.createArrayNode();
        openedCommunication.forEach(openedCommunicationArray::add);

        ObjectNode sellerProfileData = objectMapper.createObjectNode();
        sellerProfileData.put("email", userEmail);
        sellerProfileData.put("sellerLicense", sellerLicense);
        sellerProfileData.put("openCommunication", openedCommunicationArray);
        sellerProfileData.put("propertyTypes", propertyTypesArray);

        String jsonInputString = sellerProfileData.toString();

        String targetUrl = "http://localhost:8080/api/yourpropertyseller/register";

        try {
            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            if(pair.getKey() == 200){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Create your seller profile");
                alert.setHeaderText(null);
                alert.setContentText(pair.getValue());
                alert.showAndWait();
            } else if(pair.getKey() == 409){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Something went wrong!");
                alert.setContentText("You already have an active renter profile!");

                alert.showAndWait();
            } else if(pair.getKey() == 500){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Something went wrong!");
                alert.setContentText("An error occurred!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    public void onManageSellerProfileOptionClicked(MouseEvent mouseEvent) {
        manageBuyerProfile.setVisible(false);
        manageRenterProfile.setVisible(false);
        manageSellerProfile.setVisible(true);
    }
}
