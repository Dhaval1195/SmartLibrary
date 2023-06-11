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


import com.example.hp_pc.smartlibrary.Model.EmotionModel;
import com.example.hp_pc.smartlibrary.R;

import java.util.ArrayList;

/**
 * Created by admin on 18-01-2018.
 */
public class EmotionAdapter extends ArrayAdapter<EmotionModel> {

    private Context mcontext;
    ArrayList<EmotionModel> data=new ArrayList<EmotionModel>();
    Activity activity;
    int layoutResourceId;
    EmotionModel List;

    public EmotionAdapter(Activity fragment, int layoutResourceId, ArrayList<EmotionModel> data) {
        super(fragment, layoutResourceId, data);
        this.activity=fragment;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }
    public void addListItemToAdapter(java.util.List<EmotionModel> list)
        {
            data.addAll(list);
            this.notifyDataSetChanged();

        }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public EmotionModel getItem(int i) {
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

        holder.emoid=(TextView) row.findViewById(R.id.emoo_id);
        holder.emoname=(TextView)row.findViewById(R.id.emoo_name);





            row.setTag(holder);
        }
        else
        {
            holder= (PdfHolder) row.getTag();
        }

        List = data.get(i);

        holder.emoid.setText(List.getEmo_id());
        holder.emoname.setText(List.getEmo_name());



        return row;
    }

    class PdfHolder
    {
        TextView emoid,emoname,campaign,affiliate_url,category;
        ImageView image_url;
    }


}
