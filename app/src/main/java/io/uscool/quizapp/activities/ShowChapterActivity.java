package io.uscool.quizapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import io.uscool.quizapp.R;
import io.uscool.quizapp.models.Subject;

public class ShowChapterActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mSubjectIcon;
    private TextView mSubjectTilte;
    private Subject mSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);

        Intent intent = getIntent();
        mSubject = (Subject) intent.getSerializableExtra("subject");

        initToolbar();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSubjectIcon = (ImageView) findViewById(R.id.avatar_image);
        mSubjectTilte = (TextView) findViewById(R.id.title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSubjectIcon.setImageResource(mSubject.getIcon_id());
        mSubjectTilte.setText(mSubject.getName());
    }


}
