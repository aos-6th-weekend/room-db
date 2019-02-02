package com.example.rathana.roompersistence.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rathana.roompersistence.data.entity.User;

import java.util.List;


@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(User... users);

    @Insert
    void add(List<User> users);

    @Insert
    void add(User user);

    @Update
    void edit(User user);

    @Delete
    void delete(User user);

    @Query("select * from users")
    List<User> getUsers();

}
