package io.uscool.quizapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import io.uscool.quizapp.models.Subject;

/**
 * Created by ujjawal on 8/6/17.
 */

public class QuizDatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "quiz_app.db";
    private static  final int DATABASE_VERSION = 1;

    private static List<Subject> mSubjectList;

    private static QuizDatabaseHelper mInstance;

    private QuizDatabaseHelper(Context context) {
      // prevents external instance creation
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static QuizDatabaseHelper getInstance(Context context) {
      if(mInstance == null) {
          mInstance = new QuizDatabaseHelper(context.getApplicationContext());
      }
      return mInstance;
    }

    private static SQLiteDatabase getReadableDatabase(Context context) {
        return getInstance(context).getReadableDatabase();
    }

    public static List<Subject> getSubjects(Context context) {
        if(mSubjectList == null) {
            mSubjectList = loadSubjects(context);
        }
        return mSubjectList;
    }

    private static List<Subject> loadSubjects(Context context) {
        Cursor data = QuizDatabaseHelper.getSubjectCusor(context);
        List<Subject> tmpSubjectList = new ArrayList<>(data.getCount());
        final SQLiteDatabase readableDatabase = QuizDatabaseHelper.getReadableDatabase(context);
        do {
            final Subject subject = getSubject(data, readableDatabase);
            tmpSubjectList.add(subject);
        } while (data.moveToNext());
        return tmpSubjectList;
    }

    private static Cursor getSubjectCusor(Context context) {
        SQLiteDatabase readableDatabase = QuizDatabaseHelper.getReadableDatabase(context);
        final String TABLE_NAME = "subject";
        final String [] TABLE_PROJECTION = {"id", "name"};
        Cursor data = readableDatabase.query(TABLE_NAME, TABLE_PROJECTION, null, null, null, null, null);
        data.moveToFirst();
        return data;
    }

    private static Subject getSubject(Cursor data, SQLiteDatabase readableDatabase) {
        // magic number based on subject TABLE_PROJECTION
        final String id = data.getString(0);
        final String name = data.getString(1);
        return new Subject(id, name);
    }






}
