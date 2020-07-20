package com.praktikum.spapp.service;

import android.app.RecoverableSecurityException;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Comment;
import com.praktikum.spapp.service.internal.CommentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommentServiceImplTest extends AbstractTestBundle {

    /* The project id */
    final Long projectId = 1L;

    CommentService adminService = new CommentServiceImpl(adminSession);
    CommentService usersService = new CommentServiceImpl(userSession);

    @Test
    public void testGetAllProjectComments() throws ResponseException {
        ArrayList<Comment> list = adminService.getAllComments(1L);
        assertEquals(2, list.size());
    }

    @Test
    public void testUpdateComment() throws ResponseException {
        Comment result = adminService.updateComment(projectId, true, "First!");
        assertEquals(1, adminService.getPublicComments(projectId).size());
        result = adminService.updateComment(projectId, false, "First!");
        assertEquals(2, usersService.getPublicComments(projectId).size());
    }

    @Test
    public void testCreateDeleteComment() throws ResponseException {
        int before = adminService.getAllComments(projectId).size();
        Comment aComment = adminService.createComment(projectId, false, "Ein TestComment!");

        assertEquals(before + 1, adminService.getAllComments(projectId).size());

        adminService.deleteComment(aComment.getId());

        assertEquals(before, adminService.getAllComments(projectId).size());

    }


}
