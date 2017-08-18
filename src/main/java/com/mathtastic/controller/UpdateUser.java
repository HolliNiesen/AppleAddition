package com.mathtastic.controller;

import com.mathtastic.entity.User;
import com.mathtastic.persistence.UserData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * UpdateUser checks the password entered on myAccount.jsp and updates the user.
 * If the password entered does not match the password stored, the user is given
 * an error message and can try to update again.
 * If the password entered matches the password stored, the user is given a success
 * message and the changes entered are stored in the database.
 */
@WebServlet(
        urlPatterns = "/updateUser"
)
public class UpdateUser extends HttpServlet {
    private User user;
    private HttpSession session;

    /**
     * Gets the servlet request and response from myAccount.jsp, and updates the user.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateMessage = "Unable to update your account.";

        setSession(request);
        setUser();
        String currentPassword = setCurrentPassword(request);

        if (currentPassword.equals(user.getPassword())) {
            updateMessage = "Account successfully updated.";
            updateUser(request);
        }
        displayMessage(request, response, updateMessage);
    }

    /**
     * Sets the current session.
     * @param request The servlet request.
     */
    private void setSession(HttpServletRequest request) {
        session = request.getSession();
    }

    /**
     * Sets the current user from the session.
     */
    private void setUser() {
        user = (User) session.getAttribute("user");
    }

    /**
     * Sets the current password entered.
     * @param request The servlet request.
     * @return The password entered.
     */
    private String setCurrentPassword(HttpServletRequest request) {
        return request.getParameter("currentPassword");
    }

    /**
     * Updates the user in the database and updates the user session.
     * @param request The servlet request.
     */
    private void updateUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String newPassword = request.getParameter("newPassword");
        String dateOfBirth = year + "-" + month + "-" + day;

        updateTable("Email", email, user.getEmail());
        email = getEmail(email);

        updateTable("FirstName", firstName, email);
        updateTable("LastName", lastName, email);

        if (day.matches("\\d+")) {
            updateTable("DateOfBirth", dateOfBirth, email);
        }
        updateTable("Password", newPassword, email);

        updateUserSession(email);
    }

    /**
     * Updates the User table.
     * If the value sent is not empty, the table is updated.
     * @param column The table column name.
     * @param value The value to update.
     * @param email The user's email.
     */
    private void updateTable(String column, String value, String email) {
        if (!value.equals("")) {
            UserData userData = new UserData();
            userData.updateColumn(column, value, email);
        }
    }

    /**
     * Gets the user's current email.
     * @param email The user's new email.
     * @return The current email.
     */
    private String getEmail(String email) {
        if (email.equals("")) {
            email = user.getEmail();
        }
        return email;
    }

    /**
     * Sets the user's new session.
     * @param email The user's email.
     */
    private void updateUserSession(String email) {
        UserData userData = new UserData();
        user = userData.getUserByEmail(email);
        session.setAttribute("user", user);
    }

    /**
     * Displays a message to myAccount.jsp.
     * @param request The servlet request.
     * @param response The servlet response.
     * @param updateMessage The message to display.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void displayMessage(HttpServletRequest request, HttpServletResponse response, String updateMessage) throws ServletException, IOException {
        request.setAttribute("updateMessage", updateMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("myAccount.jsp");
        dispatcher.forward(request, response);
    }
}
