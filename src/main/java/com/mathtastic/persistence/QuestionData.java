package com.mathtastic.persistence;

import com.mathtastic.entity.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Sends question-related queries and updates to the database.
 * Created by Holli on 7/14/2017.
 */
public class QuestionData {

    /**
     * Gets a single question by its id.
     * @param questionId The question id.
     * @return The question requested.
     */
    public Question getQuestionById(int questionId) {
        Database database = Database.getInstance();
        Question question = null;
        String sql = "SELECT * FROM AdditionQuestion WHERE AdditionQuestionId = " + questionId + ";";
        SQLData sqlData = new SQLData();

        try {
            ResultSet results = sqlData.executeQuery(sql);
            if (results.next()) {
                question = createQuestionFromResults(results);
            }
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("QuestionData.getQuestionById()...SQLException: " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("QuestionData.getQuestionById()...Exception: " + e);
            e.printStackTrace();
        }
        return question;
    }

    /**
     * Gets a list of questions.
     * @param difficultQuestions Whether or not there are difficult questions.
     * @return The list of questions.
     */
    public ArrayList<Question> getRandomQuestions(boolean difficultQuestions) {
        String sql;
        int LIST_LENGTH = 5;

        if (difficultQuestions) {
            sql = "SELECT * FROM AdditionQuestion ORDER BY RAND() LIMIT " + LIST_LENGTH + ";";
        } else {
            sql = "SELECT * FROM AdditionQuestion WHERE Answer <= 10 ORDER BY RAND() LIMIT " + LIST_LENGTH + ";";
        }
        return sendRandomQuestionQuery(sql);
    }

    /**
     * Sends question list related queries to the SQLData class.
     * @param sql The select statement.
     * @return The list of questions requested.
     */
    private ArrayList<Question> sendRandomQuestionQuery(String sql) {
        ArrayList<Question> questions = new ArrayList<Question>();
        Database database = Database.getInstance();
        SQLData sqlData = new SQLData();

        try {
            ResultSet results = sqlData.executeQuery(sql);

            while (results.next()) {
                questions.add(createQuestionFromResults(results));
            }
            database.disconnect();

        } catch (SQLException e) {
            System.out.println("QuestionData.getRandomQuestion()...SQLException: " + e);
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("QuestionData.getRandomQuestion()...Exception" + e);
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Creates a question from the results.
     * @param results The results of the query.
     * @return The new question.
     * @throws SQLException Results do not match.
     */
    private Question createQuestionFromResults(ResultSet results) throws SQLException {
        return new Question(results.getInt("AdditionQuestionId"), results.getInt("LeftNumber"),
                results.getInt("RightNumber"), results.getInt("Answer"),
                results.getInt("LeftImageId"), results.getInt("RightImageId"),
                results.getInt("AnswerImageId"));
    }

}