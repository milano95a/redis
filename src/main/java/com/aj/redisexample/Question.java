package com.aj.redisexample;

import java.io.Serializable;

/**
 * Created by AB on 13-Sep-17.
 */

public class Question{

    String question;
    String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
