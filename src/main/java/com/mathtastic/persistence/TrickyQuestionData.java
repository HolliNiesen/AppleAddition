package com.mathtastic.persistence;

import com.mathtastic.entity.Question;
import com.mathtastic.entity.TrickyQuestion;
import com.mathtastic.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Sends tricky question-related queries and updates to the database.
 */
public class TrickyQuestionData {

    /**
     * Inserts tricky questions in the database.
     * @param questionMap The group of questions and number of attempts.
     * @param user The user signed in.
     */
    public void insertTrickyQuestions(HashMap<Question, Integer> questionMap, User user) {
        SQLData sqlData = new SQLData();

        for (Map.Entry<Question, Integer> trickyQuestion : questionMap.entrySet()) {
            Question question = trickyQuestion.getKey();
            int attempts = trickyQuestion.getValue();

            String sql = "INSERT INTO TrickyQuestion (AdditionQuestionId, UserId, Attempts, Date) " +
                    "VALUES ("
                    + question.getQuestionId() + ", " + user.getUserId() + ", " + attempts + ", CURDATE()); ";

            sqlData.executeUpdate(sql);
        }
    }

    /**
     * Gets a user's history of "tricky questions".
     * @param user The user signed in.
     * @return The list of "tricky questions".
     */
    public ArrayList<TrickyQuestion> getTrickyQuestionHistory(User user) {
        ArrayList<TrickyQuestion> trickyQuestionList = null;
        Database database = Database.getInstance();
        String sql = "SELECT * FROM TrickyQuestion WHERE UserId = " + user.getUserId() + " ORDER BY Date desc;";
        ResultSet results = null;
        SQLData sqlData = new SQLData();

        try {
            results = sqlData.executeQuery(sql);
            if (results!= null) {
                QuestionData questionData = new QuestionData();
                trickyQuestionList = new ArrayList<TrickyQuestion>();
                while (results.next()) {
                    int questionId = getQuestionIdFromResults(results);
                    Question question = questionData.getQuestionById(questionId);
                    TrickyQuestion trickyQuestion = createTrickyQuestionFromResults(results, question);
                    trickyQuestionList.add(trickyQuestion);
                }
            }
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("TrickyQuestionData.getTrickyQuestionHistory()...SQLException: " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("TrickyQuestionData.getTrickyQuestionHistory()...Exception: " + e);
            e.printStackTrace();
        }
        return trickyQuestionList;
    }

    /**
     * Gets a question id from query results.
     * @param results The results of the query.
     * @return The question id.
     * @throws SQLException If results do not match.
     */
    private int getQuestionIdFromResults(ResultSet results) throws SQLException {
        return results.getInt("AdditionQuestionId");
    }

    /**
     * Creates a tricky question from query results and the question asked.
     * @param results The query results.
     * @param question The question asked.
     * @return The "tricky question".
     * @throws SQLException If results do not match.
     */
    private TrickyQuestion createTrickyQuestionFromResults(ResultSet results, Question question) throws SQLException {
        return new TrickyQuestion(question, results.getInt("Attempts"), results.getDate("Date").toLocalDate());
    }
}
