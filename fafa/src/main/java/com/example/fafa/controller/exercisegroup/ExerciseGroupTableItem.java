package com.example.fafa.controller.exercisegroup;

import com.example.fafa.model.ExerciseGroup;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ExerciseGroupTableItem {
    private SimpleStringProperty name;
    private SimpleDoubleProperty normCalories;
    private SimpleStringProperty loadType;
    private ExerciseGroup exerciseGroup;

    public ExerciseGroupTableItem(ExerciseGroup exerciseGroup) {
        this.name = new SimpleStringProperty(exerciseGroup.getName());
        this.normCalories = new SimpleDoubleProperty(exerciseGroup.getNormCalories());
        this.loadType = new SimpleStringProperty(exerciseGroup.getLoadType());
        this.exerciseGroup = exerciseGroup;
    }

    // Getters and Setters for name
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    // Getters and Setters for normCalories
    public double getNormCalories() {
        return normCalories.get();
    }

    public SimpleDoubleProperty normCaloriesProperty() {
        return normCalories;
    }

    public void setNormCalories(double normCalories) {
        this.normCalories.set(normCalories);
    }

    // Getters and Setters for loadType
    public String getLoadType() {
        return loadType.get();
    }

    public SimpleStringProperty loadTypeProperty() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType.set(loadType);
    }

    // Getter and Setter for exerciseGroup
    public ExerciseGroup getExerciseGroup() {
        return exerciseGroup;
    }

    public void setExerciseGroup(ExerciseGroup exerciseGroup) {
        this.exerciseGroup = exerciseGroup;
    }
}