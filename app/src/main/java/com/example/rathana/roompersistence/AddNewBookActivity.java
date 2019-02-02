package com.example.rathana.roompersistence;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rathana.roompersistence.data.BookDatabase;
import com.example.rathana.roompersistence.data.dao.BookDao;
import com.example.rathana.roompersistence.data.dao.UserDao;
import com.example.rathana.roompersistence.data.entity.Book;
import com.example.rathana.roompersistence.data.entity.BookUser;
import com.example.rathana.roompersistence.data.entity.User;
import com.example.rathana.roompersistence.data.entity.UserBooks;

import java.util.ArrayList;
import java.util.List;

public class AddNewBookActivity extends AppCompatActivity {

    EditText bookTitle, bookDesc,bookPublishDate;

    Spinner authorNameSpinner;
    int authorId;
    String  authorName;
    BookDao dao;
    UserDao userDao;
    List<String> authorNames=new ArrayList<>();
    List<User> authors=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        bookDesc=findViewById(R.id.description);
        bookPublishDate=findViewById(R.id.publishDate);
        bookTitle=findViewById(R.id.bookTitle);
        authorNameSpinner= findViewById(R.id.authorName);

        dao=BookDatabase.getInstance(this).bookDao();
        userDao=BookDatabase.getInstance(this).userDao();

        authors.addAll(userDao.getUsers());
        for(User u: authors){
            authorNames.add(u.name);
        }
        //get default author id
        authorId=authors.get(0).id;
        ArrayAdapter arrayAdapter=new ArrayAdapter(
                this,android.R.layout.simple_list_item_1,authorNames
        );

        authorNameSpinner.setAdapter(arrayAdapter);
        authorNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                authorId=authors.get(position).id;
                authorName=authors.get(position).name;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onSave(View v){
        Book book=new Book();
        book.title=bookTitle.getText().toString();
        book.authorId=authorId;
        book.description= bookDesc.getText().toString();
        book.publishDate=bookPublishDate.getText().toString();

        Intent intent=new Intent();
        Bundle bundle=new Bundle();

        BookUser bookUser=new BookUser();
        bookUser.title=book.title;
        bookUser.authorId=authorId;
        bookUser.userName=authorName;
        bundle.putParcelable("bookUser",bookUser);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        dao.add(book);
        Toast.makeText(this, "save book success", Toast.LENGTH_SHORT).show();

        finish();
    }
}
