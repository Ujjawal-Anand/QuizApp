package io.uscool.quizapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.uscool.quizapp.R;
import io.uscool.quizapp.fragments.AvatarPickerDialogFragment;
import io.uscool.quizapp.fragments.SignInFragment;
import io.uscool.quizapp.helpers.UserHelper;
import io.uscool.quizapp.models.Avatars;
import io.uscool.quizapp.models.User;

/**
 * created by ujjawal on 7/6/17
 *
 * Activity used to create an user, Queries username and avatar
 */
public class SignInActivity extends AppCompatActivity implements SignInFragment.OnFragmentInteractionListener,
        AvatarPickerDialogFragment.AvatarPickerDialogListener {

    private String mUsername;

    /**
     * Instantiates the view
     * @param savedInstanceState Ignored
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.sign_in_container, SignInFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(UserHelper.isSignedIn(this)) {
            startActivity(new Intent(getApplicationContext(), ShowSubjectActivity.class));
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (UserHelper.isSignedIn(this)) {
            finish();
        }
    }

    @Override
    public void onDialogCreated(String username) {
        mUsername = username;
    }

    /**
     * called when avatar is selected in the dialog
     * @param dialog The dialog that triggers the action.
     * @param avatarResourceName resource name of selected dialog
     */
    @Override
    public void onAvatarSelected(AvatarPickerDialogFragment dialog, String avatarResourceName) {
        createUser(mUsername, avatarResourceName);
    }

    /**
     * Called when avatar selection is cancelled in the dialog
     * @param dialog The dialog that triggered the action
     */
    @Override
    public void onCancelled(AvatarPickerDialogFragment dialog) {
        createUser(mUsername, Avatars.getDefaultAvatarResourceName());
    }

      /**
     * Called when the profile creation is finished
     * @param username username that was entered
     * @param avatarResourceName Resource name of the avatar that was selected
     */
    private void createUser(String username, String avatarResourceName) {
//        mAvatarImageView.setImageResource(Avatars.getAvatarResourceId(mActivity.getApplicationContext(), avatarResourceName));
        User user = new User(username, avatarResourceName);
        UserHelper.writeToPreferences(getApplicationContext(), user);
        startActivity(new Intent(getApplicationContext(), ShowSubjectActivity.class));
        finish();
    }


}
