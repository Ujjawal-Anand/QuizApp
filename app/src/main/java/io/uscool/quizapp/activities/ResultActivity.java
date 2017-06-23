package io.uscool.quizapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.uscool.quizapp.R;
import io.uscool.quizapp.fragments.ResultFragment;

/**
 * Created by ujjawal on 24/6/17.
 */

public class ResultActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if(savedInstanceState == null) {
            attachChapterFragment();
        }

    }

    private void attachChapterFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container,
                ResultFragment.newInstance()).commit();
    }
}
