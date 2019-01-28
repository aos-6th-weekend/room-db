package com.example.rathana.roompersistence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rathana.roompersistence.data.BookDatabase;
import com.example.rathana.roompersistence.data.dao.UserDao;
import com.example.rathana.roompersistence.data.entity.User;

public class AddNewAuthorActivity extends AppCompatActivity {

    EditText name,pass, email;
    UserDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_author);

        name=findViewById(R.id.name);
        pass=findViewById(R.id.password);
        email=findViewById(R.id.email);

        dao=BookDatabase.getInstance(this).userDao();

    }

    public void onSave(View view) {
        User user=new User();
        user.name=name.getText().toString();
        user.email=email.getText().toString();
        user.password=pass.getText().toString();

        dao.add(user);
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }


    public void onGetUser(View view) {

        Log.e("User",""+dao.getUsers().toString());
    }
}
