package com.example.assignmentandroid.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.itemfolder.Item_folder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class baseAdapter extends BaseAdapter  {
    List<Item_folder> item_folders;
    Context mContext;
    LayoutInflater inflater;

    private List<Item_folder> databackup;
    private ArrayList<Item_folder> arraylist;


    public baseAdapter(List<Item_folder> item_folders, Context mContext) {
        this.item_folders = item_folders;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Item_folder>();
        this.arraylist.addAll(item_folders);
        notifyDataSetChanged();
    }

    public void replace(List<Item_folder> item_folders) {
        this.item_folders = item_folders;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = 0;
        if (item_folders != null) {
            return item_folders.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return item_folders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_folder, viewGroup, false);
        TextView idNumber = view.findViewById(R.id.id_number);
        TextView NameFolder = view.findViewById(R.id.name_Folder);
        TextView notes_count = view.findViewById(R.id.number_Notes);
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(),"Font/RobotoCondensed-Regular.ttf");
         idNumber.setTypeface(tf);
         notes_count.setTypeface(tf);
         notes_count.setTypeface(tf);
        Item_folder item_folder = item_folders.get(i);
        if (item_folder == null) {
            return null;
        }
        idNumber.setText(String.valueOf(i));
        NameFolder.setText(item_folder.getName_folder());
        notes_count.setText(item_folder.getNumber_notes());
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        item_folders.clear();
        if (charText.length() == 0) {
            item_folders.addAll(arraylist);
        } else {
            for (Item_folder wp : arraylist) {
                if (wp.getName_folder().toLowerCase(Locale.getDefault()).contains(charText)) {
                    item_folders.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}



