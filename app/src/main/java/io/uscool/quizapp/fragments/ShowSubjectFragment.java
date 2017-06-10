package io.uscool.quizapp.fragments;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.uscool.quizapp.R;
import io.uscool.quizapp.activities.ShowChapterActivity;
import io.uscool.quizapp.adapters.SubjectAdapter;
import io.uscool.quizapp.database.QuizDatabaseHelper;
import io.uscool.quizapp.models.Subject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowSubjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowSubjectFragment extends Fragment {

    private final static String MIPMAP = "mipmap";

    private RecyclerView mRecycleView;
    private Activity mActivity;

    public ShowSubjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShowSubjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowSubjectFragment newInstance() {
        ShowSubjectFragment fragment = new ShowSubjectFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_subject_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        mRecycleView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view);
        setRecyclerSubjectView();
    }

    private void setRecyclerSubjectView() {
        List<Subject> subjectList = QuizDatabaseHelper.getSubjects(getContext());
        loadData(subjectList);
        SubjectAdapter subjectAdapter = new SubjectAdapter(subjectList, getContext());
        setOnclickListener(subjectAdapter, subjectList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        mRecycleView.setLayoutManager(gridLayoutManager);
        mRecycleView.setAdapter(subjectAdapter);
    }

    private void loadData(List<Subject> subjectList) {
        Resources resources = mActivity.getResources();
        String packageName = mActivity.getPackageName();
        for(Subject subject:subjectList) {
            String iconName = subject.getName().toLowerCase();
            String colorName = subject.getName().toLowerCase()+ "_underline";
            int icon = getResourceByName(iconName,"mipmap", resources, packageName);
            int color = getResourceByName(colorName, "color", resources, packageName);
            subject.setIcon_id(icon);
            subject.setUnderline_color_id(color);
        }
    }

    private int getResourceByName(String name, String identifier, Resources resources, String packageName) {
       return resources.getIdentifier(name, identifier, packageName);
    }

    private void setOnclickListener(final SubjectAdapter subjectAdapter, final List<Subject> subjectList) {
      subjectAdapter.setOnItemClickListener(new SubjectAdapter.OnItemClickListener() {
          @Override
          public void onClick(View view, int position) {
              Intent intent = new Intent(mActivity, ShowChapterActivity.class);
              intent.putExtra("subject", subjectList.get(position));
              startActivity(intent);

          }
      });
    }
}
