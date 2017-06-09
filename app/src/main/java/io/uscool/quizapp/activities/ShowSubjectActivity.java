package io.uscool.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import io.uscool.quizapp.R;
import io.uscool.quizapp.fragments.ShowSubjectFragment;
import io.uscool.quizapp.helpers.UserHelper;
import io.uscool.quizapp.models.Avatars;
import io.uscool.quizapp.models.User;

public class ShowSubjectActivity extends AppCompatActivity {
    private static final String EXTRA_USER = "user";

    private Toolbar mToolbar;
    private ImageView mAvatarImage;
    private TextView mTitle;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);

        mUser = UserHelper.getPlayer(getApplicationContext());
        initToolbar();
        if(savedInstanceState == null) {
            attachSubjectGridFragment();
        }

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAvatarImage = (ImageView) findViewById(R.id.avatar_image);
        mTitle = (TextView) findViewById(R.id.title);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAvatarImage.setImageResource(Avatars.getAvatarResourceId(getApplicationContext(), mUser.getAvatar()));
        mTitle.setText(mUser.getUsername());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_subject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case R.id.action_signout:
               signOut();
               return true;
       }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        UserHelper.signOut(getApplicationContext());
        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        finish();
    }

    private void attachSubjectGridFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.subject_container, ShowSubjectFragment.newInstance()).commit();
    }
}
