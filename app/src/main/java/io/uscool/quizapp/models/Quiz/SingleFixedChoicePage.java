package io.uscool.quizapp.models.Quiz;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

import io.uscool.quizapp.fragments.quiz.SingleChoiceFragment;

/**
 * Created by ujjawal on 22/6/17.
 */

public class SingleFixedChoicePage extends QuizPage {
    private ArrayList<String> mChoiceList = new ArrayList<>();
    protected int mRightChoicePosition;

    public SingleFixedChoicePage(ModelCallbacks callbacks, String questionText) {
        super(callbacks, questionText);
    }

    @Override
    public Fragment createFragment() {
        return SingleChoiceFragment.create(getKey());
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(DATA_KEY));
    }

    @Override
    public void getReviewItem(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem(getKey(), getQuestionText(),
                mData.getString(DATA_KEY), mData.getBoolean(IS_CORRECT)));
    }

    public String getOptionAt(int index) {
        return mChoiceList.get(index);
    }

    public int getOptionCount() {
        return mChoiceList.size();
    }

    public int getRightChoicePosition() {
        return mRightChoicePosition;
    }

    public SingleFixedChoicePage setChoices(String... choices) {
        mChoiceList.addAll(Arrays.asList(choices));
        return this;
    }

    public SingleFixedChoicePage setChoiceMatchPosition(int position) {
        mRightChoicePosition = position;
        return this;
    }

    public SingleFixedChoicePage setValue(String value){
        mData.putString(DATA_KEY, value);
        return this;
    }
}
