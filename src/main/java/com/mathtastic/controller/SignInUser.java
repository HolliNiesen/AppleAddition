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
 * SignInUser signs in an existing user.
 * If user information does not match what is stored, the user is sent
 * to signInFail.jsp where the user can attempt to sign in again.
 * If the user information matches what is stored, the user session is
 * set and the user is sent to index.jsp.
 */
@WebServlet(
        urlPatterns = {"/signInUser"}
)
public class SignInUser extends HttpServlet {
    private User user;
    private String emailEntered;
    private String passwordEntered;

    /**
     * Gets the servlet request and response from either signIn.jsp or signInFail.jsp
     * and tests the user information against the database.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setEmailEntered(request);
        setUser();

        if (user != null) {
            setPasswordEntered(request);
            comparePasswords(request, response);
        } else {
            goToLoginFail(request, response);
        }
    }

    /**
     * Sets the email entered from the webpage.
     * @param request The servlet request.
     */
    private void setEmailEntered(HttpServletRequest request) {
        emailEntered = request.getParameter("email");
    }

    /**
     * Sets the password entered from the webpage.
     * @param request The servlet request.
     */
    private void setPasswordEntered(HttpServletRequest request) {
        passwordEntered = request.getParameter("password");
    }

    /**
     * Sets the user that should be logging in based on the email entered.
     */
    private void setUser() {
        UserData userData = new UserData();
        user = userData.getUserByEmail(emailEntered);
    }

    /**
     * Sends the user to loginFail.jsp.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void goToLoginFail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/signInFail.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Compares the password entered with the password stored.
     * If the passwords match, the user is signed in and sent to index.jsp.
     * If the passwords do not match, the user is sent to signInFail.jsp.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void comparePasswords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (passwordEntered.equals(user.getPassword())) {
            goToLoginSuccess(request, response);
        } else {
            goToLoginFail(request, response);
        }
    }

    /**
     * Sets the user and displays the home page.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void goToLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setUserSession(request);
        displayHomePage(request, response);
    }

    /**
     * Sets the user session.
     * @param request The servlet request.
     */
    private void setUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

    /**
     * Displays index.jsp.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void displayHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
