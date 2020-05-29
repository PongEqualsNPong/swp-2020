package com.praktikum.spapp;

import com.praktikum.spapp.Service.UserService;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

public class UserServiceTest {

//    @Test
//    public void getTestStringTest() throws IOException {
//        String actual = getTestString();
//        String expected  = "{\n  \"userId\": 1,\n  \"id\": 1,\n  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n}";
//        assertEquals(expected, actual);
//    }

    public void testAPI() throws IOException, JSONException {
        UserService userService = new UserService();
        System.out.println(userService.testAPI());
    }

    @Test
    public void loginOnServerTest() throws JSONException, IOException {

        String nameAdmin = "admin";
        String passwordAdmin = "admin";
        String wrongPasswordAdmin = "wrong";

        UserService userService = new UserService();
        System.out.println(userService.loginOnServer(nameAdmin, passwordAdmin));




    }


}