package com.onadasoft.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.onadasoft.roomwordsample.db.WordRoomDatabase;
import com.onadasoft.roomwordsample.db.dao.WordDao;
import com.onadasoft.roomwordsample.db.entity.Word;

import java.util.List;


/**
 * A Repository is a class that abstracts access to multiple data sources.
 * The Repository is not part of the Architecture Components libraries,
 * but is a suggested best practice for code separation and architecture.
 * A Repository class handles data operations.
 * It provides a clean API to the rest of the app for app data.
 *
 * Why use a Repository?
 * A Repository manages query threads and allows you to use multiple backends. In the most common example,
 * the Repository implements the logic for deciding whether to fetch data from a network or use results cached in a local database.
 */
public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // a constructor that gets a handle to the database and initializes the member variables.
    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    // Add a wrapper for getAllWords().
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }

    // Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
    // Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }



    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
