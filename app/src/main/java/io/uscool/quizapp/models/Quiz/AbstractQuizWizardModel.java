package io.uscool.quizapp.models.Quiz;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ujjawal on 22/6/17.
 */

public abstract class AbstractQuizWizardModel implements ModelCallbacks {
    private Context mContext;
    private List<ModelCallbacks> mListeners = new ArrayList<>();
    private QuizPageList mRootPageList;

    public AbstractQuizWizardModel(Context context) {
        this.mContext = context;
        this.mRootPageList = onNewRootPageList();
    }
    /**
     * Override this to create new Quiz Wizard Model
     * */
    protected abstract QuizPageList onNewRootPageList();

    @Override
    public void onPageDataChanged(QuizPage page) {
        for(int i=0; i<mRootPageList.size(); i++) {
            mListeners.get(i).onPageDataChanged(page);
        }

    }

    public QuizPage findByKey(String key) {
        return mRootPageList.findBYKey(key);
    }

    public void load(Bundle savedValues) {
        for(String key:savedValues.keySet()) {
            mRootPageList.findBYKey(key).resetData(savedValues.getBundle(key));
        }
    }

    public void registerListener(ModelCallbacks listener) {
        mListeners.add(listener);
    }

    public Bundle save() {
        Bundle bundle = new Bundle();
        for(QuizPage page : getCurrentPageSequence()) {
            bundle.putBundle(page.getKey(), page.getData());
        }

        return bundle;
    }

    public List<QuizPage> getCurrentPageSequence() {
        ArrayList<QuizPage> flattened = new ArrayList<>();
        mRootPageList.flattenCurrentPageSequence(flattened);
        return flattened;
    }

    public void unregisterListener(ModelCallbacks listener) {
        mListeners.remove(listener);
    }

}
