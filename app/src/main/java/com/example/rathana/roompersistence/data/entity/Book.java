package com.example.rathana.roompersistence.data.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "book",
foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "id",childColumns = "author_id")})

public class Book  implements Parcelable {

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

    public Book() { }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        authorId = in.readInt();
        publishDate = in.readString();
        thumbnail = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(authorId);
        dest.writeString(publishDate);
        dest.writeParcelable(thumbnail, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

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
