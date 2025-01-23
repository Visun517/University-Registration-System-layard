package lk.ijse.gdse71.final_project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.gdse71.final_project.Dao.CrudDAO;
import lk.ijse.gdse71.final_project.Dao.custom.AdminDao;
import lk.ijse.gdse71.final_project.bo.custom.AdminBO;
import lk.ijse.gdse71.final_project.bo.custom.BOFactory;
import lk.ijse.gdse71.final_project.bo.custom.impl.AdminBOImpl;
import lk.ijse.gdse71.final_project.dto.AdminDto;
import lk.ijse.gdse71.final_project.Dao.custom.impl.AdminDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignInFromController implements Initializable {

    @FXML
    private AnchorPane ancMain;

    @FXML
    private Label lblIdShow;

    @FXML
    private AnchorPane ancSide;

    @FXML
    private Button btuSingIn;

    @FXML
    private Label lblCourse;

    @FXML
    private Label lblRegistration;

    @FXML
    private Label lblSystem;

    @FXML
    private Label lblUniversity;

    @FXML
    private Label lblWellcom;

    @FXML
    private Pane paneLogin;

    @FXML
    private TextField txtAdminId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private ComboBox<String> cmdRole;

    @FXML
    private Label lblChooseRole;

    @FXML
    private Button btnBack;

    AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBo(BOFactory.BOType.ADMIN);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] roleArray = {"Academic", "Counselor", "financial"};
        ObservableList<String> roleList = FXCollections.observableArrayList();
        roleList.addAll(roleArray);
        cmdRole.setItems(roleList);
        try {
            getNextAdminId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSingOnAction(ActionEvent event) throws SQLException, IOException {
        String id = lblIdShow.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String role = lblChooseRole.getText();

        if (txtName.getText().isEmpty() &&  txtEmail.getText().isEmpty() && txtPassword.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Text feild are empty...!","Fill all text field...!");

            return;
        }

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPass = password.matches(passwordRegex);

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Input name is invalid...!");
            showAlert(Alert.AlertType.ERROR,"Invalid Name", "Please enter a valid name (only letters and spaces are allowed).");
            return;
        }
        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
            System.out.println("Input email is invalid....!");
            showAlert(Alert.AlertType.ERROR,"Invalid Email", "Please enter a valid email address.");
            return;
        }
        if (!isValidPass) {
            txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: red;");
            System.out.println("Input password is invalid....!");
            showAlert(Alert.AlertType.ERROR,"Invalid Password", "Password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a digit, and a special character.");
            return;
        }
        try {
            String pass = txtAdminId.getText();

            String adminPass = adminBO.getAdminPass(pass);
            if (!pass.equalsIgnoreCase(adminPass)){
                System.out.println(adminPass);
                showAlert(Alert.AlertType.ERROR,"Password incorrect","Admin password incorrect...!");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isValidName && isValidEmail && isValidPass) {
            AdminDto adminDto1 = new AdminDto(id, name, email, password, role);

            boolean isSaved = adminBO.save(adminDto1);


            if (isSaved) {
                checkAdminPass();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoardView.fxml"));
                Parent load = loader.load();
                DashBoardController dashBoardController = loader.getController();
                dashBoardController.initialize(adminDto1);

                Stage dashboardStage = new Stage();
                dashboardStage.setScene(new Scene(load));
                dashboardStage.show();
                showAlert(Alert.AlertType.INFORMATION,"Successfully..!","Added successfully..!");

            } else {
                showAlert(Alert.AlertType.ERROR,"Added not successfully..!","Please try again.....!");
            }

        }

    }

    public void getNextAdminId() throws SQLException {
         String id = adminBO.getNextId();
        lblIdShow.setText(id);
    }

    @FXML
    void cmbRoleOnAction(ActionEvent event) {
        lblChooseRole.setText(cmdRole.getSelectionModel().getSelectedItem());
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void checkAdminPass(){

    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ancMain.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginFromView.fxml"));
        Parent root = loader.load();
        Stage login = new Stage();
        login.setScene(new Scene(root));
        login.show();

    }
}
