package com.example.assignmentandroid.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.motion.widget.Debug;

import com.example.assignmentandroid.Adapters.baseAdapterNote;
import com.example.assignmentandroid.MainActivity;
import com.example.assignmentandroid.R;
import com.example.assignmentandroid.SqlFolder.DbFolderHelper;
import com.example.assignmentandroid.itemfolder.Item_Note;
import com.example.assignmentandroid.itemfolder.Item_folder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemNoteScreen extends AppCompatActivity {
   FloatingActionButton floatingActionButton;

   int reQuestCode;
   ListView listView;
   int Select;
   static int id;
   Context context=ItemNoteScreen.this;
   baseAdapterNote baseAdapterForNote;

    List<Item_Note> notes;
    Toolbar toolbar;
   DbFolderHelper dbFolderHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_note_screen);
        init();
        Bundle  bundle;
        bundle=getIntent().getExtras();
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id=bundle.getInt("idFolder");

        dbFolderHelper=new DbFolderHelper(context);
        notes=dbFolderHelper.getNotesFolder(id);
        baseAdapterForNote=new baseAdapterNote(notes,context);
        listView.setAdapter(baseAdapterForNote);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   =new Intent(context,layoutNotes.class);
                reQuestCode=200;
                Bundle bundle1=new Bundle();
                bundle1.putInt("id",id);
                intent.putExtras(bundle1);
                startActivityForResult(intent,200);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Select=i;
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent  intent=new Intent(context,contentNote.class);
                Bundle bundle1 =new Bundle();
                bundle1.putInt("idFolder",id);
                bundle1.putInt("idNote",notes.get(i).getId());
                bundle1.putBoolean("isAllNote",false);
                reQuestCode=170;
                intent.putExtras(bundle1);
                startActivityForResult(intent,170);
            }
        });
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_item_notes,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle bundle=new Bundle();
        switch (item.getItemId()){
            case R.id.sortNoteIncrease:
                baseAdapterForNote.sort_NAME_Increase();
                break;
            case R.id.sort_decrease:
                baseAdapterForNote.sort_Name_down();
                break;

            case     android.R.id.home:
                Intent intent = new Intent(context, MainActivity.class);
                bundle.putInt("id_Fragment",R.id.Folder);
                intent.putExtras(bundle);
                setResult(130,intent);
                finish();
                break;
        }





        return super.onOptionsItemSelected(item);
    }

    public  void init(){
        floatingActionButton=findViewById(R.id.fBtn_add_itemNotes);
        listView=findViewById(R.id.list_item_notes);


    }

    public  List<Item_Note> getListNoteBySql(){
        List<Item_Note> item_folders=new ArrayList<>();
        dbFolderHelper.AddNotes(new Item_Note("Vinh","day la sumary 3","Nam Dinh",1));
        dbFolderHelper.AddNotes(new Item_Note("Nam","day la sumary 4","Nam Dinh",1));
        dbFolderHelper.AddNotes(new Item_Note("Vinh","day la sumary 5","Nam Dinh",1));
        dbFolderHelper.AddNotes(new Item_Note("Nam","day la sumary 6","Nam Dinh",1));
        dbFolderHelper.AddNotes(new Item_Note("Vinh","day la sumary 7","Nam Dinh",1));
        dbFolderHelper.AddNotes(new Item_Note("Nam","day la sumary 8","Nam Dinh",1));
        return  item_folders;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=new MenuInflater(ItemNoteScreen.this);
        inflater.inflate(R.menu.menu_foder,menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent=new Intent(context,layoutNotes.class);
        Item_Note item_note=dbFolderHelper.GetNoteById(notes.get(Select).getId());

        String title_repair=item_note.getTitle();
        String summary_repair=item_note.getSummary();
        String  content_repair=item_note.getContents();
        Bundle bundle1=new Bundle();
        bundle1.putInt("id",id);
        switch (item.getItemId()) {
            case R.id.idSuaFolder:
                reQuestCode = 210;
                bundle1.putString("title_repair", title_repair);
                bundle1.putString("summary_repair", summary_repair);
                bundle1.putString("content_repair", content_repair);
                bundle1.putBoolean("IsAllNote", false);
                intent.putExtras(bundle1);
                startActivityForResult(intent, 210);
                break;
            case R.id.IdXoaFolder:
                int numberNoteBegin =  notes.size();
                int idFolder=notes.get(Select).getId_folder();
                dbFolderHelper.DeleteNote( notes.get(Select).getId());
                baseAdapterForNote.delete(Select);
                numberNoteBegin-=1;
                notes=baseAdapterForNote.getListCurrent();
                dbFolderHelper.UpdateNumberNote(idFolder, new Item_folder(null, String.valueOf(numberNoteBegin) + " note "));
                break;
        }


        return super.onContextItemSelected(item);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bundle bundle=new Bundle();


        assert data != null;
        bundle=data.getExtras();
         int idFolder=bundle.getInt("idFolder");
         String title=bundle.getString("Title");
         String content=bundle.getString("Content");
         String summary=bundle.getString("Summary");
         if(requestCode==200&&resultCode==200){
             dbFolderHelper.AddNotes(new Item_Note(title,summary,content,idFolder));
             notes=dbFolderHelper.getNotesFolder(idFolder);
             baseAdapterForNote.replace(notes);
             List<Item_Note> item_notes=dbFolderHelper.getNotesFolder(id);
             int num =item_notes.size();
             dbFolderHelper.UpdateNumberNote(id,new Item_folder(null,String.valueOf(num) +" note "));
         }
        if(requestCode==210&&resultCode==210){
            Item_Note item_note=new Item_Note(title,summary,content,idFolder);
            int idFolder1=notes.get(Select).getId();
            dbFolderHelper.UpdateNote(idFolder1,item_note);
            List<Item_Note> list= dbFolderHelper.getAllNotes();
            notes=list;
            baseAdapterForNote.replace(notes);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
         intent.putExtra("reQuestCode",reQuestCode);
        super.startActivityForResult(intent, requestCode);
    }



}