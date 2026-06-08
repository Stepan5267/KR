package com.example.fafa.controller.user;

import com.example.fafa.TrenApp;
import com.example.fafa.model.Users;
import com.example.fafa.model.Users;
import com.example.fafa.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserController {
    private List<Users> users;
    private ObservableList<UserTableItem> usersObservable;

    @FXML
    private TableColumn<UserTableItem, String> fioColumn;
    @FXML
    private TableColumn<UserTableItem, Number> weightColumn;
    @FXML
    private TableColumn<UserTableItem, Number> heightColumn;
    @FXML
    private TableColumn<UserTableItem, String> goalColumn;
    @FXML
    private TableView<UserTableItem> usersTable;

    @FXML
    void btnRecords(ActionEvent event) {
        TrenApp.primaryStage.setScene(TrenApp.records);
    }

    @FXML
    void btnUsers(ActionEvent event) {
        updateList();
    }

    @FXML
    void btnExerciseGroups(ActionEvent event) {
        TrenApp.primaryStage.setScene(TrenApp.exerciseGroups);
    }

    @FXML
    void btnOff(ActionEvent event) {
        TrenApp.primaryStage.close();
    }

    @FXML
    void btnAddUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TrenApp.class.getResource("add-edit-user-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(TrenApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить пользователя");
            AddEditUserDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void btnEditUser(ActionEvent event) {
        UserTableItem currentItem = usersTable.getSelectionModel().getSelectedItem();
        int currentItemId = usersTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(TrenApp.class.getResource("add-edit-user-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(TrenApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать пользователя");
                AddEditUserDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getUser());
                dialogStage.showAndWait();
                updateList();
            } catch (IOException e) {
                System.out.println("Ошибка открытия окна: " + e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для редактирования");
            alert.showAndWait();
        }
    }

    @FXML
    void btnDeleteUser(ActionEvent event) {
        UserTableItem currentItem = usersTable.getSelectionModel().getSelectedItem();
        int currentItemId = usersTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getFio() + "\"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new UserService().delete(currentItem.getUser());
                usersTable.getItems().remove(currentItemId);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateUsers(ActionEvent event) {
        updateList();
    }

    private void updateList() {
        try {
            users = new UserService().findAll();
            usersObservable.clear();
            if (users != null) {
                for (Users user : users) {
                    usersObservable.add(new UserTableItem(user));
                }
            }
            usersTable.refresh();
        } catch (Exception e) {
            System.err.println("Ошибка в updateList():");
            e.printStackTrace();
        }
    }

    public void initialize() {
        fioColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        goalColumn.setCellValueFactory(new PropertyValueFactory<>("goal"));

        usersObservable = FXCollections.observableArrayList();
        usersTable.setItems(usersObservable);
        updateList();
    }
}