package com.example.rathana.roompersistence.data.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class UserBooks {

    @Embedded
    public User user;

    @Relation(parentColumn = "id",entityColumn = "author_id")
    public List<Book> books;

    @Override
    public String toString() {
        return "UserBooks{" +
                "user=" + user +
                ", books=" + books +
                '}';
    }
}
