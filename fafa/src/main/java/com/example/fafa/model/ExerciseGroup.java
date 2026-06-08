package com.example.fafa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise_group")
public class ExerciseGroup {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    @Column(name = "name")
    private String name;

    @Column(name = "norm_calories")
    private Double normCalories;

    @Column(name = "load_type")
    private String loadType;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Название группы не должно быть пустым!");
        }
    }

    public Double getNormCalories() {
        return normCalories;
    }

    public void setNormCalories(Double normCalories) {
        if (normCalories != null && normCalories > 0) {
            this.normCalories = normCalories;
        } else {
            throw new IllegalArgumentException("Норма калорий должна быть положительным числом!");
        }
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        if (loadType != null && !loadType.isEmpty()) {
            this.loadType = loadType;
        } else {
            throw new IllegalArgumentException("Тип нагрузки не должен быть пустым!");
        }
    }

    @Override
    public String toString() {
        return name + " (" + loadType + ", норма: " + normCalories + " ккал)";
    }
}