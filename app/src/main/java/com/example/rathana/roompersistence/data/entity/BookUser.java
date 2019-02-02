package com.example.rathana.roompersistence.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class BookUser  implements Parcelable {

    public int id;
    public  String title;
    public  String description;
    @ColumnInfo(name = "author_id")
    public int authorId;
    @ColumnInfo(name = "publish_date")
    public String publishDate;

    @ColumnInfo(name = "user_name")
    public String userName;


    @Ignore
    public Bitmap thumbnail;


    public BookUser() {
    }

    protected BookUser(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        authorId = in.readInt();
        publishDate = in.readString();
        userName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(authorId);
        dest.writeString(publishDate);
        dest.writeString(userName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookUser> CREATOR = new Creator<BookUser>() {
        @Override
        public BookUser createFromParcel(Parcel in) {
            return new BookUser(in);
        }

        @Override
        public BookUser[] newArray(int size) {
            return new BookUser[size];
        }
    };
}
