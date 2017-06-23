package io.uscool.quizapp.models.Quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by ujjawal on 22/6/17.
 */

public abstract class QuizPage implements QuizPageTreeNode {
    public static final String DATA_KEY = "data_key";
    public static final String IS_CORRECT = "is_correct";

    protected ModelCallbacks mCallbacks;

    protected Bundle mData = new Bundle();
    protected String mQuestionText;
    protected boolean mRequired;
    protected String mParentKey;

    protected QuizPage(ModelCallbacks callbacks, String questionText) {
        mCallbacks = callbacks;
        mQuestionText = questionText;
    }

    public Bundle getData() {
        return mData;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public boolean isRequired() {
        return mRequired;
    }

    public void setParentKey(String parentKey) {
        this.mParentKey = parentKey;
    }

    public String getKey() {
        return (mParentKey != null) ? mParentKey + ":" + mQuestionText : mQuestionText;
    }

    @Override
    public QuizPage findBYKey(String key) {
        return (getKey().equals(getKey())) ? this : null;
    }

    @Override
    public void flattenCurrentPageSequence(ArrayList<QuizPage> dest) {
        dest.add(this);
    }

    public abstract Fragment createFragment();

    public abstract void getReviewItem(ArrayList<ReviewItem> dest);

    public boolean isCompleted() {
        return true;
    }

    public void notifyDataChanged() {
        mCallbacks.onPageDataChanged(this);
    }

    public void resetData(Bundle data) {
        mData = data;
        notifyDataChanged();
    }

    public QuizPage setRequired(boolean mRequired) {
        this.mRequired = mRequired;
        return this;
    }
}
