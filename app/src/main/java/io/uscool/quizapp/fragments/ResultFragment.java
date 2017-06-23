package io.uscool.quizapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.uscool.quizapp.R;
import io.uscool.quizapp.helpers.ResultHelper;
import io.uscool.quizapp.models.ResultItem;

/**
 * Created by ujjawal on 24/6/17.
 */

public class ResultFragment extends Fragment {

    private static final String SUBJECT_ID = "subject_id";

    // TODO: Rename and change types of parameters
    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        dataAnalysis();
        ResultItem item = ResultHelper.getPlayer(getActivity().getApplicationContext());
        ((TextView) view.findViewById(R.id.total_marks)).append(" "+item.getTotalMarks());
        ((TextView) view.findViewById(R.id.marks_obtained)).append(" "+item.getMarksObtained());
        ((TextView) view.findViewById(R.id.num_answered)).append(" "+ item.getNumAnswered());
        ((TextView) view.findViewById(R.id.num_incorrect)).append(" "+ item.getNumIncorrectAnswer());
        ((TextView) view.findViewById(R.id.num_unanswered)).append(" "+ item.getNumUnanswered());

    }


}
