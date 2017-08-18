package com.mathtastic.controller;

import com.mathtastic.entity.Question;
import com.mathtastic.entity.User;
import com.mathtastic.persistence.TrickyQuestionData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * EndGame handles tracking "tricky questions" and displays game results to
 * appleAdditionResults.jsp.
 * Users who are logged in have their "tricky questions" added to the database
 * for those who would like to look at their history.
 */
public class EndGame {
    private HttpSession session;
    private User user;
    private HashMap<Question, Integer> trickyQuestions;

    /**
     * Gets the servlet request and response from the AskQuestion class and handles
     * end game logic.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    public void initiateEndGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setSession(request);
        setUser();
        setTrickyQuestions();
        addTrickyQuestionsToDatabase();
        removeTrickyQuestionsFromSession();
        resetImageDisplay();
        displayTrickyQuestions(request, response);
    }

    /**
     * Sets the current session.
     * @param request The servlet request.
     */
    private void setSession(HttpServletRequest request) {
        session = request.getSession();
    }

    /**
     * Sets the user from the session.
     */
    private void setUser() {
        user = (User) session.getAttribute("user");
    }

    /**
     * Sets the "tricky questions" from the session.
     */
    private void setTrickyQuestions() {
        trickyQuestions = (HashMap<Question, Integer>) session.getAttribute("trickyQuestions");
    }

    /**
     * Adds the map of "tricky questions" to the database.
     */
    private void addTrickyQuestionsToDatabase() {
        if (user != null && trickyQuestions != null) {
            TrickyQuestionData trickyQuestionData = new TrickyQuestionData();
            trickyQuestionData.insertTrickyQuestions(trickyQuestions, user);
        }
    }

    /**
     * Removes the "tricky questions" from the session.
     */
    private void removeTrickyQuestionsFromSession() {
        session.removeAttribute("trickyQuestions");
    }

    /**
     * Removes the images boolean from the session.
     */
    private void resetImageDisplay() {
        session.removeAttribute("imagesOn");
    }

    /**
     * Displays the "tricky questions" on appleAdditionResults.jsp.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void displayTrickyQuestions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("trickyQuestions", trickyQuestions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/appleAdditionResults.jsp");
        dispatcher.forward(request, response);
    }
}
