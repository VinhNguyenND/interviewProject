package com.example.assignmentandroid.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

//androidx.appcompat.widget.Toolbar cannot be cast to android.widget.Toolbar

import com.example.assignmentandroid.Activity.SearchNote;
import com.example.assignmentandroid.Activity.contentNote;
import com.example.assignmentandroid.MainActivity;
import com.example.assignmentandroid.R;
import com.example.assignmentandroid.SqlFolder.DbFolderHelper;
import com.example.assignmentandroid.itemfolder.Item_Note;
import  com.example.assignmentandroid.Adapters.*;
import com.example.assignmentandroid.Activity.*;
import com.example.assignmentandroid.itemfolder.Item_folder;

import java.util.ArrayList;
import java.util.List;


public class allNoteFragment extends Fragment  implements  View.OnClickListener  {
    Toolbar toolbar;
    ListView listView;

    Context context=getActivity();

    DbFolderHelper dbFolderHelper;

    List<Item_Note>item_notes;

   int reQuestCode;
    baseAdapterNote baseAdapterNote;



    boolean is;

    int Select;
    public allNoteFragment(Context context) {
       this.context=context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.all_note, container, false);
        toolbar =view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        listView=view.findViewById(R.id.listViewOfAllNote);

         dbFolderHelper=new DbFolderHelper(getActivity());
         item_notes= dbFolderHelper.getAllNotes();
         baseAdapterNote=new baseAdapterNote(item_notes,context);
        listView.setAdapter(baseAdapterNote);
        toolbar.setNavigationIcon(R.drawable.icons8_notes_36);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent  intent=new Intent(context, contentNote.class);
                Bundle bundle1 =new Bundle();
                bundle1.putInt("idNote",item_notes.get(i).getId());
                bundle1.putBoolean("isAllNote",true);
                intent.putExtras(bundle1);
                reQuestCode=175;
                startActivityForResult(intent,reQuestCode);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Select=i;
                return false;
            }
        });
        return view;
    };

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                break;
            case R.id.search_barNote:
               Intent intent=new Intent(context, SearchNote.class);
               startActivity(intent);
            case R.id.sort_increase:
                baseAdapterNote.sort_NAME_Increase();
                break;
            case R.id.sort_decrease:
                baseAdapterNote.sort_Name_down();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent=new Intent(context,layoutNotes.class);
        Item_Note item_note=dbFolderHelper.GetNoteById(item_notes.get(Select).getId());
        String title_repair=item_note.getTitle();
        String summary_repair=item_note.getSummary();
        String  content_repair=item_note.getContents();
        Bundle bundle1=new Bundle();

            switch (item.getItemId()) {
                case R.id.idSuaFolder:
                    reQuestCode = 210;
                    bundle1.putString("title_repair", title_repair);
                    bundle1.putString("summary_repair", summary_repair);
                    bundle1.putString("content_repair", content_repair);
                    bundle1.putBoolean("IsAllNote", true);
                    intent.putExtras(bundle1);
                    startActivityForResult(intent, 210);
                    break;
                case R.id.IdXoaFolder:

                      int numberNoteBegin =  item_notes.size();
                      int idFolder=item_notes.get(Select).getId_folder();
                      dbFolderHelper.DeleteNote( item_notes.get(Select).getId());
                      baseAdapterNote.delete(Select);
                      numberNoteBegin-=1;
                      item_notes=baseAdapterNote.getListCurrent();
                      dbFolderHelper.UpdateNumberNote(idFolder, new Item_folder(null, String.valueOf(numberNoteBegin) + " note "));
            }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(getActivity());
        inflater.inflate(R.menu.menu_foder,menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerForContextMenu(listView);
    }

    public List<Item_Note> getNotes(){
      List<Item_Note> item_notes=new ArrayList<>();
       item_notes.add(new Item_Note("not","summary","content",1));
      item_notes.add(new Item_Note("not1","summary","content",1));
      item_notes.add(new Item_Note("not2","summary","content",1));
      item_notes.add(new Item_Note("not3","summary","content",1));
       return item_notes;
  }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bundle bundle=new Bundle();
        assert data != null;
        bundle=data.getExtras();
        if(resultCode==220){
            String tittle=bundle.getString("Title");
            String Summary=bundle.getString("Summary");
            String Content=bundle.getString("Content");
            dbFolderHelper.UpdateNote(item_notes.get(Select).getId(),new Item_Note(tittle,Summary,Content));
            List<Item_Note> list=dbFolderHelper.getAllNotes();
            baseAdapterNote.replace(list);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivity(Intent intent) {
        intent.putExtra("reQuestCode",reQuestCode);
        super.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra("reQuestCode",reQuestCode);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View view) {

    }
}
