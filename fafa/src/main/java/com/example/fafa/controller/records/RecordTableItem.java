package com.example.fafa.controller.records;

import com.example.fafa.model.Record;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class RecordTableItem {
    private SimpleLongProperty recordId;
    private SimpleStringProperty userFio;
    private SimpleStringProperty exerciseGroupName;
    private SimpleDoubleProperty calories;
    private SimpleObjectProperty<LocalDate> recordDate;
    private Record record;

    public RecordTableItem(Record record) {
        this.recordId = new SimpleLongProperty(record.getRecordId());
        this.userFio = new SimpleStringProperty(record.getUser().getFio());
        this.exerciseGroupName = new SimpleStringProperty(record.getExerciseGroup().getName());
        this.calories = new SimpleDoubleProperty(record.getCalories());
        this.recordDate = new SimpleObjectProperty<LocalDate>(record.getRecordDate());
        this.record = record;
    }

    // Getters and Setters for recordId
    public long getRecordId() {
        return recordId.get();
    }

    public SimpleLongProperty recordIdProperty() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId.set(recordId);
    }

    // Getters and Setters for userFio
    public String getUserFio() {
        return userFio.get();
    }

    public SimpleStringProperty userFioProperty() {
        return userFio;
    }

    public void setUserFio(String userFio) {
        this.userFio.set(userFio);
    }

    // Getters and Setters for exerciseGroupName
    public String getExerciseGroupName() {
        return exerciseGroupName.get();
    }

    public SimpleStringProperty exerciseGroupNameProperty() {
        return exerciseGroupName;
    }

    public void setExerciseGroupName(String exerciseGroupName) {
        this.exerciseGroupName.set(exerciseGroupName);
    }

    // Getters and Setters for calories
    public double getCalories() {
        return calories.get();
    }

    public SimpleDoubleProperty caloriesProperty() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories.set(calories);
    }

    // Getters and Setters for recordDate
    public LocalDate getRecordDate() {
        return recordDate.getValue();
    }

    public SimpleObjectProperty<LocalDate> recordDateProperty() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate.setValue(recordDate);
    }

    // Getter and Setter for record
    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}