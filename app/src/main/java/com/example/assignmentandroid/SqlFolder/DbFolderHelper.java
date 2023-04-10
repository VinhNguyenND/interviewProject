package com.example.assignmentandroid.SqlFolder;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.assignmentandroid.MainActivity;
import com.example.assignmentandroid.itemfolder.Item_Note;
import com.example.assignmentandroid.itemfolder.Item_folder;

import java.util.ArrayList;
import java.util.List;

public class DbFolderHelper extends SQLiteOpenHelper {
    public final static String NAME_DATABASE ="APP_NOTE_APP";


    public   final static  String TABLE_NAME_Note="NAMENOTE";
    public  final  static  String ID_Note="IDNOTES";
    public final  static String  TITLE="TITLE";
    public  final  static  String  SUMMARY="SUMMARY";

    public  final static String CONTENT_NOTE="CONTENTNOTE";

    public   final static  String TABLE_NAME_FOLDER="FOLDER";
    public  final  static  String ID_FOLDER="IDFOLDER";
    public  final static String NameFolder="NAMEFOLDER";
    public  final  static  String NumberNotes="NUMBERNOTE";

    public  final  static  String Font="FONT";

    public  final  static  String ForeignKEY="FOREIGNER";


    public  final  static   int VERSION=1;
    public DbFolderHelper(@Nullable Context context) {
        super(context, NAME_DATABASE, null, VERSION);
    }



    String sqlNotes= "CREATE TABLE IF NOT EXISTS  "
            +TABLE_NAME_Note+
            "("
            +ID_Note+

            " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            +TITLE+

            " TEXT , "

            +SUMMARY

            +" TEXT, "

            + CONTENT_NOTE

            + " TEXT , "

            +ID_FOLDER +" INTEGER , "

            +"FOREIGN KEY " +  "("+ID_FOLDER +" )" +" REFERENCES " +TABLE_NAME_FOLDER+"("+ID_FOLDER+")"

            +")"
            ;
    String sqlFolder="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_FOLDER+"( "
            +ID_FOLDER+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NameFolder+ " TEXT, "+NumberNotes + " TEXT "+")";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlFolder);
        db.execSQL(sqlNotes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_FOLDER);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_Note);
        onCreate(db);

    }
    public List<Item_folder> getAllContact(){
        List<Item_folder> list=new ArrayList<>();
        String Query="SELECT * FROM "+TABLE_NAME_FOLDER;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(Query,null);

        if(cursor!=null){
            while (cursor.moveToNext()){
                Item_folder Folder=new Item_folder(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                list.add(Folder);
            }
        }
        return list;
    }
    public  void addContact(Item_folder Folder){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NameFolder,Folder.getName_folder());
        contentValues.put(NumberNotes,Folder.getNumber_notes());
        db.insert(TABLE_NAME_FOLDER,null,contentValues);
        db.close();
    }

    public  void update(int id,Item_folder Item_folder){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ID_FOLDER,Item_folder.getId());
        contentValues.put(NameFolder,Item_folder.getName_folder());
        contentValues.put(NumberNotes,Item_folder.getNumber_notes());
        db.update(TABLE_NAME_FOLDER,contentValues,ID_FOLDER +" =?",new String[]{String.valueOf(id)});
        db.close();
    }
    public  void Update(int id,Item_folder Item_folder){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NameFolder,Item_folder.getName_folder());
        db.update(TABLE_NAME_FOLDER,contentValues,ID_FOLDER +" = "+id,null);
        db.close();
    }

    public  void UpdateNumberNote(int id,Item_folder item_folder){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NumberNotes,item_folder.getNumber_notes());
        db.update(TABLE_NAME_FOLDER,contentValues,ID_FOLDER +" = "+id,null);
        db.close();
    }

    public void  delete(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME_FOLDER,ID_FOLDER +" = "+id,null);
        db.close();
    }
    public  void deleteNotesFolder(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        int res=db.delete(TABLE_NAME_Note,ID_FOLDER+" = "+id,null);
    }
    public  void UpdateNote(int id,Item_Note item_note){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TITLE,item_note.getTitle());
        contentValues.put(SUMMARY,item_note.getSummary());
        contentValues.put(CONTENT_NOTE,item_note.getContents());
        db.update(TABLE_NAME_Note,contentValues,ID_Note +" = " +id,null );
        db.close();
    };
    public  void AddNotes(Item_Note item_note){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TITLE,item_note.getTitle());
        contentValues.put(SUMMARY,item_note.getSummary());
        contentValues.put(CONTENT_NOTE,item_note.getContents());
        contentValues.put(ID_FOLDER,item_note.getId_folder());
        db.insert(TABLE_NAME_Note,null,contentValues);
        db.close();
    }
    public List<Item_Note> getAllNotes(){
        List<Item_Note> list=new ArrayList<>();
        String Query="SELECT * FROM "+TABLE_NAME_Note;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(Query,null);

        if(cursor!=null){
            while (cursor.moveToNext()){
                Item_Note Notes=new Item_Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
                list.add(Notes);
            }
        }
        if(cursor==null){
            list=null;
        }
        return list;
    }
    public List<Item_Note> getNotesFolder(int ID_FOLDER1){
        List<Item_Note> list=new ArrayList<>();
         SQLiteDatabase db=this.getWritableDatabase();
         String query="SELECT * FROM " +TABLE_NAME_Note+ " Where " + ID_FOLDER+" = "+ID_FOLDER1 ;
         Cursor cursor=db.rawQuery( query ,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                Item_Note Notes=new Item_Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
                list.add(Notes);
            }
        }
        return list;
    }
    public  void DeleteNote(int i){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME_Note,ID_Note +" = "+i,null);
        db.close();
    }

    @SuppressLint("Range")
    public Item_Note GetNoteById(int i){
        String Query=" SELECT * FROM  "+TABLE_NAME_Note +"  WHERE  "+ID_Note+ " = " +i;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(Query,null);
        Item_Note note =new Item_Note();
        if(cursor!=null){
            if(cursor.moveToFirst()){
               int id = cursor.getInt(cursor.getColumnIndex(ID_Note));
                String title = cursor.getString(cursor.getColumnIndex(TITLE));
               String summary = cursor.getString(cursor.getColumnIndex(SUMMARY));
               String  content = cursor.getString(cursor.getColumnIndex(CONTENT_NOTE));
               int id_folder=cursor.getInt(cursor.getColumnIndex(ID_FOLDER));
                 note=new Item_Note(id,title,summary,content,id_folder);
            }

        }
       return note;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
    }


}
