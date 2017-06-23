package io.uscool.quizapp.fragments.quiz;

import io.uscool.quizapp.models.Quiz.QuizPage;

/**
 * Created by ujjawal on 22/6/17.
 */

public interface QuizFragmentCallbacks {
    QuizPage onGetQuizPage(String key);
}
