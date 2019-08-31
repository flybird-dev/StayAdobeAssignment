/*
 *
 *  * @author ghanshyamnayma on 2/7/19 12:43 AM
 *  * @file TODO: Add a class header comment!
 *  * @copyright Tewton Software Solutions. All Rights Reserved.
 *
 */

package com.shyam.stayadobe.db.entities;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Ghanshyam Nayma on 31/08/2019.
 */
@Entity(tableName = "notes")
public class NotesE implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id_note;

    @SerializedName("title")
    private String title;

    @SerializedName("date")
    private String date;

    @SerializedName("description")
    private String description;

    public NotesE(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NotesE{" +
                "id_note=" + id_note +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

