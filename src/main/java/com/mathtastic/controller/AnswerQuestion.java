package com.mathtastic.controller;

import com.mathtastic.entity.Image;
import com.mathtastic.entity.Question;
import com.mathtastic.persistence.ImageData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

/**
 * AnswerQuestion handles numbers sent through appleAdditionAskQuestion.jsp.
 * Answers that are incorrect are added to a list of "tricky questions" to be
 * displayed at end game and stored in the database if a user is logged in.
 */
@WebServlet (
        urlPatterns = "/answerQuestion"
)
public class AnswerQuestion extends HttpServlet {
    private boolean imagesOn;
    private HttpSession session;
    private Question question;
    private ArrayList<Question> questions;
    private HashMap<Question, Integer> trickyQuestions;
    private Image leftImage;
    private Image rightImage;
    private Image answerImage;

    /**
     * Gets the servlet request and response, and sets up and displays the
     * answer stored for the question asked.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setSession(request);
        loadGame();
        setChildAnswer(request);
        setAnswerImages();
        displayAnswer(request, response);
    }

    /**
     * Sets the current session.
     * @param request The servlet request.
     */
    private void setSession(HttpServletRequest request) {
        session = request.getSession();
    }

    /**
     * Sets up the game-related instance variables.
     */
    private void loadGame() {
        loadQuestions();
        loadQuestion();
        loadTrickyQuestions();
    }

    /**
     * Sets the ArrayList of questions from the session.
     */
    private void loadQuestions() {
        questions = (ArrayList<Question>) session.getAttribute("questions");
    }

    /**
     * Sets the question asked from the session.
     */
    private void loadQuestion() {
        question = (Question) session.getAttribute("question");
    }

    /**
     * Sets the tricky questions from the session.
     */
    private void loadTrickyQuestions() {
        trickyQuestions = (HashMap<Question, Integer>) session.getAttribute("trickyQuestions");
    }

    /**
     * Sets the answer submitted from the session.
     * @param request The servlet request.
     */
    private void setChildAnswer(HttpServletRequest request) {
        int childAnswer = parseInt(request.getParameter("childAnswer"));
        compareAnswers(childAnswer);
    }

    /**
     * Compares the answer submitted to the answer stored in the database.
     * If the answer is correct, the question is removed from the list and the list is reset.
     * If the answer is incorrect, the question is compared to the list of "tricky questions"
     * and the list of "tricky questions" is reset.
     * @param childAnswer The answer submitted
     */
    private void compareAnswers(int childAnswer) {
        int questionIndex = questions.indexOf(question);

        if (childAnswer == question.getAnswer()) {
            questions.remove(questionIndex);
            setQuestions();
        } else {
            instantiateTrickyQuestions();
            compareToTrickyQuestions();
            setTrickyQuestions();
        }
    }

    /**
     * Sets up the new ArrayList of questions.
     */
    private void setQuestions() {
        session.setAttribute("questions", questions);
    }

    /**
     * Instantiates the list of "tricky questions" if it doesn't already exist.
     */
    private void instantiateTrickyQuestions() {
        if (trickyQuestions == null) {
            trickyQuestions = new HashMap<Question, Integer>();
        }
    }

    /**
     * Looks for the question asked in the current list of "tricky questions".
     * If the question is in the list, the number of attempts are increased by 1.
     * If the question is not in the list, it is added to the "tricky questions",
     * and the minimum number of attempts is set.
     */
    private void compareToTrickyQuestions() {
        int MINIMUM_ATTEMPTS = 2;

        if (!trickyQuestions.containsKey(question)) {
            trickyQuestions.put(question, MINIMUM_ATTEMPTS);

        } else {
            int attempts = trickyQuestions.get(question) + 1;
            trickyQuestions.put(question, attempts);
        }
    }

    /**
     * Sets the new session of "tricky questions".
     */
    private void setTrickyQuestions() {
        session.setAttribute("trickyQuestions", trickyQuestions);
    }

    /**
     * Gets the name of each image if they are requested and sets them.
     */
    private void setAnswerImages() {
        imagesOn = (boolean) session.getAttribute("imagesOn");

        if (imagesOn) {
            leftImage = setImage(question.getLeftImageId());
            rightImage = setImage(question.getRightImageId());
            answerImage = setImage(question.getAnswerImageId());
        }
    }

    /**
     * Sets an image based on the "ImageId" in the database.
     * @param imageId The image id.
     * @return The image information.
     */
    private Image setImage(int imageId) {
        ImageData imageData = new ImageData();
        return imageData.getImageById(imageId);
    }

    /**
     * Displays the answer stored in the database.
     * If images are requested, it sets them for display.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or interrupted.
     */
    private void displayAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("leftNumber", question.getLeftNumber());
        request.setAttribute("rightNumber", question.getRightNumber());
        request.setAttribute("answer", question.getAnswer());

        if (imagesOn) {
            request.setAttribute("leftImage", leftImage.getName());
            request.setAttribute("rightImage", rightImage.getName());
            request.setAttribute("answerImage", answerImage.getName());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/appleAdditionAnswer.jsp");
        dispatcher.forward(request, response);
    }
}
