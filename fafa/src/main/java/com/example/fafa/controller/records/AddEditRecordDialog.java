package com.example.fafa.controller.records;

import com.example.fafa.model.Users;
import com.example.fafa.model.ExerciseGroup;
import com.example.fafa.model.Record;
import com.example.fafa.service.UserService;
import com.example.fafa.service.ExerciseGroupService;
import com.example.fafa.service.RecordService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddEditRecordDialog implements Initializable {
    @FXML
    private ComboBox<Users> userField;
    @FXML
    private ComboBox<ExerciseGroup> exerciseGroupField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField caloriesField;
    @FXML
    private DatePicker recordDateField;
    @FXML
    private Button okButton;

    private Stage dialogStage;
    private Record record;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Users> users = new UserService().findAll();
        userField.getItems().addAll(FXCollections.observableList(users));

        List<ExerciseGroup> exerciseGroups = new ExerciseGroupService().findAll();
        exerciseGroupField.getItems().addAll(FXCollections.observableList(exerciseGroups));
    }

    private void add() {
        try {
            if (userField.getValue() == null) {
                throw new IllegalArgumentException("Выберите пользователя");
            }
            if (exerciseGroupField.getValue() == null) {
                throw new IllegalArgumentException("Выберите группу упражнений");
            }
            if (caloriesField.getText().isEmpty()) {
                throw new IllegalArgumentException("Введите количество калорий");
            }
            if (recordDateField.getValue() == null) {
                throw new IllegalArgumentException("Введите дату записи");
            }

            Record record = new Record();
            record.setUser(userField.getValue());
            record.setExerciseGroup(exerciseGroupField.getValue());
            record.setCalories(Double.parseDouble(caloriesField.getText()));
            record.setRecordDate(recordDateField.getValue());

            new RecordService().save(record);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Количество калорий должно быть числом");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    void edit() {
        try {
            record.setUser(userField.getValue());
            record.setExerciseGroup(exerciseGroupField.getValue());
            record.setCalories(Double.parseDouble(caloriesField.getText()));
            record.setRecordDate(recordDateField.getValue());

            new RecordService().update(record);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Количество калорий должно быть числом");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        okButton.setOnAction(e -> add());
    }

    public void setEditDialogStage(Stage dialogStage, Record record) {
        this.record = record;
        this.dialogStage = dialogStage;

        userField.setValue(record.getUser());
        exerciseGroupField.setValue(record.getExerciseGroup());
        caloriesField.setText(String.valueOf(record.getCalories()));
        recordDateField.setValue(record.getRecordDate());

        okButton.setOnAction(e -> edit());
    }
}