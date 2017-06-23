package io.uscool.quizapp.models.Quiz;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ujjawal on 22/6/17.
 */

public abstract class AbstractQuizWizardModel implements ModelCallbacks {
    protected Context mContext;

    private List<ModelCallbacks> mListeners = new ArrayList<ModelCallbacks>();
    private QuizPageList mRootPageList;

    public AbstractQuizWizardModel(Context context) {
        mContext = context;
        mRootPageList = onNewRootPageList();
    }

    /**
     * Override this to define a new wizard model.
     */
    protected abstract QuizPageList onNewRootPageList();

    @Override
    public void onPageDataChanged(QuizPage page) {
        // can't use for each because of concurrent modification (review fragment
        // can get added or removed and will register itself as a listener)
        for (int i = 0; i < mListeners.size(); i++) {
            mListeners.get(i).onPageDataChanged(page);
        }
    }

    @Override
    public void onPageTreeChanged() {
        // can't use for each because of concurrent modification (review fragment
        // can get added or removed and will register itself as a listener)
        for (int i = 0; i < mListeners.size(); i++) {
            mListeners.get(i).onPageTreeChanged();
        }
    }

    public QuizPage findByKey(String key) {
        return mRootPageList.findByKey(key);
    }

    public void load(Bundle savedValues) {
        for (String key : savedValues.keySet()) {
            mRootPageList.findByKey(key).resetData(savedValues.getBundle(key));
        }
    }

    public void registerListener(ModelCallbacks listener) {
        mListeners.add(listener);
    }

    public Bundle save() {
        Bundle bundle = new Bundle();
        for (QuizPage page : getCurrentPageSequence()) {
            bundle.putBundle(page.getKey(), page.getData());
        }
        return bundle;
    }

    /**
     * Gets the current list of wizard steps, flattening nested (dependent) pages based on the
     * user's choices.
     */
    public List<QuizPage> getCurrentPageSequence() {
        ArrayList<QuizPage> flattened = new ArrayList<QuizPage>();
        mRootPageList.flattenCurrentPageSequence(flattened);
        return flattened;
    }

    public void unregisterListener(ModelCallbacks listener) {
        mListeners.remove(listener);
    } 
}
