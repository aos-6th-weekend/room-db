package com.example.rathana.roompersistence.data.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rathana.roompersistence.data.entity.Book;
import com.example.rathana.roompersistence.data.entity.UserBooks;

import java.util.List;

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Book ... books);
    @Update
    void edit(Book book);

    @Delete
    void delete(Book b);

    @Query("select * from book order by id asc")
    List<Book> getBooks();

    @Query("select * from users order by id asc")
    List<UserBooks> getUserBooks();
}
