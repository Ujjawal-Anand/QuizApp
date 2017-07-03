package io.uscool.quizapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import io.uscool.quizapp.R;
import io.uscool.quizapp.helpers.UserHelper;
import io.uscool.quizapp.models.Avatars;
import io.uscool.quizapp.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment implements
        TextView.OnEditorActionListener {

    private EditText mUsernameInput;
    // reference to username TextInputLayout
    private TextInputLayout mUsernameInputLayout;
    // reference to user
    private User mNewUser = null;
    private ImageView mAvatarImageView;
    private FloatingActionButton mFabLoadAvatarPickerDialog;
    private Activity mActivity;
    private OnFragmentInteractionListener mListener;


    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     *
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
      
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFabLoadAvatarPickerDialog = (FloatingActionButton) view.findViewById(R.id.load_avatar_picker_dialog);
        mFabLoadAvatarPickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUsernameLength()) {
                    createAvatarSelectDialog();
                }
            }
        });

        mUsernameInputLayout = (TextInputLayout) view.findViewById(R.id.input_username_layout);
        mUsernameInput = (EditText) view.findViewById(R.id.input_username);
        mUsernameInput.setOnEditorActionListener(this);

        mAvatarImageView = (ImageView) view.findViewById(R.id.avatar);
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

     @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignInFragment.OnFragmentInteractionListener) {
            mListener = (SignInFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        FragmentManager fm = getFragmentManager();
        AvatarPickerDialogFragment avatarPickerDialogFragment = new AvatarPickerDialogFragment();
        avatarPickerDialogFragment.show(fm, "avatar_picker");
        mListener.onDialogCreated(mUsernameInput.getText().toString().trim());

    }






    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDialogCreated(String username);
    }
}
