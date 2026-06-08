package com.example.fafa.controller.user;

import com.example.fafa.model.Users;
import com.example.fafa.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.example.fafa.TrenApp.users;

public class AddEditUserDialog {
    @FXML
    private TextField fioField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField goalField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button okButton;

    private Stage dialogStage;
    private Users user;

    void add() {
        try {
            if (fioField.getText().isEmpty()) {
                throw new IllegalArgumentException("Введите ФИО");
            }
            if (weightField.getText().isEmpty()) {
                throw new IllegalArgumentException("Введите вес");
            }
            if (heightField.getText().isEmpty()) {
                throw new IllegalArgumentException("Введите рост");
            }
            if (goalField.getText().isEmpty()) {
                throw new IllegalArgumentException("Введите цель");
            }

            user = new Users();
            user.setFio(fioField.getText());
            user.setWeight(Double.parseDouble(weightField.getText()));
            user.setHeight(Double.parseDouble(heightField.getText()));
            user.setGoal(goalField.getText());

            new UserService().save(user);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Вес и рост должны быть числами");
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
            user.setFio(fioField.getText());
            user.setWeight(Double.parseDouble(weightField.getText()));
            user.setHeight(Double.parseDouble(heightField.getText()));
            user.setGoal(goalField.getText());

            new UserService().update(user);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Вес и рост должны быть числами");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setEditDialogStage(Stage dialogStage, Users user) {
        this.user = user;
        this.dialogStage = dialogStage;

        fioField.setText(user.getFio());
        weightField.setText(String.valueOf(user.getWeight()));
        heightField.setText(String.valueOf(user.getHeight()));
        goalField.setText(user.getGoal());

        okButton.setOnAction((ActionEvent ww) -> edit());
    }
}