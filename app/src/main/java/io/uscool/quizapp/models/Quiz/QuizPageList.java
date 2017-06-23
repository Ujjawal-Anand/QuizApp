package io.uscool.quizapp.models.Quiz;

import java.util.ArrayList;

/**
 * Created by ujjawal on 22/6/17.
 */

public class QuizPageList extends ArrayList<QuizPage> implements QuizPageTreeNode {
    public QuizPageList() {

    }

    public QuizPageList(QuizPage... pages) {
        for(QuizPage page: pages) {
            add(page);
        }
    }

    @Override
    public QuizPage findByKey(String key) {
        for(QuizPage childPage: this) {
            QuizPage found = childPage.findByKey(key);
            if(found != null) {
                return found;
            }
        }
        return null;
    }

    @Override
    public void flattenCurrentPageSequence(ArrayList<QuizPage> dest) {
        for(QuizPage childPage:this) {
            childPage.flattenCurrentPageSequence(dest);
        }
    }
}
