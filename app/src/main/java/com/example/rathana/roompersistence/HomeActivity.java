package com.example.rathana.roompersistence;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rathana.roompersistence.data.BookDatabase;
import com.example.rathana.roompersistence.data.dao.BookDao;
import com.example.rathana.roompersistence.data.dao.UserDao;
import com.example.rathana.roompersistence.data.entity.Book;
import com.example.rathana.roompersistence.data.entity.BookUser;
import com.example.rathana.roompersistence.data.entity.User;
import com.example.rathana.roompersistence.data.entity.UserBooks;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements BookAdapter.OnBookListener {

    private static final int REQUEST_CODE = 2;
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
        adapter=new BookAdapter(bookUsers,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getBooks();
        Log.e("Home", "onCreate: "+userDao.getUsers() );
    }

    private void getBooks() {
       /* List<UserBooks> userBooks=bookDao.getUserBooks();
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
        */

        adapter.setBookUsers(bookDao.getBookUsers());
        //Log.e("Home", "getBooks: "+adapter.getItemCount() );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addAuthor:
                startActivity(new Intent(this,AddNewAuthorActivity.class));

                return true;
            case  R.id.addBook:

                startActivityForResult(new Intent(this,AddNewBookActivity.class),REQUEST_CODE);

                return  true;

            default  : return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            BookUser bookUser= data.getParcelableExtra("bookUser");
            bookUser.thumbnail=BitmapFactory.decodeResource(getResources(),R.drawable.panda);
            adapter.setBookUser(bookUser);
        }
    }

    @Override
    public void onDelete(BookUser bookUser, int pos) {
        //delete from table
        Book book=new Book();
        book.id=bookUser.id;
        bookDao.delete(book);

        adapter.onDelete(bookUser, pos);

    }
}
