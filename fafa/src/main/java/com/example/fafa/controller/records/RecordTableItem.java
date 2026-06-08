package com.example.fafa.controller.records;

import com.example.fafa.model.Record;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class RecordTableItem {
    private SimpleStringProperty userFio;
    private SimpleStringProperty exerciseGroupName;
    private SimpleDoubleProperty calories;
    private SimpleObjectProperty<LocalDate> recordDate;
    private Record record;

    public RecordTableItem(Record record) {
        this.userFio = new SimpleStringProperty(record.getUser().getFio());
        this.exerciseGroupName = new SimpleStringProperty(record.getExerciseGroup().getName());
        this.calories = new SimpleDoubleProperty(record.getCalories());
        this.recordDate = new SimpleObjectProperty<>(record.getRecordDate());
        this.record = record;
    }

    public String getUserFio() {
        return userFio.get();
    }

    public SimpleStringProperty userFioProperty() {
        return userFio;
    }

    public void setUserFio(String userFio) {
        this.userFio.set(userFio);
    }

    public String getExerciseGroupName() {
        return exerciseGroupName.get();
    }

    public SimpleStringProperty exerciseGroupNameProperty() {
        return exerciseGroupName;
    }

    public void setExerciseGroupName(String exerciseGroupName) {
        this.exerciseGroupName.set(exerciseGroupName);
    }

    public double getCalories() {
        return calories.get();
    }

    public SimpleDoubleProperty caloriesProperty() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories.set(calories);
    }

    public LocalDate getRecordDate() {
        return recordDate.get();
    }

    public SimpleObjectProperty<LocalDate> recordDateProperty() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate.set(recordDate);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}