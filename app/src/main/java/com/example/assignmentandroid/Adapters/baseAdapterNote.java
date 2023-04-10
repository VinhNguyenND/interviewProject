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
import com.example.assignmentandroid.itemfolder.Item_Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class baseAdapterNote extends BaseAdapter {
    List<Item_Note> notes;
    LayoutInflater inflater;
    Context context;

    private ArrayList<Item_Note> arraylist;


    public baseAdapterNote(List<Item_Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
        this.arraylist = new ArrayList<Item_Note>();
        this.arraylist.addAll(notes);
        inflater=LayoutInflater.from(context);
        notifyDataSetChanged();
    }
    public  void Add(Item_Note item_note){
        this.notes.add(item_note);
        notifyDataSetChanged();
    }
    public  void sort_Name_down(){
        Collections.sort(notes, new Comparator<Item_Note>() {
            @Override
            public int compare(Item_Note t1, Item_Note t2) {
                return t2.getTitle().compareToIgnoreCase(t1.getTitle());
            }

        });
        notifyDataSetChanged();
    }

    public void sort_NAME_Increase(){
        Collections.sort(notes, new Comparator<Item_Note>() {
            @Override
            public int compare(Item_Note t1, Item_Note t2) {
                return t1.getTitle().compareToIgnoreCase(t2.getTitle());
            }
        });
        notifyDataSetChanged();
    }
    public void replace(List<Item_Note> item_notes) {
        this.notes = item_notes;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        int count=0;
         if(notes!=null){
             count=notes.size();
         }
        return count;
    }
    public  void AddNote(Item_Note item_note){
        this.notes.add(item_note);
        notifyDataSetChanged();
    }

  public  void  delete(int id){
        this.notes.remove(id);
        notifyDataSetChanged();
  }

  public  List<Item_Note> getListCurrent(){
        return this.notes;
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_note, viewGroup, false);
        Item_Note item_Note=notes.get(i);
        TextView title=view.findViewById(R.id.titleForNote);
        TextView SUMMARY=view.findViewById(R.id.SUMMARYFORCONTENT);
        title.setTextColor(context.getResources().getColor(R.color.black));
        SUMMARY.setTextColor(context.getResources().getColor(R.color.black));
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"Font/RobotoCondensed-Regular.ttf");
        title.setTypeface(tf);
        SUMMARY.setTypeface(tf);
        if(item_Note==null){
            return null ;
        }

        title.setText(item_Note.getTitle());
        SUMMARY.setText(item_Note.getSummary());

        return view;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        notes.clear();
        if (charText.length() == 0) {
            notes.addAll(arraylist);
        } else {
            for (Item_Note wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    notes.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    public  List<Item_Note> getItemFolderCurrent(){
        return  this.notes;
    }
}
