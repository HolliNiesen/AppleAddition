package com.mathtastic.persistence;

import com.mathtastic.entity.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Holli on 7/18/2017.
 */
public class QuestionDataTest {

    @Test
    public void getEasyQuestion() {
        QuestionData questionData = new QuestionData();
        ArrayList<Question> question = questionData.getRandomQuestions(false);

        for (int count = 0; count < 5; count++) {
            assertNotNull(question);
            assertTrue(question.get(count).getAnswer() <= 10);
        }
    }

    @Test
    public void getDifficultQuestion() {
        QuestionData questionData = new QuestionData();
        ArrayList<Question> question = questionData.getRandomQuestions(true);

        for (int count = 0; count < 5; count++) {
            assertNotNull(question.get(count));
        }
    }
}
