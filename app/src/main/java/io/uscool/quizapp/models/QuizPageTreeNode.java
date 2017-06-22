package io.uscool.quizapp.models;

import java.util.ArrayList;

/**
 * Created by ujjawal on 22/6/17.
 */

public interface QuizPageTreeNode {
    QuizPage findBYKey(String key);
    void flattenCurrentPageSequence(ArrayList<QuizPage> dest);
}
