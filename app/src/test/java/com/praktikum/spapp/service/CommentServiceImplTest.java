package com.praktikum.spapp.service;

import android.app.RecoverableSecurityException;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Comment;
import com.praktikum.spapp.service.internal.CommentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceImplTest extends AbstractTestBundle {

    /* The project id */
    final Long projectId = 1L;

    CommentService adminService = new CommentServiceImpl(adminSession);
    CommentService usersService = new CommentServiceImpl(userSession);

    @Test
    public void testGetAllProjectComments() throws ResponseException {
        ArrayList<Comment> list = adminService.getAllComments(1L);
        assertEquals(1, list.size());
    }

    @Test
    public void testUpdateComment() throws ResponseException {
        adminService.updateComment(2L, true, "First!");
        assertEquals(1, adminService.getAllComments(1L).size());
        assertEquals(0, adminService.getPublicComments(1L).size());
        assertEquals(0, usersService.getPublicComments(1L).size());
    }


}
