package com.example.realestatemanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class homepageController implements Initializable {

    public VBox forSaleLayer;
    public VBox forRentLayer;
    public VBox addPropertyLayer;
    public VBox newPropertyLayer;
    public VBox findAgentLayer;
    public VBox servicesLayer;
    public AnchorPane sideMenu;

    public VBox apartmentsSale;
    public VBox residentialSale;
    public VBox apartmentsRent;
    public VBox residentialRent;
    public VBox businessRent;
    public VBox newApartmentSale;
    public VBox newResidentialSale;
    public VBox reportLayer;

    public Button joinButton;
    public HBox userAccountContainer;
    public Label userAccountContainerLabel;
    public ImageView userAccountProfilePicture;

    private Stage stage;
    private Scene scene;

    public void activeTransitionOnY(Node c, double move){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(c);
        transition.setToY(move);
        transition.play();
    }

    public void activeTransitionOnX(Node c, double move){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(c);
        transition.setToX(move);
        transition.play();
    }

    public void onBuyActive(MouseEvent mouseEvent) {
        activeTransitionOnY(forSaleLayer, 0);
    }



    public void onBuyDeactive(MouseEvent mouseEvent) {
        // setting submenu deactive
        subMenuDeactive(apartmentsSale);
        subMenuDeactive(residentialSale);

        activeTransitionOnY(forSaleLayer, -105);
    }

    public void onRentActive(MouseEvent mouseEvent) {
        activeTransitionOnY(forRentLayer, 0);
    }

    public void onRentDeactive(MouseEvent mouseEvent) {
        // setting submenu deactive
        subMenuDeactive(apartmentsRent);
        subMenuDeactive(residentialRent);
        subMenuDeactive(businessRent);

        activeTransitionOnY(forRentLayer, -140);
    }

    public void onAddActive(MouseEvent mouseEvent) {
        activeTransitionOnY(addPropertyLayer, 0);
    }

    public void onAddDeactive(MouseEvent mouseEvent) {
        activeTransitionOnY(addPropertyLayer, -70);
    }

    public void onNewActive(MouseEvent mouseEvent) {
        activeTransitionOnY(newPropertyLayer, 0);
    }

    public void onNewDeactive(MouseEvent mouseEvent) {
        // setting submenu deactive
        subMenuDeactive(newApartmentSale);
        subMenuDeactive(newResidentialSale);

        activeTransitionOnY(newPropertyLayer, -105);
    }

    public void onFindAgentActive(MouseEvent mouseEvent) {
        activeTransitionOnY(findAgentLayer, 0);
    }

    public void onFindAgentDeactive(MouseEvent mouseEvent) {
        activeTransitionOnY(findAgentLayer, -105);
    }

    public void onServiceActive(MouseEvent mouseEvent) {
        activeTransitionOnY(servicesLayer, 0);
    }

    public void onServiceDeactive(MouseEvent mouseEvent) {
        // setting submenu deactive
        subMenuDeactive(reportLayer);

        activeTransitionOnY(servicesLayer, -70);
    }

    public void onBuyClicked(MouseEvent mouseEvent) {
        if(forSaleLayer.getTranslateY() == -105){
            onBuyActive(mouseEvent);

            // setting others deactive
            onRentDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(forSaleLayer.getTranslateY() == 0){
            onBuyDeactive(mouseEvent);
        }
    }

    public void onRentClicked(MouseEvent mouseEvent) {
        if(forRentLayer.getTranslateY() == -140){
            onRentActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(forRentLayer.getTranslateY() == 0){
            onRentDeactive(mouseEvent);
        }
    }

    public void onAddClicked(MouseEvent mouseEvent) {
        if(addPropertyLayer.getTranslateY() == -70){
            onAddActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onRentDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(addPropertyLayer.getTranslateY() == 0){
            onAddDeactive(mouseEvent);
        }
    }

    public void onNewClicked(MouseEvent mouseEvent) {
        if(newPropertyLayer.getTranslateY() == -105){
            onNewActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onRentDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(newPropertyLayer.getTranslateY() == 0){
            onNewDeactive(mouseEvent);
        }
    }

    public void onFindAgentClicked(MouseEvent mouseEvent) {
        if(findAgentLayer.getTranslateY() == -105){
            onFindAgentActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onRentDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(findAgentLayer.getTranslateY() == 0){
            onFindAgentDeactive(mouseEvent);
        }
    }

    public void onServiceClicked(MouseEvent mouseEvent) {
        if(servicesLayer.getTranslateY() == -70){
            onServiceActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onRentDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
        }
        else if(servicesLayer.getTranslateY() == 0){
            onServiceDeactive(mouseEvent);
        }
    }

    public void onJoinClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signInPage.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
    }

    public void onMenuButtonClicked(ActionEvent event) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(sideMenu);
        transition.setToX(0);
        transition.play();
    }

    public void closeSideMenu(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(sideMenu);
        transition.setToX(-183);
        transition.play();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Check if the user is logged in and set visibility of join button accordingly
        if(YourPropertyUserSession.getInstance().isLoggedIn()) {
            joinButton.setVisible(false);
            userAccountContainer.setVisible(true);

            // Fetch user email
            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            // Get user info from database
            String targetUrl = "http://localhost:8080/api/yourpropertyuser";
            try {
                JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);
                String firstName = jsonUser.getString("FirstName");
                String lastName = jsonUser.getString("LastName");
                String profilePicture = jsonUser.getString("ProfilePicture");


                userAccountContainerLabel.setText(firstName + " " + lastName);
                if(profilePicture != null) userAccountProfilePicture.setImage(new Image(profilePicture));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            joinButton.setVisible(true);
            userAccountContainer.setVisible(false);
        }
    }

    public void saleActive(Node c){
        c.setVisible(true);
        activeTransitionOnX(c, 163);
    }

    public void rentActive(Node c){
        c.setVisible(true);
        activeTransitionOnX(c, 159);
    }

    public void serviceActive(Node c){
        c.setVisible(true);
        activeTransitionOnX(c, 100);
    }

    public void subMenuDeactive(Node c){
        activeTransitionOnX(c, 0);
        c.setVisible(false);
    }

    public void onApartmentsSaleClicked(MouseEvent mouseEvent) {
        if(apartmentsSale.getTranslateX() == 0){
            saleActive(apartmentsSale);
            subMenuDeactive(residentialSale);
        } else if(apartmentsSale.getTranslateX() == 163){
            subMenuDeactive(apartmentsSale);
        }
    }

    public void onResidentialSaleClicked(MouseEvent mouseEvent) {
        if(residentialSale.getTranslateX() == 0){
            saleActive(residentialSale);
            subMenuDeactive(apartmentsSale);
        } else if(residentialSale.getTranslateX() == 163){
            subMenuDeactive(residentialSale);
        }
    }

    public void onCommercialSaleClicked(MouseEvent mouseEvent) {
        subMenuDeactive(apartmentsSale);
        subMenuDeactive(residentialSale);
    }


    public void onApartmentsRentClicked(MouseEvent mouseEvent) {
        if(apartmentsRent.getTranslateX() == 0) {
            rentActive(apartmentsRent);
            subMenuDeactive(residentialRent);
            subMenuDeactive(businessRent);
        } else if(apartmentsRent.getTranslateX() == 159){
            subMenuDeactive(apartmentsRent);
        }
    }

    public void onResidentialRentClicked(MouseEvent mouseEvent) {
        if(residentialRent.getTranslateX() == 0) {
            rentActive(residentialRent);
            subMenuDeactive(apartmentsRent);
            subMenuDeactive(businessRent);
        } else if(residentialRent.getTranslateX() == 159){
            subMenuDeactive(residentialRent);
        }
    }

    public void onCommercialRentClicked(MouseEvent mouseEvent) {
        subMenuDeactive(apartmentsRent);
        subMenuDeactive(residentialRent);
        subMenuDeactive(businessRent);
    }

    public void onBusinessRentClicked(MouseEvent mouseEvent) {
        if(businessRent.getTranslateX() == 0) {
            rentActive(businessRent);
            subMenuDeactive(residentialRent);
            subMenuDeactive(apartmentsRent);
        } else if(businessRent.getTranslateX() == 159){
            subMenuDeactive(businessRent);
        }
    }


    public void newApartmentSaleClicked(MouseEvent mouseEvent) {
        if(newApartmentSale.getTranslateX() == 0){
            saleActive(newApartmentSale);
            subMenuDeactive(newResidentialSale);
        } else if(newApartmentSale.getTranslateX() == 163){
            subMenuDeactive(newApartmentSale);
        }
    }

    public void newResidentialSaleClicked(MouseEvent mouseEvent) {
        if(newResidentialSale.getTranslateX() == 0){
            saleActive(newResidentialSale);
            subMenuDeactive(newApartmentSale);
        } else if(newResidentialSale.getTranslateX() == 163){
            subMenuDeactive(newResidentialSale);
        }
    }

    public void newCommercialSaleClicked(MouseEvent mouseEvent) {
        subMenuDeactive(newApartmentSale);
        subMenuDeactive(newResidentialSale);
    }


    public void onContactUsClicked(MouseEvent mouseEvent) {
        subMenuDeactive(reportLayer);
    }

    public void onReportClicked(MouseEvent mouseEvent) {
        if(reportLayer.getTranslateX() == 0) {
            serviceActive(reportLayer);
        } else if(reportLayer.getTranslateX() == 100){
            subMenuDeactive(reportLayer);
        }
    }


    public void userAccountBoxClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileDashboardPage.fxml")));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Profile Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
