package com.praktikum.spapp.service;

import com.google.gson.JsonObject;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Appointment;

import java.util.ArrayList;

public interface AppointmentService {

    String createAppointment(JsonObject appointment, Long projectId) throws ResponseException;

    ArrayList<Appointment> fetchAppointments(Long projectId) throws ResponseException;

    Appointment updateAppointment(JsonObject data, Long appointmentId) throws ResponseException;

    void deleteAppointment(Long appointmentId) throws ResponseException;

    void toCalendar(Appointment appointment);
}
