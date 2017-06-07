package io.uscool.quizapp.adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.makeramen.roundedimageview.RoundedImageView;

import io.uscool.quizapp.models.Avatars;

/**
 * Created by ujjawal on 7/6/17.
 */

public class AvatarImageAdapter extends BaseAdapter {
    private Context mContext;
    // references to avatar images
    private Integer[] mThumbIds = Avatars.getAvatarResources();
    // references to ImageViews
    private RoundedImageView[] mImages = new RoundedImageView[mThumbIds.length];

    /**
     * Constructor defines the context
     * @param context
     */
    public AvatarImageAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * Get the length of the adapter
     * @return Thumb-Id length
     */
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    /**
     * Get an object at the position
     * @param position Position of the item
     * @return Object at the position
     */
    @Override
    public Object getItem(int position) {
        return mImages[position];
    }

    /**
     * Get the id of an item
     * @param position Position of the item
     * @return 0
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Creates a new view for each item referenced by the adapter
     * @param position Position of the item
     * @param convertView
     * @param parent
     * @return imageView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RoundedImageView imageView;
        if(convertView == null) {
            //if it's not recycled, initialize some attributes
            imageView = new RoundedImageView(mContext);

            int sizeDip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    85.f,
                    mContext.getResources().getDisplayMetrics());

            imageView.setLayoutParams(new GridView.LayoutParams(sizeDip, sizeDip));
            imageView.setOval(true);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (RoundedImageView) convertView;
        }

        mImages[position] = imageView;
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
