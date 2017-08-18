package com.mathtastic.controller;

import com.mathtastic.entity.TrickyQuestion;
import com.mathtastic.entity.User;
import com.mathtastic.persistence.TrickyQuestionData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ViewTrickyQuestions gets all of the "tricky questions" from the Apple Addition game
 * in a user's history and displays them on trickyQuestions.jsp.
 * If the list of "tricky questions" is empty, the list is set to null and the page is
 * still displayed.
 */
@WebServlet(
        urlPatterns = "/viewTrickyQuestions"
)
public class ViewTrickyQuestions extends HttpServlet {
    private HttpSession session;

    /**
     * Gets the servlet request and response from myAccount.jsp, and displays the
     * "tricky question" history on trickyQuestions.jsp.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getSession(request);
        setTrickyQuestions();
        displayTrickyQuestions(request, response);
    }

    /**
     * Gets the current session from the servlet request.
     * @param request The servlet request.
     */
    private void getSession(HttpServletRequest request) {
        session = request.getSession();
    }

    /**
     * Sets the list of "tricky questions" in the session.
     */
    private void setTrickyQuestions() {
        User user = getUser();
        TrickyQuestionData trickyQuestionData = new TrickyQuestionData();
        ArrayList<TrickyQuestion> trickyQuestionList = trickyQuestionData.getTrickyQuestionHistory(user);

        if (trickyQuestionList.size() == 0) {
            trickyQuestionList = null;
        }
        session.setAttribute("trickyQuestionList", trickyQuestionList);
    }

    /**
     * Gets the user from the session.
     */
    private User getUser() {
        return (User) session.getAttribute("user");
    }

    /**
     * Displays the list of "tricky questions" on trickyQuestions.jsp.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void displayTrickyQuestions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("trickyQuestions.jsp");
        dispatcher.forward(request, response);
    }
}
