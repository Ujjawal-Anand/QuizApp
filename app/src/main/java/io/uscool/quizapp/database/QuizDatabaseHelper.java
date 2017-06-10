package io.uscool.quizapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import io.uscool.quizapp.models.Chapter;
import io.uscool.quizapp.models.Subject;

/**
 * Created by ujjawal on 8/6/17.
 */

public class QuizDatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "quiz.db";
    private static  final int DATABASE_VERSION = 1;

    private static List<Subject> mSubjectList;
    private static List<Chapter> mChapterList;

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
            final SQLiteDatabase readableDatabase = QuizDatabaseHelper.getReadableDatabase(context);
            mSubjectList = loadSubjects(readableDatabase);
        }
        return mSubjectList;
    }

    public static List<Chapter> getChapters(Context context, String id) {
        if(mChapterList == null) {
            final SQLiteDatabase readableDatabase = QuizDatabaseHelper.getReadableDatabase(context);
            mChapterList = loadChapters(readableDatabase, id);
        }
        return mChapterList;
    }

    private static List<Subject> loadSubjects(SQLiteDatabase database) {
        final String TABLE_NAME = "subject";
        final String [] TABLE_PROJECTION = {"id", "name"};
        Cursor cursor = database.query(TABLE_NAME, TABLE_PROJECTION, null, null, null, null, null);
        List<Subject> tmpSubjectList = new ArrayList<>(cursor.getCount());
        if(cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                final Subject subject = getSubject(cursor);
                tmpSubjectList.add(subject);
            } while (cursor.moveToNext());
        }
        return tmpSubjectList;
    }

    private static List<Chapter> loadChapters(SQLiteDatabase database, String id) {
        final String TABLE_NAME = "chapter";
        final String [] TABLE_PROJECTION = {"id", "name"};
        final String WhereClause = "subject_id = ?";
        final String [] WhereArgs  = new String[] {id};
        final String OrderBy = "seq";

        Cursor cursor = database.query(TABLE_NAME, TABLE_PROJECTION,
                WhereClause, WhereArgs, null, null, null);
        List<Chapter> tmpChapterList = new ArrayList<>(cursor.getCount());
        if(cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                tmpChapterList.add(getChapterFromSubject(cursor));
            } while(cursor.moveToNext());
        }
        return  tmpChapterList;
    }

    private static Chapter getChapterFromSubject(Cursor cursor) {
        final String id = cursor.getString(0);
        final String name = cursor.getString(1);
        return new Chapter(id, name);
    }



    private static Subject getSubject(Cursor data) {
        // magic number based on subject TABLE_PROJECTION
        final String id = data.getString(0);
        final String name = data.getString(1);
        return new Subject(id, name);
    }

}
