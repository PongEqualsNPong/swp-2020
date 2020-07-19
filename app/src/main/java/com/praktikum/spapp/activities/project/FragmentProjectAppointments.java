package com.praktikum.spapp.activities.project;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.praktikum.spapp.R;
import com.praktikum.spapp.activities.appointment.CreateAppointmentActivity;
import com.praktikum.spapp.models.Appointment;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterAppointment;

import java.util.ArrayList;

public class FragmentProjectAppointments extends Fragment {
    RecyclerViewAdapterAppointment adapter;
    ArrayList<Appointment> appointments;
    View view;
    Button button_create_appointment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project_appointments, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.appointment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        button_create_appointment = view.findViewById(R.id.button_create_appointment);
        Project project = (Project) getArguments().getSerializable("project");

        button_create_appointment.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), CreateAppointmentActivity.class);
            intent.putExtra("project", project);
            startActivity(intent);
        });

        appointments = project.getAppointments();

        adapter = new RecyclerViewAdapterAppointment(appointments, view.getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

}