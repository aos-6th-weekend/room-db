package com.example.rathana.roompersistence;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rathana.roompersistence.data.entity.Book;
import com.example.rathana.roompersistence.data.entity.BookUser;
import com.example.rathana.roompersistence.data.entity.User;
import com.example.rathana.roompersistence.data.entity.UserBooks;

import java.util.List;

import javax.security.auth.login.LoginException;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    List<BookUser> bookUsers;
    public BookAdapter(List<BookUser> bookUsers) {
        this.bookUsers=bookUsers;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BookUser bookUser=bookUsers.get(i);

        viewHolder.authorName.setText(bookUser.userName);
        viewHolder.title.setText(bookUser.book.title);
        viewHolder.publishDate.setText(bookUser.book.publishDate);
        viewHolder.thumb.setImageResource(R.drawable.panda);

        /*for(User u : users){
            if(u.id==book.authorId){
                viewHolder.authorName.setText(u.name);
                viewHolder.title.setText(book.title);
                viewHolder.publishDate.setText(book.publishDate);
                viewHolder.thumb.setImageResource(R.drawable.panda);
            }

        }*/

    }

    @Override
    public int getItemCount() {
        return bookUsers.size();
    }

    public void setBookUser(List<BookUser> bookUsers) {
        this.bookUsers.addAll(bookUsers);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView thumb;
        TextView title,authorName,publishDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb=itemView.findViewById(R.id.thumb);
            title=itemView.findViewById(R.id.title);
            authorName=itemView.findViewById(R.id.authorName);
            publishDate=itemView.findViewById(R.id.publishDate);
        }
    }

}
