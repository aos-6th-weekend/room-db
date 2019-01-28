package com.example.rathana.roompersistence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rathana.roompersistence.data.BookDatabase;
import com.example.rathana.roompersistence.data.dao.BookDao;
import com.example.rathana.roompersistence.data.dao.UserDao;
import com.example.rathana.roompersistence.data.entity.Book;
import com.example.rathana.roompersistence.data.entity.BookUser;
import com.example.rathana.roompersistence.data.entity.User;
import com.example.rathana.roompersistence.data.entity.UserBooks;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<BookUser> bookUsers=new ArrayList<>();
    BookAdapter adapter;
    BookDao bookDao;
    UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bookDao=BookDatabase.getInstance(this).bookDao();
        userDao=BookDatabase.getInstance(this).userDao();
        recyclerView=findViewById(R.id.rvBook);
        adapter=new BookAdapter(bookUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getBooks();
        Log.e("Home", "onCreate: "+userDao.getUsers() );
    }

    private void getBooks() {
        List<UserBooks> userBooks=bookDao.getUserBooks();
        Log.e("home","UserBooks"+ userBooks.size());


        for(UserBooks u: userBooks){
            BookUser bookUser=new BookUser();
            Log.e("home","UserLoop"+ u.toString());
            Book book1=new Book();
            for (Book book: u.books){
                book1.id=book.id;
                book1.title=book.title;
                book1.publishDate=book.publishDate;
                book1.thumbnail=book.thumbnail;
            }

            bookUser.book=book1;
            bookUser.userId=u.user.id;
            bookUser.userName=u.user.name;
            this.bookUsers.add(bookUser);
        }



        adapter.setBookUser(bookUsers);
        //Log.e("Home", "getBooks: "+adapter.getItemCount() );
    }
}
