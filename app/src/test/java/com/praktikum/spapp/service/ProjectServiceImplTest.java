package com.praktikum.spapp.service;


import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.service.internal.ProjectServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectServiceImplTest extends AbstractTestBundle {

    ProjectService adminService = new ProjectServiceImpl(SessionManager.getSession());

    @Test
    public void testCreateDeleteProject() throws Exception {
        int before = adminService.fetchAllProjects().size();
        Project aProject = new Project();
        aProject.setName("TESTESTPROJECT");
        aProject.setDescription("This is a project description");
        Long aProjectId = adminService.createProject(aProject);

        int after = adminService.fetchAllProjects().size();

        assertEquals(before + 1, after);

        // cleanUp
        adminService.deleteProject(aProjectId);
        assertEquals(before, adminService.fetchAllProjects().size());
    }

    @Test
    public void testCreateFulProject() throws Exception {
        Project aProject = new Project();
        aProject.setName("FULLPROJECTCHIGGA");
        aProject.setDescription("A project for rich chiggas");
        //TODO
    }



}