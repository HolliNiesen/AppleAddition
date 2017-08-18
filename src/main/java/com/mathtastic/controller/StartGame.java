package com.mathtastic.controller;

import com.mathtastic.entity.Question;
import com.mathtastic.persistence.QuestionData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * StartGame handles the tear-down of unfinished games and setup of a new game.
 * Game difficulty and image display are set from the form on appleAddition.jsp.
 * Once the setup is complete, the first question is asked through the AskQuestion class.
 */
@WebServlet(
        urlPatterns = {"/startGame"}
)
public class StartGame extends HttpServlet {
    private HttpSession session;

    /**
     * Gets the servlet request and response from appleAddition.jsp, and starts the game.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setSession(request);
        checkImageDisplay(request);
        resetTrickyQuestions();
        loadQuestions(request);
        startGame(request, response);
    }

    /**
     * Sets the current session.
     * @param request The servlet request.
     */
    private void setSession(HttpServletRequest request) {
        session = request.getSession();
    }

    /**
     * Sets the game difficulty
     * @param request The servlet request.
     * @return Difficult questions boolean.
     */
    private boolean setGameDifficulty(HttpServletRequest request) {

        if (request.getParameter("difficultQuestions") == null) {
            return false;
        }
        return true;
    }

    /**
     * Checks for image display.
     * True for images.
     * False for no images.
     * @param request The servlet request.
     */
    private void checkImageDisplay(HttpServletRequest request) {
        boolean imagesOn = true;

        if (request.getParameter("imagesOn") == null) {
            imagesOn = false;
        }
        session.setAttribute("imagesOn", imagesOn);
    }

    /**
     * Removes any tricky questions from the session.
     * Needed if a user started a game and left it unfinished.
     */
    private void resetTrickyQuestions() {
        if (session.getAttribute("trickyQuestions") != null) {
            session.removeAttribute("trickyQuestions");
        }
    }

    /**
     * Gets a list of questions from the database and sets them to the session.
     * @param request The servlet request.
     */
    private void loadQuestions(HttpServletRequest request) {
        boolean difficultQuestions = setGameDifficulty(request);
        QuestionData questionData = new QuestionData();
        ArrayList<Question> questions = questionData.getRandomQuestions(difficultQuestions);
        HttpSession session = request.getSession();
        session.setAttribute("questions", questions);
    }

    /**
     * Starts the game in the AskQuestion class.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void startGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AskQuestion askQuestion = new AskQuestion();
        askQuestion.startGame(request, response);
    }
}
