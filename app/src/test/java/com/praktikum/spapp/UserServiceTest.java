package com.praktikum.spapp;

import com.praktikum.spapp.Service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.net.ProtocolException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

//    @Test
//    public void getTestStringTest() throws IOException {
//        String actual = getTestString();
//        String expected  = "{\n  \"userId\": 1,\n  \"id\": 1,\n  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n}";
//        assertEquals(expected, actual);
//    }

    @Test
    public void loginOnServerTest() throws JSONException, ProtocolException {

        String nameAdmin = "admin";
        String passwordAdmin = "admin";
        String wrongPasswordAdmin = "wrong";

        // mock objects
        UserService userService =  mock(UserService.class);
        // the json that should be returned uppn successfull login
        JSONObject mockJsonSuccess = mock(JSONObject.class);
        mockJsonSuccess.put("tokentype", "Bearer");
        mockJsonSuccess.put("accessToken", "ABCDEFG");
        mockJsonSuccess.put("success", "1");
        // in string format
        String successString = mockJsonSuccess.toString();

        when(userService.loginOnServer(nameAdmin, passwordAdmin)).thenReturn(successString);

        // assert
        assertEquals( successString, userService.loginOnServer(nameAdmin,passwordAdmin));


        JSONObject mockJsonFail = mock(JSONObject.class);
        mockJsonFail.put("success", "0");
        mockJsonFail.put("Error", "This is an error message");
        String failString =  mockJsonFail.toString();

        when(userService.loginOnServer(nameAdmin, wrongPasswordAdmin)).thenReturn(failString);

        // assert
        assertEquals( failString, userService.loginOnServer(nameAdmin,wrongPasswordAdmin));



    }
}