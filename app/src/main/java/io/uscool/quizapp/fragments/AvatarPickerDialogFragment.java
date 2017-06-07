package io.uscool.quizapp.fragments;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;

import com.makeramen.roundedimageview.RoundedImageView;

import io.uscool.quizapp.R;
import io.uscool.quizapp.adapters.AvatarImageAdapter;
import io.uscool.quizapp.models.Avatars;

/**
 * Created by ujjawal on 7/6/17.
 * <p/>
 * A dialog fragment that allows user to select avatar from predefined set of images
 * <p/>
 * Documentation (Dialogs): http://developer.android.com/guide/topics/ui/dialogs.html
 * Documentation (GridView): http://developer.android.com/guide/topics/ui/layout/gridview.html
 */

public class AvatarPickerDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener{

    //use this interface to deliver action events
    AvatarPickerDialogListener mListener;
    // References to currently selcted item
    private String mSelectedAvatarEntryName;
    private RoundedImageView mSelectedAvatarImageView = null;
    // reference to created alert dialog
    private AlertDialog mCreatedDialog;

    /**
     * The activity that opens this dialog must implement AvatarPickerDialogListener
     * This method stores the listener when the activity is attached
     */
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        // verify that host activity implements the callback interface
        try {
            // cast to AvatarPickerDialogListener so we can send events to host
            mListener = (AvatarPickerDialogListener) context;
        } catch(ClassCastException e) {
            // the activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString() +
                    " activity must implement AvatarPickerDialogListener ");
        }
    }

    /**
    * Called to create the dialog, creates a custom alert dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // use the builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for dialog
        // Pass null as parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_avatar_picker, null);
        builder.setView(view);

        GridView gridView = (GridView) view.findViewById(R.id.avatar_grid);
        gridView.setAdapter(new AvatarImageAdapter(getContext()));
        gridView.setOnItemClickListener(this);

        final AvatarPickerDialogFragment self = this;
        builder.setMessage(R.string.message_select_avatar)
                .setPositiveButton(R.string.action_select, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onAvatarSelected(self, mSelectedAvatarEntryName);
                    }
                }).setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onCancelled(self);
            }
        });

        // Create the AlertDialog
        mCreatedDialog = builder.create();

        // disable select button untill an avatar is picked
        mCreatedDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                mCreatedDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
        });

        return mCreatedDialog;
    }

    /**
     * This method is called when an avatar is selected in the dialog
     *
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // avatar selected; enable select button
        mCreatedDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

        // reset border on previously selected avatar when new one is selected
        if(mSelectedAvatarImageView != null) {
            mSelectedAvatarImageView.setBorderWidth(0.f);
        }

        // use device independent pixels for uniform border width
        float borderWidthDip = TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP, 20.f, getContext().getResources().getDisplayMetrics());

        // animate border on currently selected avatar
        RoundedImageView imageView = (RoundedImageView) view;
        ObjectAnimator anim = ObjectAnimator.ofFloat(imageView, "borderWidth", 0f, borderWidthDip);
        anim.setDuration(300);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();

        int accentColor = ContextCompat.getColor(getContext(), R.color.colorAccent);
        imageView.setBorderColor(accentColor);

        // store selected resource id
        Integer avatarResourceId = Avatars.getAvatarResources()[position];
        mSelectedAvatarEntryName = Avatars.getAvatarResourceName(getContext(), avatarResourceId);
        mSelectedAvatarImageView = imageView;
    }

    /**
     * The activity that creates an instance of this dialog must
     * implement this interface in order to receive event callbacks
     * Eac method passes DialogFragment as argument in the case the
     * host needs to query it
     */
    public interface AvatarPickerDialogListener {
        /**
        * Called when the avatar has been selected by the user
        * @param dialog The dialog that triggers the action.
         * @param resourceName The resource name of selected avatar
         *
         */
        void onAvatarSelected(AvatarPickerDialogFragment dialog, String resourceName);
        /**
         * Called when avatar selection is aborted by the user
         * @param dialog The dialog that triggered the action
         */
        void onCancelled(AvatarPickerDialogFragment dialog);
    }
}
