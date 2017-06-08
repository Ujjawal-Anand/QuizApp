package io.uscool.quizapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import io.uscool.quizapp.models.User;

/**
 * Created by ujjawal on 7/6/17.
 *
 * Helper class storing and retrieval of the user data using shared preferences
 * @link see tutorial https://www.tutorialspoint.com/android/android_shared_preferences.htm
 * @link stackoverflow https://stackoverflow.com/questions/23024831/android-shared-preferences-example
 */

public class UserHelper {
    private static final String USER_PREFERENCES = "userPreferences";
    private static final String PREFERENCE_USERNAME = USER_PREFERENCES + ".username";
    private static final String PREFERENCE_AVATAR = USER_PREFERENCES + ".avatar";

    private UserHelper() {
        //no instance
    }

    /**
     * Writes a {@link io.uscool.quizapp.models.User} to preferences.
     *
     * @param context The Context which to obtain the SharedPreferences from.
     * @param user  The {@link io.uscool.quizapp.models.User} to write.
     */
    public static void writeToPreferences(Context context, User user) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCE_USERNAME, user.getUsername());
        editor.putString(PREFERENCE_AVATAR, user.getAvatar());
        editor.apply();
    }

    /**
     * Retrieves a {@link io.uscool.quizapp.models.User} from preferences.
     *
     * @param context The Context which to obtain the SharedPreferences from.
     * @return A previously saved user or <code>null</code> if none was saved previously.
     */
    public static User getPlayer(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        final String username = preferences.getString(PREFERENCE_USERNAME, null);
        final String avatar = preferences.getString(PREFERENCE_AVATAR, null);
        return new User(username, avatar);
    }

    /**
     * Signs out a user by removing all it's data.
     *
     * @param context The context which to obtain the SharedPreferences from.
     */
    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCE_USERNAME);
        editor.remove(PREFERENCE_AVATAR);
        editor.apply();
    }

    /**
     * Checks whether a user is currently signed in.
     *
     * @param context The context to check this in.
     * @return <code>true</code> if login data exists, else <code>false</code>.
     */
    public static boolean isSignedIn(Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCE_USERNAME) &&
                preferences.contains(PREFERENCE_AVATAR);
    }

    /**
     * Checks whether the user's input data is valid.
     *
     * @param mUsername   The user's username.
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
        return context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }
}
