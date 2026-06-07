package com.example.fafa.controller.user;

import com.example.fafa.model.Users;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTableItem {
    private SimpleStringProperty fio;
    private SimpleDoubleProperty weight;
    private SimpleDoubleProperty height;
    private SimpleStringProperty goal;
    private Users user;

    public UserTableItem(Users user) {
        this.fio = new SimpleStringProperty(user.getFio());
        this.weight = new SimpleDoubleProperty(user.getWeight());
        this.height = new SimpleDoubleProperty(user.getHeight());
        this.goal = new SimpleStringProperty(user.getGoal());
        this.user = user;
    }

    public String getFio() { return fio.get(); }
    public SimpleStringProperty fioProperty() { return fio; }
    public void setFio(String fio) { this.fio.set(fio); }

    public double getWeight() { return weight.get(); }
    public SimpleDoubleProperty weightProperty() { return weight; }
    public void setWeight(double weight) { this.weight.set(weight); }

    public double getHeight() { return height.get(); }
    public SimpleDoubleProperty heightProperty() { return height; }
    public void setHeight(double height) { this.height.set(height); }

    public String getGoal() { return goal.get(); }
    public SimpleStringProperty goalProperty() { return goal; }
    public void setGoal(String goal) { this.goal.set(goal); }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }
}