package com.example.rathana.roompersistence.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.rathana.roompersistence.data.dao.UserDao;
import com.example.rathana.roompersistence.data.entity.User;


@Database(version = 1,entities = {User.class})
public abstract class BookDatabase extends RoomDatabase {

    public  abstract UserDao userDao();

    public  static BookDatabase getInstance(Context context){
        return Room.databaseBuilder(context,BookDatabase.class,
                "bookDB")
                .allowMainThreadQueries()
                .build();
    }


}
