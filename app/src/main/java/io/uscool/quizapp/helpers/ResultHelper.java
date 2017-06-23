package io.uscool.quizapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import io.uscool.quizapp.models.ResultItem;

/**
 * Created by ujjawal on 23/6/17.
 */

public class ResultHelper {
    private static final String RESULT_PREFERENCES = "resultPreferences";
    private static final String PREFERENCE_NUM_QUESTIONS = RESULT_PREFERENCES + ".numQuestions";
    private static final String PREFERENCE_NUM_ANSWERED = RESULT_PREFERENCES + ".numAnswered";
    private static final String PREFERENCE_NUM_INCORRECT_ANSWER = RESULT_PREFERENCES + ".numIncorrectAnswer";


    private ResultHelper() {
        //no instance
    }

    /**
     * Writes a {@link ResultItem} to preferences.
     *
     * @param context    The Context which to obtain the SharedPreferences from.
     * @param resultItem The {@link ResultItem} to write.
     */
    public static void writeToPreferences(Context context, ResultItem resultItem) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(PREFERENCE_NUM_QUESTIONS, resultItem.getNumQuestions());
        editor.putInt(PREFERENCE_NUM_ANSWERED, resultItem.getNumAnswered());
        editor.putInt(PREFERENCE_NUM_INCORRECT_ANSWER, resultItem.getNumIncorrectAnswer());
        editor.apply();
    }

    /**
     * Retrieves a {@link ResultItem} from preferences.
     *
     * @param context The Context which to obtain the SharedPreferences from.
     * @return A previously saved resultItem or <code>null</code> if none was saved previously.
     */
    public static ResultItem getPlayer(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        final int numQuestions = preferences.getInt(PREFERENCE_NUM_QUESTIONS, 0);
        final int numAnswered = preferences.getInt(PREFERENCE_NUM_ANSWERED, 0);
        final int numIncorrectAnswer = preferences.getInt(PREFERENCE_NUM_INCORRECT_ANSWER, 0);

        return new ResultItem(numQuestions, numAnswered, numIncorrectAnswer);
    }

    /**
     * Signs out a resultItem by removing all it's data.
     *
     * @param context The context which to obtain the SharedPreferences from.
     */
    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCE_NUM_QUESTIONS);
        editor.remove(PREFERENCE_NUM_ANSWERED);
        editor.apply();
    }


    /**
     * Checks whether the resultItem's input data is valid.
     *
     * @param mUsername The resultItem's username.
     * @return <code>true</code> if username is not null nor 0-length, else <code>false</code>.
     */
    public static boolean isInputDataValid(CharSequence mUsername) {
        return !TextUtils.isEmpty(mUsername);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(RESULT_PREFERENCES, Context.MODE_PRIVATE);
    }
}
