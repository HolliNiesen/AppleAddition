package com.mathtastic.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TrickyQuestion represents a difficult question asked in the past.
 */
public class TrickyQuestion {
    private Question question;
    private int attempts;
    private String dateAsked;

    /**
     * Constructor
     * @param question The question asked.
     * @param attempts The number of attempts before answering correctly.
     * @param dateAsked The date the question was asked.
     */
    public TrickyQuestion(Question question, int attempts, LocalDate dateAsked) {
        this.question = question;
        this.attempts = attempts;
        this.dateAsked = formatDateAsked(dateAsked);
    }

    /**
     * Formats a LocalDate and converts it to a string.
     * @param dateAsked The date to convert.
     * @return The converted date.
     */
    private String formatDateAsked(LocalDate dateAsked) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        return dateAsked.format(formatter);
    }

    /**
     * Gets the question asked.
     * @return The question asked.
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Gets the attempts to answer correctly.
     * @return The attempts to answer correctly.
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Gets the date the question was asked.
     * @return The date the question was asked.
     */
    public String getDateAsked() {
        return dateAsked;
    }

    /**
     * Gets a string representation of the class.
     * @return The string representation of the class.
     */
    public String toString() {
        return "Question: " + question.toString()
                + "Answer: " + question.getAnswer()
                + "Attempts: " + attempts
                + "Date asked: " + dateAsked;
    }
}
