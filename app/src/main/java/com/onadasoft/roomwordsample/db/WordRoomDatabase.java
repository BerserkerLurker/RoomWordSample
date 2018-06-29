package com.onadasoft.roomwordsample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.onadasoft.roomwordsample.db.dao.WordDao;
import com.onadasoft.roomwordsample.db.entity.Word;

/**
 * Room is a database layer on top of an SQLite database. Room takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.

        Room uses the DAO to issue queries to its database.
        By default, to avoid poor UI performance, Room doesn't allow you to issue database queries on the main thread. LiveData applies this rule by automatically running the query asynchronously on a background thread, when needed.
        Room provides compile-time checks of SQLite statements.
        Your Room class must be abstract and extend RoomDatabase.
        Usually, you only need one instance of the Room database for the whole app.
 **/
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {


    // Define the DAOs that work with the database. Provide an abstract "getter" method for each @Dao.
    public abstract WordDao wordDao();


    // --- Making WordRoomDatabase a singleton to prevent having multiple instances of the database opened at the same time.
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    // Create DB
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database").build();

                }

            }
        }
        return INSTANCE;
    }
    // ---


    // In case of migration --> https://medium.com/google-developers/understanding-migrations-with-room-f01e04b07929

}
