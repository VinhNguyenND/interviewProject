package com.example.assignmentandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.assignmentandroid.Activity.ItemNoteScreen;
import  com.example.assignmentandroid.Fragments.*;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.assignmentandroid.SqlFolder.DbFolderHelper;
import com.example.assignmentandroid.itemfolder.Item_Note;
import com.example.assignmentandroid.itemfolder.Item_folder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements   BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    Context context=MainActivity.this;


    int id_Fragment =R.id.Note;
    fragmentListFolder fragmentListFolder;
    DbFolderHelper dbFolderHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         dbFolderHelper=new DbFolderHelper(context);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(id_Fragment);



        Bundle bundle=new Bundle();
        if(getIntent().getExtras()!=null){
            bundle=getIntent().getExtras();
            id_Fragment=bundle.getInt("id_Fragment");
        }




    }
     allNoteFragment firstFragment = new allNoteFragment(MainActivity.this);
    fragmentListFolder secondFragment = new fragmentListFolder(MainActivity.this);

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Note:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
                return true;

            case R.id.Folder:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, secondFragment).commit();
                return true;
        }
        return false;

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle=new Bundle();
        assert data != null;
        bundle=data.getExtras();
        String s=String.valueOf(requestCode)+" "+String.valueOf(resultCode);
        if(resultCode==130){
            id_Fragment=bundle.getInt("id_Fragment");
        }

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }

    }



}