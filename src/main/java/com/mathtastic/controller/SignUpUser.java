package com.mathtastic.controller;

import com.mathtastic.entity.User;
import com.mathtastic.persistence.UserData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SignUpUser adds new users to the database based on their email.
 * If the email entered already exists in the database, the user is sent
 * to signUpFail.jsp and can try to sign up again from there.
 * If the email entered does not already exist in hte database, the user
 * is added, and sent to signUpSuccess.jsp.
 */
@WebServlet(
        urlPatterns = {"/signUpUser"}
)
public class SignUpUser extends HttpServlet {
    private User user;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String dateOfBirth;

    /**
     * Gets the servlet request and response from either signUp.jsp or signUpFail.jsp,
     * and tests the user's existence based on the email entered.
     * If the email is in the database, the user is sent to signUpFail.jsp.
     * If the email is not in the database, a new user is created.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        setEmail(request);
        setFirstName(request);
        setLastName(request);
        setPassword(request);
        setDateOfBirth(request);
        setUser();

        if (user != null) {
            dispatcher = request.getRequestDispatcher("signUpFail.jsp");
        } else {
            addUserToDatabase();
            dispatcher = request.getRequestDispatcher("signUpSuccess.jsp");
        }
        dispatcher.forward(request, response);
    }

    /**
     * Sets the email entered.
     * @param request The servlet request.
     */
    private void setEmail(HttpServletRequest request) {
        email = request.getParameter("email");
    }

    /**
     * Sets the first name entered.
     * @param request The servlet request.
     */
    private void setFirstName(HttpServletRequest request) {
        firstName = request.getParameter("firstName");
    }

    /**
     * Sets the last name entered.
     * @param request The servlet request.
     */
    private void setLastName(HttpServletRequest request) {
        lastName = request.getParameter("lastName");
    }

    /**
     * Sets the password entered.
     * @param request The servlet request.
     */
    private void setPassword(HttpServletRequest request) {
        password = request.getParameter("password");
    }

    /**
     * Sets the date of birth entered.
     * @param request The servlet request.
     */
    private void setDateOfBirth(HttpServletRequest request) {
        String day = getDay(request);
        String month = getMonth(request);
        String year = getYear(request);

        dateOfBirth = year + "-" + month + "-" + day;
    }

    /**
     * Gets the day entered.
     * @param request The servlet request.
     * @return The day entered.
     */
    private String getDay(HttpServletRequest request) {
        return request.getParameter("day");
    }

    /**
     * Gets the month entered.
     * @param request The servlet request.
     * @return The month entered.
     */
    private String getMonth(HttpServletRequest request) {
        return request.getParameter("month");
    }

    /**
     * Gets the year entered.
     * @param request The servlet request.
     * @return The year entered.
     */
    private String getYear(HttpServletRequest request) {
        return request.getParameter("year");
    }

    /**
     * Sets the user based on the email entered.
     * For a new user, this should return null.
     */
    private void setUser() {
        UserData userData = new UserData();
        user = userData.getUserByEmail(email);
    }

    /**
     * Adds the new user to the database.
     */
    private void addUserToDatabase() {
        UserData userData = new UserData();
        userData.createNewUser(firstName, lastName, email, dateOfBirth, password);
    }
}
