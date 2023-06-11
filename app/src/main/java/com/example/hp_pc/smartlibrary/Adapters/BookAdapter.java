package com.example.hp_pc.smartlibrary.Adapters;

/**
 * Created by dell on 4/1/2018.
 */


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pc.smartlibrary.Model.BookModel;
import com.example.hp_pc.smartlibrary.R;

import java.util.ArrayList;

/**
 * Created by admin on 18-01-2018.
 */
public class BookAdapter extends ArrayAdapter<BookModel> {

    private Context mcontext;
    ArrayList<BookModel> data=new ArrayList<BookModel>();
    Activity activity;
    int layoutResourceId;
    BookModel List;

    public BookAdapter(Activity fragment, int layoutResourceId, ArrayList<BookModel> data) {
        super(fragment, layoutResourceId, data);
        this.activity=fragment;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }
    public void addListItemToAdapter(java.util.List<BookModel> list)
        {
            data.addAll(list);
            this.notifyDataSetChanged();

        }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public BookModel getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row=view;
        PdfHolder holder=null;
        if(row==null)
        {
            LayoutInflater inflater= LayoutInflater.from(activity);
            row=inflater.inflate(layoutResourceId,viewGroup,false);
            holder=new PdfHolder();

        holder.book_name=(TextView) row.findViewById(R.id.book_name);
        holder.edition=(TextView)row.findViewById(R.id.edition);
        holder.path=(TextView)row.findViewById(R.id.path);
        holder.publication=(TextView)row.findViewById(R.id.publication);
        holder.emo_id=(TextView)row.findViewById(R.id.myemoid);
        holder.book_cat_id=(TextView)row.findViewById(R.id.book_cat_id);
        holder.book_id=(TextView)row.findViewById(R.id.book_id);
        holder.author=(TextView)row.findViewById(R.id.author);





            row.setTag(holder);
        }
        else
        {
            holder= (PdfHolder) row.getTag();
        }

        List = data.get(i);

        holder.book_name.setText(List.getBook_name());
        holder.author.setText(List.getAuthor());
        holder.book_id.setText(List.getBook_id());
        holder.book_cat_id.setText(List.getBook_cat_id());
        holder.emo_id.setText(List.getEmo_id());
        holder.publication.setText(List.getPublication());
        holder.edition.setText(List.getEdition());
        holder.path.setText(List.getPath());



        return row;
    }

    class PdfHolder
    {
        TextView book_name,author,book_id,book_cat_id,emo_id,publication,path,edition;
        ImageView image_url;
    }


}
