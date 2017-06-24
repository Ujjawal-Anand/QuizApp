package io.uscool.quizapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import io.uscool.quizapp.fragments.quiz.ReviewFragment;
import io.uscool.quizapp.models.Quiz.QuizPage;

import static android.support.v4.view.PagerAdapter.POSITION_NONE;
import static android.support.v4.view.PagerAdapter.POSITION_UNCHANGED;

/**
 * Created by ujjawal on 23/6/17.
 */

public class QuizPagerAdapter extends FragmentStatePagerAdapter {
    private int mCutOffPage;
    private Fragment mPrimaryItem;
    private List<QuizPage> mCurrentPageSequence;

    public QuizPagerAdapter(List<QuizPage> pageSequence, FragmentManager fm) {
        super(fm);
        mCurrentPageSequence = pageSequence;
    }

    @Override
    public Fragment getItem(int i) {
        if (i >= mCurrentPageSequence.size()) {
            return new ReviewFragment();
        }

        return mCurrentPageSequence.get(i).createFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == mCurrentPageSequence.size()) {
            return "review";
        }
        return (position+1)+" ";
    }


    @Override
    public int getItemPosition(Object object) {
        // TODO: be smarter about this
        if (object == mPrimaryItem) {
            // Re-use the current fragment (its position never changes)
            return POSITION_UNCHANGED;
        }

        return POSITION_NONE;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mPrimaryItem = (Fragment) object;
    }

    @Override
    public int getCount() {
        if (mCurrentPageSequence == null) {
            return 0;
        }
//        return Math.min(mCutOffPage + 1, mCurrentPageSequence.size() + 1);
        return mCurrentPageSequence.size()+1;
    }

    public void setCutOffPage(int cutOffPage) {
        if (cutOffPage < 0) {
            cutOffPage = Integer.MAX_VALUE;
        }
        mCutOffPage = cutOffPage;
    }

    public int getCutOffPage() {
        return mCutOffPage;
    }
}
