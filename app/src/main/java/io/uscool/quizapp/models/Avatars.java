package io.uscool.quizapp.models;

import android.content.Context;
import android.content.res.Resources;

import io.uscool.quizapp.R;

/**
 * Created by ujjawal on 6/6/17.
 * class for building user avatar
 */

public class Avatars {
    // List of all available avatars
    private static final Integer[] AVATARS = {
            R.mipmap.anonymous, R.mipmap.anonymous2_girl,
            R.mipmap.astronaut, R.mipmap.basketball_man,
            R.mipmap.bomberman, R.mipmap.bomberman2,
            R.mipmap.boxer_hispanic, R.mipmap.bride_hispanic_material,
            R.mipmap.budist, R.mipmap.call_center_operator_man,
            R.mipmap.cashier_woman, R.mipmap.cook2_man
    };

    /**
     * Get a list of all avatar resource ids.
     *
     * @return Array containing all avatar resource ids.
     */
    public static Integer[] getAvatarResources() {
        return AVATARS;
    }

    /**
     * Get the resource name for a given avatar resource id.
     *
     * @param context
     * @param avatarResourceId resource id of the avatar
     * @return resource name of the avatar to be used for persistent storage.
     */
    public static String getAvatarResourceName(Context context, Integer avatarResourceId) {
        Resources resources = context.getResources();
        return resources.getResourceEntryName(avatarResourceId);
    }

    /**
     * Get the resource id for a given avatar name
     *
     * @param context
     * @param avatarResourceName name of the avatar
     * @return resource id
     */
    public static Integer getAvatarResourceId(Context context, String avatarResourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(avatarResourceName, "mipmap", context.getPackageName());
    }

    /**
     * @return resource name of the default avatar
     */
    public static String getDefaultAvatarResourceName() {
        return "anonymous";
    }

    /**
     * @return resource id of the default avatar
     */
    public static Integer getDefaultAvatarResourceId() {
        return R.mipmap.anonymous;
    }
}
