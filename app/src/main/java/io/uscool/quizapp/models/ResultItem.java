package io.uscool.quizapp.models;

/**
 * Created by ujjawal on 23/6/17.
 */

public class ResultItem {
    private int mNumQuestions = 0;
    private int mNumAnswered = 0;
    private int mNumUnanswered = 0;
    private int mNumIncorrectAnswer = 0;
    private int mTotalMarks = 0;
    private int mMarksObtained = 0;

    public ResultItem(int numQuestions, int numAnswered, int numIncorrect) {
        this.mNumQuestions = numQuestions;
        this.mNumAnswered = numAnswered;
        this.mNumIncorrectAnswer = numIncorrect;
        this.mNumUnanswered = mNumQuestions - (mNumAnswered+mNumIncorrectAnswer);
        this.mTotalMarks = numQuestions*4;
        this.mMarksObtained = numAnswered*4;
    }

    public int getMarksObtained() {
        return mMarksObtained;
    }

    public int getNumAnswered() {
        return mNumAnswered;
    }

    public int getNumIncorrectAnswer() {
        return mNumIncorrectAnswer;
    }

    public int getNumQuestions() {
        return mNumQuestions;
    }

    public int getNumUnanswered() {
        return mNumUnanswered;
    }

    public int getTotalMarks() {
        return mTotalMarks;
    }
}
