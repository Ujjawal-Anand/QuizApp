package io.uscool.quizapp.models.Quiz;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ujjawal on 22/6/17.
 */

public class SingleCorrectChoiceQuiz extends QuizPage {
    private ArrayList<String> mChoiceList = new ArrayList<>();
    protected int mRightChoicePosition;

    public SingleCorrectChoiceQuiz(ModelCallbacks callbacks, String questionText) {
        super(callbacks, questionText);
    }

    @Override
    public Fragment createFragment() {
        return null;
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

    public SingleCorrectChoiceQuiz setChoices(String... choices) {
        mChoiceList.addAll(Arrays.asList(choices));
        return this;
    }

    public SingleCorrectChoiceQuiz setChoiceMatchPosition(int position) {
        mRightChoicePosition = position;
        return this;
    }

    public SingleCorrectChoiceQuiz setValue(String value){
        mData.putString(DATA_KEY, value);
        return this;
    }
}
