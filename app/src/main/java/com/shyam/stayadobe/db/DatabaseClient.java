/*
 *
 *  * @author ghanshyamnayma on 2/7/19 1:14 AM
 *  * @file TODO: Add a class header comment!
 *  * @copyright Tewton Software Solutions. All Rights Reserved.
 *
 */

package com.shyam.stayadobe.db;


import android.content.Context;

import androidx.room.Room;

import com.shyam.stayadobe.utils.Constants;

/**
 * @author Ghanshyam Nayma on 31/08/2019.
 */
public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private DataFactory dataFactory;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        dataFactory = Room.databaseBuilder(mCtx, DataFactory.class, Constants.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public DataFactory getDataFactory() {
        return dataFactory;
    }
}
