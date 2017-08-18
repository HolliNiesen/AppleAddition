package com.mathtastic.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SignOutUser removes the user signed in from the current session
 */
@WebServlet(
        urlPatterns = "/signOutUser"
)
public class SignOutUser extends HttpServlet {

    /**
     * Gets the servlet request and response, and removes current user
     * from the session.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
