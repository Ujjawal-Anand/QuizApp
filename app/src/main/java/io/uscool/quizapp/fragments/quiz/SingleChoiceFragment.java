package io.uscool.quizapp.fragments.quiz;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.uscool.quizapp.R;
import io.uscool.quizapp.models.Quiz.QuizPage;
import io.uscool.quizapp.models.Quiz.SingleCorrectChoiceQuiz;

/**
 * Created by ujjawal on 23/6/17.
 */

public class SingleChoiceFragment extends ListFragment {
    private static final String ARG_KEY = "key";
    private QuizFragmentCallbacks mCallback;
    private List<String> mChoice;
    private String mKey;
    private QuizPage mPage;
    private int mRightChoicePosition;

    public SingleChoiceFragment() {

    }

    public SingleChoiceFragment newInstance(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);
        SingleChoiceFragment fragment = new SingleChoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null) {
            mKey = args.getString(ARG_KEY);
            mPage = mCallback.onGetQuizPage(mKey);
            SingleCorrectChoiceQuiz fixedChoicePage = (SingleCorrectChoiceQuiz) mPage;
            mChoice = new ArrayList<>();
            for(int i=0; i<fixedChoicePage.getOptionCount(); i++) {
                mChoice.add(fixedChoicePage.getOptionAt(i));
            }
            mRightChoicePosition = fixedChoicePage.getRightChoicePosition();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz_page, container, false);
        View headerview = ((LayoutInflater) getActivity().getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_list_header_view, null, false);
        ((TextView) headerview.findViewById(android.R.id.title)).setText(mPage.getQuestionText());




        final ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        listView.addHeaderView(headerview);

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.item_answer,
                android.R.id.text1,
                mChoice));
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Pre-select currently selected item.
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String selection = mPage.getData().getString(QuizPage.DATA_KEY);
                for (int i = 0; i < mChoice.size(); i++) {
                    if (mChoice.get(i).equals(selection)) {
                        listView.setItemChecked(i+1, true); // a simple hack
                        break;
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof QuizFragmentCallbacks)) {
            throw new ClassCastException("Activity must implement PageFragmentCallbacks");
        }

        mCallback = (QuizFragmentCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        position -= 1; // a simple hack
        if (position >= 0) {
            mPage.getData().putString(QuizPage.DATA_KEY,
                    getListAdapter().getItem(position).toString());

            if (mRightChoicePosition == position) {
                mPage.getData().putBoolean(QuizPage.IS_CORRECT, true);
            } else {
                mPage.getData().putBoolean(QuizPage.IS_CORRECT, false);
            }
            mPage.notifyDataChanged();
        }
    }

}