package io.uscool.quizapp.helpers;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by ujjawal on 24/6/17.
 */

public class TimeHelper {
        private static long MillisecondTime, StartTime, TimeBuff,  UpdateTime = 0L ;
        private static Handler handler;
        private static int Seconds, Minutes, MilliSeconds;
        private static int mAssignedTime;
        private static TextView mTextView;

        private TimerHelper() {
        }

        public static void setTimer(TextView textView, int assignedTime) {
            mTextView = textView;
            mAssignedTime = assignedTime;
            startTimer();
        }

        public static void setTimer(TextView textView) {
            mTextView = textView;
            startTimer();
        }

        private static void startTimer() {
            handler = new Handler();
            StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
        }

        public static Runnable runnable = new Runnable() {

            public void run() {

                MillisecondTime = SystemClock.uptimeMillis() - StartTime;

                UpdateTime = TimeBuff + MillisecondTime;

                Seconds = (int) (UpdateTime / 1000);

                Minutes = mAssignedTime-Seconds / 60;

                Seconds = 60-Seconds % 60;

                MilliSeconds = (int) (UpdateTime % 1000);

                mTextView.setText("" + String.format("%02d", Minutes) + ":"
                        + String.format("%02d", Seconds));

                handler.postDelayed(this, 0);
            }

        };

    
}
