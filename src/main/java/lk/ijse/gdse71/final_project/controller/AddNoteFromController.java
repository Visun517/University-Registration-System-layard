package lk.ijse.gdse71.final_project.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.gdse71.final_project.Dao.custom.StudentDao;
import lk.ijse.gdse71.final_project.Dao.custom.impl.StudentDaoImpl;
import lk.ijse.gdse71.final_project.bo.custom.BOFactory;
import lk.ijse.gdse71.final_project.bo.custom.StudentBO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNoteFromController implements Initializable {

    @FXML
    private Button btnNoteSave;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmcStudentId;

    @FXML
    private Label lblNote;

    @FXML
    private Label lblNoteStudentId;

    @FXML
    private Label lblNoteStudentName;

    @FXML
    private Label lblStudetnNameShow;

    @FXML
    private TextField txtAddNote;

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBo(BOFactory.BOType.STUDENT);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllStudentId();
    }

    @FXML
    void btnNoteSaveOnAction(ActionEvent event) {

        String id = cmcStudentId.getSelectionModel().getSelectedItem();
        String note = txtAddNote.getText();
        try {
            boolean isSaved = studentBO.saveNote(id,note);
            if (isSaved){
                txtAddNote.setText("");
                new Alert(Alert.AlertType.INFORMATION,"Note is saved...").showAndWait();
            }else{
                new Alert(Alert.AlertType.ERROR,"Note is not saved...").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void getAllStudentId(){
        try {
            ObservableList<String> ids  = studentBO.getAllStudentIds();
            cmcStudentId.setItems(ids);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmcStudentIdOnAction(ActionEvent event) {
        String id = cmcStudentId.getSelectionModel().getSelectedItem();
        try {
            String name = studentBO.getStudentName(id);
            lblStudetnNameShow.setText(name);

            String note = studentBO.getStudentNote(id);
            txtAddNote.setText(note);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnResetOnAction(ActionEvent event) {
        cmcStudentId.setValue("");
        txtAddNote.setText("");
        lblStudetnNameShow.setText("");

    }


}
