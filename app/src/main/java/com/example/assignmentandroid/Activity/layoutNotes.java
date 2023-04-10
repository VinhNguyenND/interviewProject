package com.example.assignmentandroid.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.assignmentandroid.MainActivity;
import com.example.assignmentandroid.R;
import com.example.assignmentandroid.SqlFolder.DbFolderHelper;
import com.example.assignmentandroid.itemfolder.Item_Note;
import com.example.assignmentandroid.itemfolder.Item_folder;
import com.google.android.material.textfield.TextInputEditText;
import com.example.assignmentandroid.Adapters.*;

import java.util.List;
import java.util.Objects;

public class layoutNotes extends AppCompatActivity {

    Button btnOk,btnCancel;
    TextInputEditText title,summary,content;
    Context context=layoutNotes.this;

    DbFolderHelper dbFolderHelper=new DbFolderHelper(context);
    int reQuestCode;
    int idFolder;
    boolean isAllNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_layoutnotes);
                Bundle bundle=getIntent().getExtras();
                idFolder=bundle.getInt("id");
                reQuestCode=getIntent().getIntExtra("reQuestCode",0);
                isAllNote=getIntent().getExtras().getBoolean("IsAllNote");
                 init();
                 if(bundle.getInt("reQuestCode")==210){
                     title.setText(bundle.getString("title_repair"));
                     summary.setText((bundle).getString("summary_repair"));
                     content.setText(bundle.getString("content_repair"));
                 }
            btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title= Objects.requireNonNull(title.getText()).toString();
                String Summary=Objects.requireNonNull(summary.getText()).toString();
                String Content=Objects.requireNonNull(content.getText()).toString();
                Intent intent=new Intent(context,ItemNoteScreen.class);
                Bundle  bundle1=new Bundle();
                bundle1.putInt("idFolder",idFolder);
                bundle1.putString("Title",Title);
                bundle1.putString("Summary",Summary);
                bundle1.putString("Content",Content);
                intent.putExtras(bundle1);
                 if (reQuestCode==200) {

                     setResult(200,intent);
                 }
                  if (reQuestCode==210){
                      if(isAllNote){
                          Intent intent1=new Intent(context,MainActivity.class);
                          Bundle  bundle2=new Bundle();
                          bundle2.putString("Title",Title);
                          bundle2.putString("Summary",Summary);
                          bundle2.putString("Content",Content);
                          intent1.putExtras(bundle1);
                          setResult(220,intent1);
                      }
                      if(!isAllNote) {
                          Intent intent2=new Intent(context,ItemNoteScreen.class);
                          Bundle  bundle2=new Bundle();
                          bundle2.putInt("idFolder",idFolder);
                          bundle2.putString("Title",Title);
                          bundle2.putString("Summary",Summary);
                          bundle2.putString("Content",Content);
                          intent2.putExtras(bundle2);
                          setResult(210, intent2);
                      }
              }

              finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllNote){
                Intent intent=new Intent(context,ItemNoteScreen.class);
                Bundle  bundle1=new Bundle();
                bundle1.putInt("idFolder",idFolder);
                intent.putExtras(bundle1);
                setResult(125,intent);

                }else {
                    Intent intent=new Intent(context, MainActivity.class);
                    Bundle bundle1=new Bundle();
                    bundle1.putInt("id_Fragment",R.id.Note);
                    intent.putExtras(bundle1);
                    setResult(130,intent);
                }
                finish();
            }
        });
    }




    public  void init(){
        btnCancel=findViewById(R.id.btnCancelNote);
        btnOk=findViewById(R.id.btnOkNote);
        title=findViewById(R.id.idTitleOfNote);
        summary=findViewById(R.id.idSummary);
        content=findViewById(R.id.idContentNote);
    }
}