package com.example.assignmentandroid.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.assignmentandroid.MainActivity;
import com.example.assignmentandroid.R;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class layoutFolder extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_add,btn_cancel;
    EditText edtName;
    Context context=layoutFolder.this;
    int requestCode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_folder);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        requestCode=getIntent().getIntExtra("requestCode",0);
        if(requestCode==110){
            Bundle bundle=getIntent().getExtras();
            String s= bundle.getString("NameFolder");
            edtName.setText(s);
        }
        Bundle bundle=new Bundle();
        if(bundle!=null) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (requestCode == 100) {
                        Intent intent = new Intent(context, MainActivity.class);
                        String s = edtName.getText().toString();
                        bundle.putString("Name_folder", s);
                        bundle.putString("number_notes", "0 note");
                        intent.putExtras(bundle);
                        setResult(100, intent);
                    }
                    if (requestCode == 110) {
                        Intent intent = new Intent(context, MainActivity.class);
                        String s = edtName.getText().toString();
                        bundle.putString("Name_folder", s);
                        intent.putExtras(bundle);
                        setResult(110, intent);
                    }
                    finish();
                }


            });
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MainActivity.class);
                    bundle.putInt("id_Fragment",R.id.Folder);
                    intent.putExtras(bundle);
                    setResult(130, intent);
                    finish();
                }

            });
        }

        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public  void init(){
        btn_add=findViewById(R.id.btnOkFolder);
        btn_cancel=findViewById(R.id.btnCancelFolder);
        edtName=findViewById(R.id.edtNameFolder);
    }

}
