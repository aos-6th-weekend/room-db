package com.example.rathana.roompersistence.data.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

@Entity(tableName = "book",
foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "id",childColumns = "author_id")})

public class Book {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public  String title;
    public  String description;
    @ColumnInfo(name = "author_id")
    public int authorId;
    @ColumnInfo(name = "publish_date")
    public String publishDate;

    @Ignore
    public Bitmap thumbnail;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", publishDate='" + publishDate + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
