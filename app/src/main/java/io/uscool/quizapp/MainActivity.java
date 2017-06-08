package io.uscool.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import io.uscool.quizapp.helpers.UserHelper;
import io.uscool.quizapp.models.Avatars;
import io.uscool.quizapp.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = UserHelper.getPlayer(getApplicationContext());

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(user.getUsername());

        ImageView avatarImageView = (ImageView) findViewById(R.id.avatar_image);
        avatarImageView.setImageResource(Avatars.getAvatarResourceId(getApplicationContext(), user.getAvatar()));

    }
}
