package com.example.rathana.roompersistence;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rathana.roompersistence.data.BookDatabase;
import com.example.rathana.roompersistence.data.dao.BookDao;
import com.example.rathana.roompersistence.data.entity.Book;

public class AddNewBookActivity extends AppCompatActivity {

    EditText bookTitle, bookDesc,bookPublishDate,authorId;

    BookDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        bookDesc=findViewById(R.id.description);
        bookPublishDate=findViewById(R.id.publishDate);
        bookTitle=findViewById(R.id.bookTitle);
        authorId=findViewById(R.id.authorId);

        dao=BookDatabase.getInstance(this).bookDao();

    }

    public void onSave(View v){
        Book book=new Book();
        book.title=bookTitle.getText().toString();
        book.authorId=Integer.parseInt(authorId.getText().toString());
        book.description= bookDesc.getText().toString();
        book.publishDate=bookPublishDate.getText().toString();
        book.thumbnail=BitmapFactory.decodeResource(getResources(),R.drawable.panda);
        dao.add(book);
        Toast.makeText(this, "save book success", Toast.LENGTH_SHORT).show();
    }
}
