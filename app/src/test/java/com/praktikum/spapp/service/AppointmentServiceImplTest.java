package com.praktikum.spapp.service;

import com.google.gson.JsonObject;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Appointment;
import com.praktikum.spapp.service.internal.AppointmentServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;

public class AppointmentServiceImplTest extends AbstractTestBundle {

    private final Long PROJECT_ID = 1L;

    private final Long APPOINTEMENT_ID = 1L;

    AppointmentService service = new AppointmentServiceImpl(SessionManager.getSession());

    @Test
    public void testGetAppointments() throws ResponseException {
        ArrayList<Appointment> list = service.fetchAppointments(PROJECT_ID);
        assertEquals(2, list.size());
    }

    @Test
    public void testUpdateAppointment() throws ResponseException {
        JsonObject data = new JsonObject();
        data.addProperty("name", "Ein Treffen");
        data.addProperty("description", "Nichts besonderes");
        Appointment anAppointment = service.updateAppointment(data, APPOINTEMENT_ID);
        assertEquals("Ein Treffen", anAppointment.getName());

        // clean up
        data = new JsonObject();
        data.addProperty("name", "In3Days");
        data.addProperty("description", "j");
    }

    @Test
    public void testCreateDeleteAppointment() throws ResponseException {
        Appointment anAppointment = service.fetchAppointments(PROJECT_ID).get(0);
        int before = service.fetchAppointments(PROJECT_ID).size();

        Appointment bAppointment = service.createAppointment(anAppointment, PROJECT_ID);
        assertEquals(before + 1, service.fetchAppointments(PROJECT_ID).size());

        service.deleteAppointment(bAppointment.getId());
        assertEquals(before, service.fetchAppointments(PROJECT_ID).size());
    }


}
