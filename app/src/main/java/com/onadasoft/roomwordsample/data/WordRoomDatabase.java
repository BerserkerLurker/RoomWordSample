package com.onadasoft.roomwordsample.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


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
