package com.mathtastic.persistence.data;

import com.mathtastic.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Holli on 7/18/2017.
 */
public class UserDataTest {

    @Before
    public void resetUserFirstName() {
        UserData userData = new UserData();
        userData.updateColumn("FirstName", "Holli", "test@mathtastic.com");
    }

    @Test
    public void getUserByEmail() {
        UserData userData = new UserData();
        User user = userData.getUserByEmail("test@mathtastic.com");

        assertNotNull(user);
        assertEquals("UserId was not correct", 1, user.getUserId());
    }

    @Test
    public void updateUserName() {
        UserData userData = new UserData();
        userData.updateColumn("FirstName", "Holliann", "test@mathtastic.com");

        User user = userData.getUserByEmail("test@mathtastic.com");
        assertEquals("User first name was not correct", "Holliann", user.getFirstName());
    }
}
