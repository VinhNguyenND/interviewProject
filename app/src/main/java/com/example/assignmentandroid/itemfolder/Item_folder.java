package com.example.assignmentandroid.itemfolder;

public class Item_folder {
    private  int id;
    private  String Name_folder;
    private  String Number_notes;



    public Item_folder(String name_folder) {
        Name_folder = name_folder;
    }

    public Item_folder(int id, String name_folder, String number_notes) {
        this.id = id;
        Name_folder = name_folder;
        Number_notes = number_notes;
    }

    public Item_folder(String name_folder, String number_notes) {
        Name_folder = name_folder;
        Number_notes = number_notes;
    }






    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_folder() {
        return Name_folder;
    }

    public void setName_folder(String name_folder) {
        Name_folder = name_folder;
    }

    public String getNumber_notes() {
        return Number_notes;
    }

    public void setNumber_notes(String number_notes) {
        Number_notes = number_notes;
    }

    @Override
    public String toString() {
        return "Item_folder{" +
                "id='" + id +
                ", Name_folder='" + Name_folder +
                ", Number_notes='" + Number_notes  +
                '}';
    }
}
