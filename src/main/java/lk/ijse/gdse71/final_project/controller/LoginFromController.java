package lk.ijse.gdse71.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.gdse71.final_project.Dao.custom.AdminDao;
import lk.ijse.gdse71.final_project.bo.custom.AdminBO;
import lk.ijse.gdse71.final_project.bo.custom.BOFactory;
import lk.ijse.gdse71.final_project.bo.custom.impl.AdminBOImpl;
import lk.ijse.gdse71.final_project.dto.AdminDto;
import lk.ijse.gdse71.final_project.Dao.custom.impl.AdminDaoImpl;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFromController  {

    @FXML
    private AnchorPane ancMain;

    @FXML
    private AnchorPane ancSide;

    @FXML
    private Button btuLogin;

    @FXML
    private Button btuSignIn;

    @FXML
    private Label lblCourse;

    @FXML
    private Label lblHaveNot;

    @FXML
    private Label lblRegistration;

    @FXML
    private Label lblSystem;

    @FXML
    private Label lblUniversity;

    @FXML
    private Label lblWelcome;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Pane paneLogin;

    private AdminDto adminDto;

    AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBo(BOFactory.BOType.ADMIN);
    @FXML
    void loginOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        if (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()){
            showAlert("Text feild are not fill..!","Fill all text fields..!");
            return;
        }
        try {
            //methana adminBo eka use karal thiyenawa...
            adminDto = adminBO.checkAdmin(userName);


            if (adminDto != null && adminDto.getPassword().equals(password)) {
                Stage stage = (Stage) ancMain.getScene().getWindow();
                stage.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoardView.fxml"));
                Parent dashboardRoot = loader.load();
                DashBoardController dashBoardController = loader.getController();
                dashBoardController.initialize(adminDto);

                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/ReportsGenerateView.fxml"));
                Parent root = loader1.load();
                GenerateReportsViewController reportsViewController = loader1.getController();
                reportsViewController.getAdmin(adminDto);

                Stage dashboardStage = new Stage();
                Image image = new Image(getClass().getResourceAsStream("/image/Screenshot 2024-11-12 232120.png"));
                dashboardStage.getIcons().add(image);
                dashboardStage.setScene(new Scene(dashboardRoot));
                dashboardStage.setResizable(false);
                dashboardStage.show();
            } else {
                showAlert("Invalid User Name or Password", "Please enter a valid User name and password..! ");
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {
        ancMain.getChildren().clear();
        AnchorPane signInPane = FXMLLoader.load(getClass().getResource("/view/SignInFromView.fxml"));
        ancMain.getChildren().add(signInPane);
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        String userName = txtUserName.getText();
        try {
            AdminDto adminDto = adminBO.checkAdmin(userName);
            if (adminDto != null) {
                txtUserName.setStyle(";-fx-border-color: null;");
                txtPassword.requestFocus();
            } else {
                txtUserName.setStyle(";-fx-border-color: red;");
            }
        } catch (Exception e) {
            System.out.println("Error checking username: " + e.getMessage());
        }
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        loginOnAction(actionEvent);
    }
    private void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
