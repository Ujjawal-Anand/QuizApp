package io.uscool.quizapp.models.Quiz;

/**
 * Created by ujjawal on 22/6/17.
 */

public class ReviewItem {
    private String mQuestionText;
    private String mUserAnswer;
    private String mPageKey;
    private boolean mCorrect;

    public ReviewItem(String pageKey, String questionText, String userAnswer, boolean correct) {
        this.mPageKey = pageKey;
        this.mQuestionText = questionText;
        this.mUserAnswer = userAnswer;
        this.mCorrect = correct;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public String getPageKey() {
        return mPageKey;
    }

    public String getUserAnswer() {
        return mUserAnswer;
    }

    public boolean isCorrect() {
        return mCorrect;
    }

    public void setQuestionText(String mQuestionText) {
        this.mQuestionText = mQuestionText;
    }
}
