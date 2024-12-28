// Test cases for Homework 2
// *** DO NOT CHANGE THIS FILE ***

import UserPackage.Administrator;
import UserPackage.Officer;
import UserPackage.User;
import UserPackage.UserList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestHomeworkp2 {
    static HashMap<String, String> configData;
    static UserList userList = new UserList();


    @Test
    public void testUserList() {
        userList.readUserFile("users.csv");
        ArrayList<User> listOfUsers = userList.getListOfUsers();
        assertEquals(4, listOfUsers.size());
        assertEquals("Jones", listOfUsers.get(0).getUserName());
        User user = listOfUsers.get(1);
        assertEquals(UserPackage.Officer.class, user.getClass());
        Officer officer = (Officer)listOfUsers.get(1);
        assertEquals("Captain", officer.getRank());
        Administrator admin = (Administrator)listOfUsers.get(0);
        assertEquals("Cyber", admin.getDepartment());
    }
}
