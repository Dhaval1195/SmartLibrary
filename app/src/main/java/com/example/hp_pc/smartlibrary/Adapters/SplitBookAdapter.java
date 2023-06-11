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

import com.example.hp_pc.smartlibrary.Model.BookSplitModel;
import com.example.hp_pc.smartlibrary.R;

import java.util.ArrayList;

/**
 * Created by admin on 18-01-2018.
 */
public class SplitBookAdapter extends ArrayAdapter<BookSplitModel> {

    private Context mcontext;
    ArrayList<BookSplitModel> data=new ArrayList<BookSplitModel>();
    Activity activity;
    int layoutResourceId;
    BookSplitModel List;

    public SplitBookAdapter(Activity fragment, int layoutResourceId, ArrayList<BookSplitModel> data) {
        super(fragment, layoutResourceId, data);
        this.activity=fragment;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }
    public void addListItemToAdapter(java.util.List<BookSplitModel> list)
        {
            data.addAll(list);
            this.notifyDataSetChanged();

        }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public BookSplitModel getItem(int i) {
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

        holder.booksplitid=(TextView) row.findViewById(R.id.book_split_id);
        holder.booksplitname=(TextView)row.findViewById(R.id.book_split_name);
        holder.bookid=(TextView)row.findViewById(R.id.sbook_id);
        holder.path=(TextView)row.findViewById(R.id.spath);





            row.setTag(holder);
        }
        else
        {
            holder= (PdfHolder) row.getTag();
        }

        List = data.get(i);

        holder.booksplitid.setText(List.getBook_split_id());
        holder.booksplitname.setText(List.getBook_split_name());
        holder.bookid.setText(List.getBook_id());
        holder.path.setText(List.getPath());



        return row;
    }

    class PdfHolder
    {
        TextView booksplitid,booksplitname,bookid,path;
        ImageView image_url;
    }


}
