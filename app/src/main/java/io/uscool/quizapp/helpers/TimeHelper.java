package io.uscool.quizapp.helpers;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by ujjawal on 24/6/17.
 */

public class TimeHelper {
        private static long  StartTime;
        private static long mPauseTime = 0L;
        private static int Seconds, Minutes, MilliSeconds;
        private static int mAssignedTime;
        private static Handler mHandler = new Handler();
        private static TextView mTextView;
        private static long timeInMilliseconds = 0L;
        private static long timeSwapBuff = 0L;
        private static long updatedTime = 0L;
        private static boolean mPause = true;

        private TimeHelper() {

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

        public static void pauseTimer() {
            mPause = true;
            mHandler.removeCallbacks(updateTimerThread);
        }

        private static void startTimer() {
            Minutes = 0;
            Seconds = 0;
            MilliSeconds = 0;
            StartTime = SystemClock.uptimeMillis();
            mPause = false;
            mHandler.postDelayed(updateTimerThread, 0);
        }

    public static boolean isPause() {
        return mPause;
    }

    public static void restartTimer() {
        if(isPause()) {
            timeSwapBuff += timeInMilliseconds;
            mHandler.postDelayed(updateTimerThread, 0);
            mPause = false;
            mPauseTime = 0;
        }
    }

    private static Runnable updateTimerThread = new Runnable() {

            public void run() {

                timeInMilliseconds = SystemClock.uptimeMillis() - StartTime;

                updatedTime = timeSwapBuff + timeInMilliseconds;

                Seconds = (int) (updatedTime / 1000);

                Minutes = mAssignedTime-Seconds / 60;

                Seconds = 60-Seconds % 60;

                MilliSeconds = (int) (updatedTime % 1000);

                mTextView.setText("" + String.format("%02d", Minutes) + ":"
                        + String.format("%02d", Seconds));

                mHandler.postDelayed(this, 0);
                if(Minutes < 0) {
                    mHandler.removeCallbacks(this);
                    mTextView.setText("Exam Over");
                }
            }

        };


}
