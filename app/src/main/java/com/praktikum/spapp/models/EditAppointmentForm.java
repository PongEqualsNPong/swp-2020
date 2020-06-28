package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.AppointmentType;

import java.util.ArrayList;

public class EditAppointmentForm {

    int id;
    String name;
    String description;
    String startDate;
    String endDate;
    AppointmentType type;
    ArrayList<Reminder> reminders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public ArrayList<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(ArrayList<Reminder> reminders) {
        this.reminders = reminders;
    }

    public void prettyPrint(){}

}
