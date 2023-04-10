package com.example.assignmentandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.assignmentandroid.Adapters.baseAdapterNote;
import com.example.assignmentandroid.MainActivity;
import com.example.assignmentandroid.R;
import com.example.assignmentandroid.SqlFolder.DbFolderHelper;
import com.example.assignmentandroid.itemfolder.Item_Note;

import java.util.ArrayList;
import java.util.List;

public class SearchNote extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView searchView;
    ImageView btn_back;

    ListView list;


    baseAdapterNote baseAdapter_search_Note;
    Context context=SearchNote.this;

    List<Item_Note> item_notes=new ArrayList<>();

    DbFolderHelper dbFolderHelper;


    static int idFolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_note);
        dbFolderHelper=new DbFolderHelper(context);
        init();
        item_notes=dbFolderHelper.getAllNotes();
        baseAdapter_search_Note=new baseAdapterNote(item_notes,context);
        list.setAdapter(baseAdapter_search_Note);
        searchView.setOnQueryTextListener(SearchNote.this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("id_Fragment",R.id.Note);
                setResult(130,intent);
                finish();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(context,contentNote.class);
                int id=baseAdapter_search_Note.getItemFolderCurrent().get(i).getId();
                Bundle bundle=new Bundle();
                bundle.putInt("idNote",id);
                intent.putExtras(bundle);
                startActivityForResult(intent,11);
            }
        });
    }
    public  void init(){
        searchView=findViewById(R.id.SearchViewOfAllNote);
        btn_back=findViewById(R.id.btnBackOfSearchAllNote);
        list =findViewById(R.id.listViewAllNote);
    }
    @Override
    public boolean onQueryTextSubmit(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        baseAdapter_search_Note.filter(text);
        return false;
    }
}