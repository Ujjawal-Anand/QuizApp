package io.uscool.quizapp.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.uscool.quizapp.R;
import io.uscool.quizapp.adapters.ChapterAdapter;
import io.uscool.quizapp.database.QuizDatabaseHelper;
import io.uscool.quizapp.models.Chapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowChapterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowChapterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SUBJECT_ID = "subject_id";

    // TODO: Rename and change types of parameters
    private String mSubjectId;
    
    private Activity mActivity;
    private RecyclerView mRecycleView;


    public ShowChapterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param subjectId id of the subject.
     * @return A new instance of fragment ShowChapterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowChapterFragment newInstance(String subjectId) {
        ShowChapterFragment fragment = new ShowChapterFragment();
        Bundle args = new Bundle();
        args.putString(SUBJECT_ID, subjectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSubjectId = getArguments().getString(SUBJECT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        mRecycleView = (RecyclerView) view.findViewById(R.id.fragment_chapter_recycler_view);
        setRecyclerSubjectView();
    }

    private void setRecyclerSubjectView() {
//        Chapter chapter = new Chapter("1", mSubjectId);
//        List<Chapter> chapterList = new ArrayList<>();
//        chapterList.add(chapter);
        List<Chapter> chapterList = QuizDatabaseHelper.getChapters(getContext(), mSubjectId, true);
        ChapterAdapter chapterAdapter = new ChapterAdapter(chapterList, getContext());
        setOnclickListener(chapterAdapter, chapterList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 1);
        mRecycleView.setLayoutManager(gridLayoutManager);
        mRecycleView.setAdapter(chapterAdapter);
    }

    private void setOnclickListener(final ChapterAdapter chapterAdapter, final List<Chapter> list) {
        chapterAdapter.setOnItemClickListener(new ChapterAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });
    }
}
