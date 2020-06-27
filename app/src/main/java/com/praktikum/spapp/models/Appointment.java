package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.AppointmentType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Appointment implements Serializable {

    private int id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private AppointmentType type;
    private ArrayList<Reminder> reminders;

    public Appointment(int id, String name, String description, String startDate, String endDate, AppointmentType type, ArrayList<Reminder> reminders) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.reminders = reminders;
    }

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
