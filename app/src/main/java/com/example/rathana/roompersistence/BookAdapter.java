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

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    OnBookListener listener;
    List<BookUser> bookUsers;


    public BookAdapter(List<BookUser> bookUsers , OnBookListener listener) {
        this.bookUsers=bookUsers;
        this.listener=listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final BookUser bookUser=bookUsers.get(i);

        viewHolder.authorName.setText(bookUser.userName);
        viewHolder.title.setText(bookUser.title);
        viewHolder.publishDate.setText(bookUser.publishDate);
        viewHolder.thumb.setImageResource(R.drawable.panda);

        /*for(User u : users){
            if(u.id==book.authorId){
                viewHolder.authorName.setText(u.name);
                viewHolder.title.setText(book.title);
                viewHolder.publishDate.setText(book.publishDate);
                viewHolder.thumb.setImageResource(R.drawable.panda);
            }

        }*/
        viewHolder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(bookUser,viewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookUsers.size();
    }

    public void setBookUsers(List<BookUser> bookUsers) {
        this.bookUsers.addAll(bookUsers);
        notifyDataSetChanged();
    }

    public void setBookUser(BookUser bookUser) {
        this.bookUsers.add(0,bookUser);
        notifyItemInserted(0);
    }
    public void setBook(Book book) {

    }

    public void onDelete(BookUser bookUser,int pos) {

        this.bookUsers.remove(bookUser);
        notifyItemRemoved(pos);

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView thumb,btnDel;
        TextView title,authorName,publishDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb=itemView.findViewById(R.id.thumb);
            title=itemView.findViewById(R.id.title);
            authorName=itemView.findViewById(R.id.authorName);
            publishDate=itemView.findViewById(R.id.publishDate);
            btnDel=itemView.findViewById(R.id.btnDel);
        }
    }

    public interface  OnBookListener{
        void onDelete(BookUser bookUser, int pos);
    }
}
