package io.uscool.quizapp.fragments.quiz;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.uscool.quizapp.R;
import io.uscool.quizapp.adapters.ReviewAdapter;
import io.uscool.quizapp.helpers.ResultHelper;
import io.uscool.quizapp.models.Quiz.AbstractQuizWizardModel;
import io.uscool.quizapp.models.Quiz.ModelCallbacks;
import io.uscool.quizapp.models.Quiz.QuizPage;
import io.uscool.quizapp.models.Quiz.ReviewItem;
import io.uscool.quizapp.models.ResultItem;

/**
 * Created by ujjawal on 22/6/17.
 */

public class ReviewFragment extends ListFragment implements ModelCallbacks {
    private Callbacks mCallback;
    private AbstractQuizWizardModel mQuizWizardModel;
    private List<ReviewItem> mReviewList;
    private ReviewAdapter mReviewAdapter;
    private int mTotalQuestions;
    private int mNumRightAnswered;
    private int mNumIncorrectAnswered;

    public ReviewFragment() {}

    public ReviewFragment newInstance() {
        ReviewFragment fragment = new ReviewFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz_review, container, false);

        TextView titleView = (TextView) rootView.findViewById(R.id.title);
        titleView.setText("Review");
        titleView.setTextColor(getResources().getColor(R.color.review_title));

        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        mReviewAdapter = new ReviewAdapter(mReviewList, getContext());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof Callbacks)) {
            throw new ClassCastException("Activity must implement fragment callbacks");
        }
        mCallback = (Callbacks) context;
        mQuizWizardModel = mCallback.onGetModel();
        mQuizWizardModel.registerListener(this);
        onPageTreeChanged();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
        mQuizWizardModel.unregisterListener(this);
    }

    @Override
    public void onPageTreeChanged() {
        onPageDataChanged(null);
    }

    @Override
    public void onPageDataChanged(QuizPage changedPage) {
        ArrayList<ReviewItem> reviewItems = new ArrayList<>();
        for(QuizPage page: mQuizWizardModel.getCurrentPageSequence()) {
            page.getReviewItem(reviewItems);
        }
        mReviewList = reviewItems;
        calculateResult();
        if(mReviewAdapter != null) {
            mReviewAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onEditScreenAfterReview(mReviewList.get(position).getPageKey());
    }

    private void calculateResult() {
        mTotalQuestions = mReviewList.size();
        mNumRightAnswered = 0;
        mNumIncorrectAnswered = 0;
        for(ReviewItem item: mReviewList) {
            if(item.getUserAnswer() != null && item.isCorrect()) {
                mNumRightAnswered++;
            } else if(item.getUserAnswer() != null && !item.isCorrect()) {
                mNumIncorrectAnswered++;
            }
        }
        ResultItem resultItem = new ResultItem(mTotalQuestions, mNumRightAnswered, mNumIncorrectAnswered);
        ResultHelper.writeToPreferences(getContext(), resultItem);
    }

    public interface Callbacks {
        AbstractQuizWizardModel onGetModel();
        void onEditScreenAfterReview(String pageKey);
    }
}
