package com.example.fafa.controller.exercisegroup;

import com.example.fafa.TrenApp;
import com.example.fafa.model.ExerciseGroup;
import com.example.fafa.service.ExerciseGroupService;
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

public class ExerciseGroupController {
    private List<ExerciseGroup> exerciseGroups;

    @FXML
    private Button btnUsers;
    @FXML
    private TableColumn<?, ?> normCaloriesColumn;
    @FXML
    private Button btnRecords;
    @FXML
    private TableView<ExerciseGroupTableItem> exerciseGroupsTable;
    @FXML
    private Button btnExerciseGroups;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> loadTypeColumn;

    private ObservableList<ExerciseGroupTableItem> exerciseGroupsObservable;

    @FXML
    void btnAddExerciseGroup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TrenApp.class.getResource("add-edit-exercise-group-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(TrenApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить группу упражнений");
            AddEditExerciseGroupDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteExerciseGroup(ActionEvent event) {
        ExerciseGroupTableItem currentItem = exerciseGroupsTable.getSelectionModel().getSelectedItem();
        int currentItemId = exerciseGroupsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getName() + "\"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new ExerciseGroupService().delete(currentItem.getExerciseGroup());
                exerciseGroupsTable.getItems().remove(currentItemId);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnEditExerciseGroup(ActionEvent event) {
        ExerciseGroupTableItem currentItem = exerciseGroupsTable.getSelectionModel().getSelectedItem();
        int currentItemId = exerciseGroupsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(TrenApp.class.getResource("add-edit-exercise-group-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(TrenApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать группу упражнений");
                AddEditExerciseGroupDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getExerciseGroup());
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
    void btnRecords(ActionEvent event) {
        TrenApp.primaryStage.setScene(TrenApp.records);
    }

    @FXML
    void btnOff(ActionEvent event) {
        TrenApp.primaryStage.close();
    }

    @FXML
    void btnUpdateExerciseGroups(ActionEvent event) {
        updateList();
    }

    public void updateList() {
        try {
            exerciseGroups = new ExerciseGroupService().findAll();
            exerciseGroupsObservable.clear();
            if (exerciseGroups != null) {
                for (ExerciseGroup exerciseGroup : exerciseGroups) {
                    exerciseGroupsObservable.add(new ExerciseGroupTableItem(exerciseGroup));
                }
            }
            exerciseGroupsTable.refresh();
        } catch (Exception e) {
            System.err.println("Ошибка в updateList():");
            e.printStackTrace();
        }
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        normCaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("normCalories"));
        loadTypeColumn.setCellValueFactory(new PropertyValueFactory<>("loadType"));

        exerciseGroupsObservable = FXCollections.observableArrayList();
        exerciseGroupsTable.setItems(exerciseGroupsObservable);
        updateList();
    }

    public void btnUsers(ActionEvent actionEvent) {
        TrenApp.primaryStage.setScene(TrenApp.users);
    }
}