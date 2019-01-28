package com.example.rathana.roompersistence.data.entity;

import android.arch.persistence.room.Embedded;

public class BookUser {

    @Embedded
    public Book book;
    public int userId;
    public String userName;
}
