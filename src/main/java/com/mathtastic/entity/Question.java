package com.mathtastic.entity;

/**
 * Question class represents a question asked in the game.
 * Created by Holli on 7/11/2017.
 */
public class Question {
    private int questionId;
    private int leftNumber;
    private int rightNumber;
    private int answer;
    private int leftImageId;
    private int rightImageId;
    private int answerImageId;

    /**
     * Empty constructor.
     */
    public Question() {}

    /**
     * Constructor
     * @param questionId the id in the table
     * @param leftNumber left of the addition sign
     * @param rightNumber right of the addition sign
     * @param answer the solution to the equation
     */
    public Question(int questionId, int leftNumber, int rightNumber, int answer, int leftImageId, int rightImageId, int answerImageId) {
        this.questionId = questionId;
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
        this.answer = answer;
        this.leftImageId = leftImageId;
        this.rightImageId = rightImageId;
        this.answerImageId = answerImageId;
    }

    /**
     * Gets the question id given in the table
     * @return the id of the question
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Gets the number left of the addition sign
     * @return the number left of the addition sign
     */
    public int getLeftNumber() {
        return leftNumber;
    }

    /**
     * Gets the number right of the addition sign
     * @return the number right of the addition sign
     */
    public int getRightNumber() {
        return rightNumber;
    }

    /**
     * Gets the solution to the equation
     * @return the solution to the equation
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * Gets the image id that corresponds with the number left of the addition sign
     * @return the image id
     */
    public int getLeftImageId() {
        return leftImageId;
    }

    /**
     * Gets the image id that corresponds with the number right of the addition sign
     * @return the image id
     */
    public int getRightImageId() {
        return rightImageId;
    }

    /**
     * Gets the image id that corresponds with the answer to the question
     * @return the image id
     */
    public int getAnswerImageId() {
        return answerImageId;
    }

    /**
     * Returns the left side of the question.
     * @return The left side of the question.
     */
    public String toString() {
        return leftNumber + " + " + rightNumber;
    }

}
