package io.uscool.quizapp.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.uscool.quizapp.R;
import io.uscool.quizapp.models.Quiz.ReviewItem;

/**
 * Created by ujjawal on 22/6/17.
 */

public class ReviewAdapter extends BaseAdapter {
    private List<ReviewItem> mReviewItemList;
    private Context mContext;
    
    public ReviewAdapter(List<ReviewItem> reviewItems, Context context) {
        this.mReviewItemList = reviewItems;
        this.mContext = context;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return mReviewItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return mReviewItemList.get(position).hashCode();
    }

    @Override
    public Object getItem(int position) {
        return mReviewItemList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View rootView = inflater.inflate(R.layout.list_item_review, container, false);
        
        ReviewItem reviewItem = mReviewItemList.get(position);
        String userAnswer = reviewItem.getUserAnswer();
        if(TextUtils.isEmpty(userAnswer)) {
            userAnswer = "(None)";
        }

        ((TextView) rootView.findViewById(android.R.id.text1)).setText(reviewItem.getQuestionText());
        ((TextView) rootView.findViewById(android.R.id.text2)).setText(userAnswer);

        return rootView;
    }
}
