package com.onadasoft.roomwordsample.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.onadasoft.roomwordsample.db.entity.Word;

import java.util.List;


/**
 * In the DAO (data access object), you specify SQL queries and associate them with method calls.
 * The compiler checks the SQL and generates queries from convenience annotations for common queries.
 */
@Dao
public interface WordDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)

    @Insert
    void insert(Word word);

    @Query("DELETE FROM WordTable")
    void deleteAll();

    @Query("SELECT * FROM WordTable ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

}
