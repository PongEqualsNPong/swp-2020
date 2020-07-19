package com.praktikum.spapp.service;

import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Appointment;
import com.praktikum.spapp.service.internal.AppointmentServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;

public class AppointmentServiceImplTest extends AbstractTestBundle{

    private final Long PROJECT_ID = 1L;

    AppointmentService service = new AppointmentServiceImpl(SessionManager.getSession());

    @Test
    public void testGetAppointments() throws ResponseException{
        ArrayList<Appointment> list = service.fetchAppointments(PROJECT_ID);
        assertEquals(2, list.size());
    }

//    @Test
//    public void testUpdare



}
