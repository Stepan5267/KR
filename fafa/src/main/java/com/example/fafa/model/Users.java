package com.example.fafa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "fio")
    private String fio;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "goal")
    private String goal;


    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getFio() { return fio; }
    public void setFio(String fio) { this.fio = fio; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    @Override
    public String toString() {
        return fio + " (" + weight + " кг, " + height + " см)";
    }
}