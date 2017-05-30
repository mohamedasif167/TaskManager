package com.example.a15017498.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15017498 on 30/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "P06PSTASK1.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTE = "taskP06";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESC = "desc";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT ,"+ COLUMN_DESC+" TEXT ) ";
        Log.d("DB",createNoteTableSql);
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("ALTER TABLE " + TABLE_NOTE + " ADD COLUMN module_name1 TEXT ");
        onCreate(db);

    }

    //Adding
    public long insertNote(String name,String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_DESC,desc);
        long result = db.insert(TABLE_NOTE, null, values);
        db.close();
        Log.d("SQL Insert",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    //Getting all the data
    public ArrayList<Task> getAllNotes() {
        ArrayList<Task> notes = new ArrayList<Task>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + ","+COLUMN_DESC+ " FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                Task task = new Task(id,name,desc);
                notes.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    //Delete
    public int deleteTask(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NOTE, condition, args);
        Log.d("DELETE",result+"");
        db.close();
        return result;
    }

}
