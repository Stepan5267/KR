package com.example.fafa.controller.records;

import com.example.fafa.TrenApp;
import com.example.fafa.model.Record;
import com.example.fafa.service.RecordService;
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

public class RecordController {
    private List<Record> records;
    private ObservableList<RecordTableItem> recordObservable;

    @FXML
    private TableColumn<RecordTableItem, Number> recordIdColumn;
    @FXML
    private TableColumn<RecordTableItem, String> userFioColumn;
    @FXML
    private TableColumn<RecordTableItem, String> exerciseGroupNameColumn;
    @FXML
    private TableColumn<RecordTableItem, Number> caloriesColumn;
    @FXML
    private TableColumn<RecordTableItem, String> recordDateColumn;
    @FXML
    private TableView<RecordTableItem> recordsTable;

    @FXML
    void btnRecords(ActionEvent event) {
        updateList();
    }

    @FXML
    void btnUsers(ActionEvent event) {
        TrenApp.primaryStage.setScene(TrenApp.users);
    }

    @FXML
    void btnExerciseGroups(ActionEvent event) {
        TrenApp.primaryStage.setScene(TrenApp.exerciseGroups);
    }

    @FXML
    void powerOff(ActionEvent event) {
        TrenApp.primaryStage.close();
    }

    @FXML
    void addRecord(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TrenApp.class.getResource("add-edit-record-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(TrenApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить запись");
            AddEditRecordDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (Exception e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void editRecord(ActionEvent event) {
        RecordTableItem selectedItem = recordsTable.getSelectionModel().getSelectedItem();
        int selectedIndex = recordsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1 && selectedItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(TrenApp.class.getResource("add-edit-record-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(TrenApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать запись");
                AddEditRecordDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, selectedItem.getRecord());
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
    void deleteRecord(ActionEvent event) {
        RecordTableItem currentItem = recordsTable.getSelectionModel().getSelectedItem();
        int currentItemId = recordsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить запись с ID \"" + currentItem.getRecordId() + "\"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new RecordService().delete(currentItem.getRecord());
                recordsTable.getItems().remove(currentItemId);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void updateRecords(ActionEvent event) {
        updateList();
    }

    private void updateList() {
        try {
            records = new RecordService().findAll();
            recordObservable.clear();
            if (records != null) {
                for (Record record : records) {
                    recordObservable.add(new RecordTableItem(record));
                }
            }
            recordsTable.refresh();
        } catch (Exception e) {
            System.err.println("Ошибка в updateList():");
            e.printStackTrace();
        }
    }

    public void initialize() {
        recordIdColumn.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        userFioColumn.setCellValueFactory(new PropertyValueFactory<>("userFio"));
        exerciseGroupNameColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseGroupName"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
        recordDateColumn.setCellValueFactory(new PropertyValueFactory<>("recordDate"));

        recordObservable = FXCollections.observableArrayList();
        recordsTable.setItems(recordObservable);
        updateList();
    }
}