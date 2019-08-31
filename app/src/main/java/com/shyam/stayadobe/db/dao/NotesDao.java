/*
 *
 *  * @author ghanshyamnayma on 2/7/19 12:43 AM
 *  * @file TODO: Add a class header comment!
 *  * @copyright Tewton Software Solutions. All Rights Reserved.
 *
 */

package com.shyam.stayadobe.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.shyam.stayadobe.db.entities.NotesE;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * @author Ghanshyam Nayma on 31/08/2019.
 */

@Dao
public interface NotesDao {

    @Insert(onConflict = REPLACE)
    void insertNote(NotesE placesE);

    @Query("SELECT * FROM notes")
    List<NotesE> getAllNotes();

    @Query("DELETE FROM notes")
    void deleteAllNote();

    @Update
    void updateNote(NotesE placesE);
}
