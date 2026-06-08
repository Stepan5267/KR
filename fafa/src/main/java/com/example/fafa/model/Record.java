package com.example.fafa.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "record")
public class Record {
    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private ExerciseGroup exerciseGroup;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "record_date")
    private LocalDate recordDate;


    public Integer getRecordId() { return recordId; }
    public void setRecordId(Integer recordId) { this.recordId = recordId; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public ExerciseGroup getExerciseGroup() { return exerciseGroup; }
    public void setExerciseGroup(ExerciseGroup exerciseGroup) { this.exerciseGroup = exerciseGroup; }

    public Double getCalories() { return calories; }
    public void setCalories(Double calories) { this.calories = calories; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
}