package com.example.fafa.controller.exercisegroup;

import com.example.fafa.model.ExerciseGroup;
import com.example.fafa.service.ExerciseGroupService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEditExerciseGroupDialog {
    @FXML
    private TextField nameField;
    @FXML
    private TextField normCaloriesField;
    @FXML
    private TextField loadTypeField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button okButton;

    private Stage dialogStage;
    private ExerciseGroup exerciseGroup;

    void add() {
        try {
            if (nameField.getText().isEmpty()) {
                throw new IllegalArgumentException("Нужно заполнить поле \"Название\"");
            }
            if (normCaloriesField.getText().isEmpty()) {
                throw new IllegalArgumentException("Нужно заполнить поле \"Норма калорий\"");
            }
            if (loadTypeField.getText().isEmpty()) {
                throw new IllegalArgumentException("Нужно заполнить поле \"Тип нагрузки\"");
            }

            exerciseGroup = new ExerciseGroup();
            exerciseGroup.setName(nameField.getText());
            exerciseGroup.setNormCalories(Double.parseDouble(normCaloriesField.getText()));
            exerciseGroup.setLoadType(loadTypeField.getText());

            new ExerciseGroupService().save(exerciseGroup);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Норма калорий должна быть числом");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        okButton.setOnAction((ActionEvent ww) -> add());
    }

    void edit() {
        try {
            exerciseGroup.setName(nameField.getText());
            exerciseGroup.setNormCalories(Double.parseDouble(normCaloriesField.getText()));
            exerciseGroup.setLoadType(loadTypeField.getText());

            new ExerciseGroupService().update(exerciseGroup);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Норма калорий должна быть числом");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setEditDialogStage(Stage dialogStage, ExerciseGroup exerciseGroup) {
        this.exerciseGroup = exerciseGroup;
        this.dialogStage = dialogStage;

        nameField.setText(exerciseGroup.getName());
        normCaloriesField.setText(String.valueOf(exerciseGroup.getNormCalories()));
        loadTypeField.setText(exerciseGroup.getLoadType());

        okButton.setOnAction((ActionEvent ww) -> edit());
    }
}