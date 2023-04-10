package com.example.assignmentandroid.itemfolder;

public class Item_Note {
    private  int id;
    private  String title;
    private  String summary;
    private  String contents;
    private  int id_folder;



    public Item_Note() {

    }


    public Item_Note(String title, String summary, String contents, int id_folder) {
        this.title = title;
        this.summary = summary;
        this.contents = contents;
        this.id_folder = id_folder;
    }

    public Item_Note(String title, String summary, String contents) {
        this.title = title;
        this.summary = summary;
        this.contents = contents;
    }

    public Item_Note(int id, String title, String summary, String contents, int id_folder) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.contents = contents;
        this.id_folder = id_folder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getId_folder() {
        return id_folder;
    }

    public void setId_folder(int id_folder) {
        this.id_folder = id_folder;
    }

    @Override
    public String toString() {
        return "Item_Note{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", id_folder='" + id_folder + '\'' +
                '}';
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
