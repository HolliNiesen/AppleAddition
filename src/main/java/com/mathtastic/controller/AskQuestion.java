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
import java.util.Random;

/**
 * AskQuestion handles setup for the game logic sent through either the StartGame
 * class or through appleAddition.jsp.
 * It selects a question to ask at random and displays it on appleAdditionAskQuestion.jsp.
 */
@WebServlet (
        urlPatterns = "/askQuestion"
)
public class AskQuestion extends HttpServlet {
    private ArrayList<Question> questions;
    private boolean imagesOn;
    private HttpSession session;
    private Image leftImage;
    private Image rightImage;
    private Question question;

    /**
     * Gets the servlet request and response from appleAdditionAnswer.jsp, and runs the game.
     * @param request The servlet request.
     * @param response The servlet resposne.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        runGame(request, response);
    }

    /**
     * Gets the servlet request and response from the StartGame class, and runs the game.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    public void startGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        runGame(request, response);
    }

    /**
     * Sets up and displays the question to ask.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void runGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setSession(request);
        loadQuestions();
        testForEndGame(request, response);
    }

    /**
     * Sets the session.
     * @param request The servlet request.
     */
    private void setSession(HttpServletRequest request) {
        session = request.getSession();
    }

    /**
     * Gets the list of questions from the session.
     */
    private void loadQuestions() {
        questions = (ArrayList<Question>) session.getAttribute("questions");
    }

    /**
     * Tests for end game.
     * If the question list is not empty, it asks a question.
     * If the question list is empty, it instantiates the EndGame class.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void testForEndGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (questions.size() > 0) {
            askQuestion(request, response);
        } else {
            EndGame endGame = new EndGame();
            endGame.initiateEndGame(request, response);
        }
    }

    /**
     * Selects a question at random and displays the question with its images.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void askQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectRandomQuestion();
        setQuestion(request);
        setQuestionImages();
        displayQuestion(request, response);
    }

    /**
     * Selects a question at random from the list of questions.
     */
    private void selectRandomQuestion() {
        Random randomGenerator = new Random();
        int questionIndex = randomGenerator.nextInt(questions.size());
        question = questions.get(questionIndex);
    }

    /**
     * Sets the question session.
     * @param request The servlet request.
     */
    private void setQuestion(HttpServletRequest request) {
        session.setAttribute("question", question);
    }

    /**
     * Gets the name of each image if they are requested and sets them.
     */
    private void setQuestionImages() {
        imagesOn = (boolean) session.getAttribute("imagesOn");

        if (imagesOn) {
            leftImage = setImage(question.getLeftImageId());
            rightImage = setImage(question.getRightImageId());
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
     * Displays the question to ask.
     * If images are request, it sets them for display.
     * @param request The servlet request.
     * @param response The servlet response.
     * @throws ServletException If the servlet encountered a difficulty.
     * @throws IOException If I/O operations failed or were interrupted.
     */
    private void displayQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("leftNumber", question.getLeftNumber());
        request.setAttribute("rightNumber", question.getRightNumber());

        if (imagesOn) {
            request.setAttribute("leftImage", leftImage.getName());
            request.setAttribute("rightImage", rightImage.getName());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/appleAdditionAskQuestion.jsp");
        dispatcher.forward(request, response);
    }
}
