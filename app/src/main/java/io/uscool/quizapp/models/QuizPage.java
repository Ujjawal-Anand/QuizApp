package io.uscool.quizapp.models;

import android.os.Bundle;

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
    protected boolean mResquired;
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

    public boolean isResquired() {
        return mResquired;
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

    public QuizPage setResquired(boolean mResquired) {
        this.mResquired = mResquired;
        return this;
    }
}
