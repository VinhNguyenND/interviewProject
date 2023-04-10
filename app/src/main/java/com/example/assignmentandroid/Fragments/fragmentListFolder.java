package com.example.assignmentandroid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.assignmentandroid.Activity.ItemNoteScreen;
import com.example.assignmentandroid.Activity.layoutFolder;
import com.example.assignmentandroid.Adapters.baseAdapter;
import com.example.assignmentandroid.R;
import com.example.assignmentandroid.SqlFolder.DbFolderHelper;
import com.example.assignmentandroid.itemfolder.Item_Note;
import com.example.assignmentandroid.itemfolder.Item_folder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class fragmentListFolder extends Fragment {
   View  view;
    ListView listView;
    ConstraintLayout btnAdd;
    Context context;

    baseAdapter BaseAdapter_folder ;
    List<Item_folder> it;
    int selectItemId;
    boolean a;
    DbFolderHelper dbFolderHelper;
    public  fragmentListFolder(Context context){
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.listfolder, container, false);
        listView= view.findViewById(R.id.list_view_folder);
        btnAdd= view.findViewById(R.id.plus_button);
        dbFolderHelper=new DbFolderHelper(getActivity());
        it=dbFolderHelper.getAllContact();
        BaseAdapter_folder = new baseAdapter(it,context);
        listView.setAdapter(BaseAdapter_folder);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, layoutFolder.class);
                startActivityForResult(intent,100);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectItemId=i;
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(context, ItemNoteScreen.class);
                int id=it.get(i).getId();
                Bundle bundle=new Bundle();
                bundle.putInt("idFolder",it.get(i).getId());
                intent.putExtras(bundle);
                startActivityForResult(intent,11);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(getActivity());
        inflater.inflate(R.menu.menu_foder,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent=new Intent(context,layoutFolder.class);
        switch (item.getItemId()) {
            case R.id.IdXoaFolder:
                dbFolderHelper.delete(it.get(selectItemId).getId());
                dbFolderHelper.deleteNotesFolder(it.get(selectItemId).getId());
                it = dbFolderHelper.getAllContact();
                BaseAdapter_folder.replace(it);
                break;
            case R.id.idSuaFolder:
                Bundle bundle=new Bundle();
                bundle.putString("NameFolder",it.get(selectItemId).getName_folder());
                intent.putExtras(bundle);
                startActivityForResult(intent,110);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        intent.putExtra("requestCode",requestCode);
        super.startActivityForResult(intent, requestCode);
    }


    public List<Item_folder> setList(){

        List<Item_folder> item_folders=new ArrayList<>();
        item_folders.add(new Item_folder(1,"tieu de 1","12"));
        item_folders.add(new Item_folder(2,"tieu de 2","12"));
        item_folders.add(new  Item_folder(3,"tieu de 3","12"));
        item_folders.add(new Item_folder(4,"tieu de 4","12"));
        item_folders.add(new Item_folder(5,"tieu de 5","12"));
        item_folders.add(new Item_folder(6,"tieu de 6","12"));
        item_folders.add(new Item_folder(7,"tieu de 7","12"));
        return  item_folders;
    }
    public  void getListBySql(){
        List<Item_folder> item_folders=new ArrayList<>();
        dbFolderHelper.addContact(new Item_folder(1,"tieu de 1","12"));
        dbFolderHelper.addContact(new Item_folder(2,"tieu de 2","12"));
        dbFolderHelper.addContact(new Item_folder(3,"tieu de 3","12"));
        dbFolderHelper.addContact(new Item_folder(4,"tieu de 4","12"));
        dbFolderHelper.addContact(new Item_folder(5,"tieu de 5","12"));
        dbFolderHelper.addContact(new Item_folder(6,"tieu de 6","12"));
        dbFolderHelper.addContact(new Item_folder(7,"tieu de 7","12"));
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        Bundle bundle=data.getExtras();
        if(requestCode==100&&resultCode==100){
            String resultForRequest=bundle.getString("Name_folder");
            String numberNotes=bundle.getString("number_notes");
            dbFolderHelper.addContact(new Item_folder(resultForRequest,numberNotes));
            it=dbFolderHelper.getAllContact();
            BaseAdapter_folder.replace(it);
        }
        if(requestCode==110&&resultCode==110){
            String resultForRequest=bundle.getString("Name_folder");
            dbFolderHelper.Update(it.get(selectItemId).getId(),new Item_folder(resultForRequest));
            it=dbFolderHelper.getAllContact();
            BaseAdapter_folder.replace(it);
        }
        if(resultCode==130){
            it=dbFolderHelper.getAllContact();
            BaseAdapter_folder.replace(it);
        }
    }
}
