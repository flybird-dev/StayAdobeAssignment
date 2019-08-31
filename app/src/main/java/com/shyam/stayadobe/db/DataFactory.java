/*
 *
 *  * @author ghanshyamnayma on 2/7/19 12:30 AM
 *  * @file TODO: Add a class header comment!
 *  * @copyright Tewton Software Solutions. All Rights Reserved.
 *
 */

package com.shyam.stayadobe.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.shyam.stayadobe.db.dao.NotesDao;
import com.shyam.stayadobe.db.entities.NotesE;

/**
 * @author Ghanshyam Nayma on 31/08/2019.
 */
@Database(entities = {NotesE.class}, version = 1, exportSchema = false)
public abstract class DataFactory extends RoomDatabase {

    private static DataFactory INSTANCE;
    public abstract NotesDao notesDao();
}
