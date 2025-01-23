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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse71.final_project.Dao.custom.SemesterDao;
import lk.ijse.gdse71.final_project.Dao.custom.SubjectDao;
import lk.ijse.gdse71.final_project.bo.custom.BOFactory;
import lk.ijse.gdse71.final_project.bo.custom.SemesterBO;
import lk.ijse.gdse71.final_project.bo.custom.StudentBO;
import lk.ijse.gdse71.final_project.bo.custom.SubjectBO;
import lk.ijse.gdse71.final_project.dto.SubjectDto;
import lk.ijse.gdse71.final_project.dto.tm.SubjectTm;
import lk.ijse.gdse71.final_project.Dao.custom.impl.SemesterDaoImpl;
import lk.ijse.gdse71.final_project.Dao.custom.impl.SubjectDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubjectViewFromController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnOpenLectureManage;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbSemesterId;

    @FXML
    private TableColumn<SubjectTm, String> colSemesterId;

    @FXML
    private TableColumn<SubjectTm, String> colSubjectDesc;

    @FXML
    private TableColumn<SubjectTm, String> colSubjectId;

    @FXML
    private TableColumn<SubjectTm, String> colSubjectName;

    @FXML
    private Label lblSemesterId;

    @FXML
    private Label lblSemesterNameShow;

    @FXML
    private Label lblSubjectDesc;

    @FXML
    private Label lblSubjectId;

    @FXML
    private Label lblSubjectIdShow;

     @FXML
    private Label lblSubjectName;

    @FXML
    private TableView<SubjectTm> tblSubject;

    @FXML
    private TextField txtSubjectDesc;

    @FXML
    private TextField txtSubjectName;

    @FXML
    private Button btnReset;

    SemesterBO semesterBO = (SemesterBO) BOFactory.getInstance().getBo(BOFactory.BOType.SEMSTER);
    SubjectBO subjectBO = (SubjectBO) BOFactory.getInstance().getBo(BOFactory.BOType.SUBJECT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSubjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colSubjectDesc.setCellValueFactory(new PropertyValueFactory<>("subDesc"));
        colSemesterId.setCellValueFactory(new PropertyValueFactory<>("semesterId"));
        refresh();
    }
    public void getAllSubjects(){
        try {
            ArrayList<SubjectDto> subjectDtos = subjectBO.getAll();
            ObservableList<SubjectTm> subjectTms = FXCollections.observableArrayList();

            for (SubjectDto subjectDto : subjectDtos){
                SubjectTm subjectTm = new SubjectTm(
                        subjectDto.getSubjectId(),
                        subjectDto.getSubjectName(),
                        subjectDto.getSubDesc(),
                        subjectDto.getSemesterId()
                );
                subjectTms.add(subjectTm);
            }
            tblSubject.setItems(subjectTms);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getNextSubjectId(){
        try {
            String id = subjectBO.getNextId();
            lblSubjectIdShow.setText(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getAllSemesterIds(){
        try {
            ObservableList<String> ids = semesterBO.getAllSemesterIds();
            cmbSemesterId.setItems(ids);

        } catch (SQLException e) {

        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblSubjectIdShow.getText();
        try {
            boolean isDelete = semesterBO.delete(id);
            if (isDelete){
                showAlert(Alert.AlertType.INFORMATION,"Delete..","Subject is Delete.....!");
                refresh();
            }else {
                showAlert(Alert.AlertType.ERROR,"Delete..","Subject is not Delete.....!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String subId = lblSubjectIdShow.getText();
        String subName = txtSubjectName.getText();
        String sudDesc = txtSubjectDesc.getText();
        String semId = cmbSemesterId.getValue();

        if (txtSubjectName.getText().isEmpty() && txtSubjectDesc.getText().isEmpty() && cmbSemesterId.getValue().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Text field are empty...!","Fill all text field...!");
            return;
        }
        txtSubjectName.setStyle(txtSubjectName.getStyle() + ";-fx-border-color: #7367F0;");
        txtSubjectDesc.setStyle(txtSubjectDesc.getStyle() + ";-fx-border-color: #7367F0;");

        String subNamePattern = "^[A-Za-z ]+$";
        String subDescPattern = "^[A-Za-z ]+$";

        boolean isValidSubName = subName.matches(subNamePattern);
        boolean isValidSudDesc = sudDesc.matches(subDescPattern);

        if (!isValidSubName) {
            txtSubjectName.setStyle(txtSubjectName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Input subject name is invalid...!");
            showAlert(Alert.AlertType.ERROR,"Invalid Name", "Please enter a valid name (only letters and spaces are allowed).");
            return;
        }
        if (!isValidSudDesc) {
            txtSubjectDesc.setStyle(txtSubjectDesc.getStyle() + ";-fx-border-color: red;");
            System.out.println("Input Description is invalid...!");
            showAlert(Alert.AlertType.ERROR,"Invalid Name", "Please enter a valid name (only letters and spaces are allowed).");
            return;
        }
        if (isValidSubName && isValidSudDesc){
            SubjectDto subjectDto = new SubjectDto(subId,subName,sudDesc,semId);

            try {
                boolean isSaved = semesterBO.save(subjectDto);
                if (isSaved){
                    showAlert(Alert.AlertType.INFORMATION,"Save..","Subject is Save.....!");
                    refresh();
                }else {
                    showAlert(Alert.AlertType.ERROR,"Save..","Subject is not Save.....!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String subId = lblSubjectIdShow.getText();
        String subName = txtSubjectName.getText();
        String sudDesc = txtSubjectDesc.getText();
        String semId = cmbSemesterId.getValue();

        SubjectDto subjectDto = new SubjectDto(subId,subName,sudDesc,semId);

        try {
            boolean isSaved = semesterBO.Update(subjectDto);
            if (isSaved){
                showAlert(Alert.AlertType.INFORMATION,"Update..","Subject is Update.....!");
                refresh();
            }else {
                showAlert(Alert.AlertType.ERROR,"Update..","Subject is not Update.....!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblSubjectOncliked(MouseEvent event) {
        SubjectTm subjectTm = tblSubject.getSelectionModel().getSelectedItem();
        if (subjectTm == null){
            showAlert(Alert.AlertType.ERROR,"Wrong row","You cliked wrong row....!");
            return;
        }
        lblSubjectIdShow.setText(subjectTm.getSubjectId());
        txtSubjectName.setText(subjectTm.getSubjectName());
        txtSubjectDesc.setText(subjectTm.getSubDesc());
        cmbSemesterId.setValue(subjectTm.getSemesterId());
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);

    }
    @FXML
    void cmbSemesterIdOnAction(ActionEvent event) {
        String id = cmbSemesterId.getValue();
        try {
            String name = subjectBO.getSemsterName(id);
            lblSemesterNameShow.setText(name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnOpenLectureManageOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/lectureViewFrom.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public void refresh(){
        getAllSubjects();
        getNextSubjectId();
        getAllSemesterIds();
        txtSubjectName.setText("");
        txtSubjectDesc.setText("");
        cmbSemesterId.setValue("");
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void btnResetOnAction(ActionEvent event) {
        refresh();
    }
}
