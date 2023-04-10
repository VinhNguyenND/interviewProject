package com.example.assignmentandroid.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.assignmentandroid.MainActivity;
import com.example.assignmentandroid.R;
import com.example.assignmentandroid.SqlFolder.DbFolderHelper;
import com.example.assignmentandroid.itemfolder.Item_Note;

import java.util.Objects;

public class contentNote extends AppCompatActivity implements KeyEvent.Callback{

    int idFolder;
    Context context=contentNote.this;
    int idNote;
    TextView Title,Content;
    DbFolderHelper dbFolderHelper;

    int requestCode;
    Toolbar toolbar;
    boolean isAllNote;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         dbFolderHelper=new DbFolderHelper(context);
        setContentView(R.layout.activity_content_note);
        init();
        Bundle bundle1=new Bundle();
        bundle1=getIntent().getExtras();
        isAllNote =bundle1.getBoolean("isAllNote");
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle;
        bundle=getIntent().getExtras();
        idFolder=bundle.getInt("idFolder",0);
        idNote=bundle.getInt("idNote",0);
        Item_Note item_note=dbFolderHelper.GetNoteById(idNote);
        String title=item_note.getTitle();
        String content=item_note.getContents();
        Title.setText(title);
        Content.setText(content);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle bundle1=new Bundle();
        switch (item.getItemId() ) {
            case android.R.id.home:
                if (!(isAllNote)) {
                    Intent intent = new Intent(context, ItemNoteScreen.class);
                    bundle1.putInt("idFolder", idFolder);
                    intent.putExtras(bundle1);
                    setResult(170, intent);
                } else {
                    Intent intent1 = new Intent(context, MainActivity.class);
                    bundle1.putInt("id_Fragment", R.id.Note);
                    intent1.putExtras(bundle1);
                    setResult(175, intent1);
                }
                finish();
                break;


        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_content,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public  void init(){
        Title=findViewById(R.id.idTitleContent);
        Content=findViewById(R.id.idContentNote);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}