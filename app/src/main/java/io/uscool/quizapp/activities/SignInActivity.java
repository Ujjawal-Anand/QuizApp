package io.uscool.quizapp.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import io.uscool.quizapp.MainActivity;
import io.uscool.quizapp.R;
import io.uscool.quizapp.fragments.AvatarPickerDialogFragment;
import io.uscool.quizapp.helpers.UserHelper;
import io.uscool.quizapp.models.Avatars;
import io.uscool.quizapp.models.User;

/**
 * created by ujjawal on 7/6/17
 *
 * Activity used to create an user, Queries username and avatar
 */
public class SignInActivity extends AppCompatActivity implements
        TextView.OnEditorActionListener, AvatarPickerDialogFragment.AvatarPickerDialogListener{

    // reference to username EditTextView
    private EditText mUsernameInput;
    // reference to username TextInputLayout
    private TextInputLayout mUsernameInputLayout;
    // reference to user
    private User mNewUser = null;
    private ImageView mAvatarImageView;
    private FloatingActionButton mFabLoadAvatarPickerDialog;

    /**
     * Instantiates the view
     * @param savedInstanceState Ignored
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mFabLoadAvatarPickerDialog = (FloatingActionButton) findViewById(R.id.load_avatar_picker_dialog);
        mFabLoadAvatarPickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUsernameLength()) {
                    createAvatarSelectDialog();
                }
            }
        });

        mUsernameInputLayout = (TextInputLayout) findViewById(R.id.input_username_layout);
        mUsernameInput = (EditText) findViewById(R.id.input_username);
        mUsernameInput.setOnEditorActionListener(this);

        mAvatarImageView = (ImageView) findViewById(R.id.avatar);
        mAvatarImageView.setImageResource(Avatars.getDefaultAvatarResourceId());

        // watch for changes and trigger validation
        mUsernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // validate the username like look for username duplication or required length
                validateUsernameLength();
            }
        });


    }

    /**
     * validates useranme length and updates GUI with error
     * @return <code>false</code> if username length is 0 || username length is more than 30,
     * else <code>true</code>.
     */

    private boolean validateUsernameLength() {
        boolean isValid;
        String username = mUsernameInput.getText().toString().trim();
        if(username.length() == 0) {
            mUsernameInput.setError(getString(R.string.empty_username));
            mUsernameInputLayout.setErrorEnabled(true);
            isValid = false;
        } else if(username.length() > 30) {
            mUsernameInputLayout.setError(getString(R.string.too_long_username));
            mUsernameInputLayout.setErrorEnabled(true);
            isValid = false;
        } else {
            mUsernameInputLayout.setErrorEnabled(false);
            isValid = true;
        }

        return isValid;
    }

    /*
     * Called when an action is performed on the username input. Opens avatar selection after
     * username confirmation.
     */
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
       if(actionId == EditorInfo.IME_ACTION_DONE) { // enter is pressed
          if(validateUsernameLength()) {
              createAvatarSelectDialog();
          }

       }
        return false;
    }

    /**
     * Loads the avatar selection dialog
     */
    private void createAvatarSelectDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AvatarPickerDialogFragment avatarPickerDialogFragment = new AvatarPickerDialogFragment();
        avatarPickerDialogFragment.show(fm, "avatar_picker");
    }

    /**
     * called when avatar is selected in the dialog
     * @param dialog The dialog that triggers the action.
     * @param avatarResourceName resource name of selected dialog
     */
    @Override
    public void onAvatarSelected(AvatarPickerDialogFragment dialog, String avatarResourceName) {
        createUser(mUsernameInput.getText().toString(), avatarResourceName);
    }

    /**
     * Called when avatar selection is cancelled in the dialog
     * @param dialog The dialog that triggered the action
     */
    @Override
    public void onCancelled(AvatarPickerDialogFragment dialog) {
        createUser(mUsernameInput.getText().toString(), Avatars.getDefaultAvatarResourceName());
    }

    /**
     * Called when the profile creation is finished
     * @param username username that was entered
     * @param avatarResourceName Resource name of the avatar that was selected
     */
    private void createUser(String username, String avatarResourceName) {
        mAvatarImageView.setImageResource(Avatars.getAvatarResourceId(getApplicationContext(), avatarResourceName));
        User user = new User(username, avatarResourceName);
        UserHelper.writeToPreferences(getApplicationContext(), user);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
