package com.example.projectandroid.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseAdapter {

    static DatabaseHelper dbHelper;
    public DatabaseAdapter(Context context){
        dbHelper= new DatabaseHelper(context);
    }

    public long addEntry(Phone_MessageDTO entry){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_PHONE , entry.getPhone());
        contentValues.put(DatabaseHelper.COL_MESSAGE,entry.getMessage());
       long id = db.insert(DatabaseHelper.TABLE_PHONE_MESSAGE,null,contentValues);
        return id;
    }

    public Phone_MessageDTO getEntry(){
        Phone_MessageDTO entry = null;
        Cursor c;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_PHONE ,DatabaseHelper.COL_MESSAGE};
        c = db.query(DatabaseHelper.TABLE_PHONE_MESSAGE,columns ,null,null,null,null,null);
        while(c.moveToNext()){
            entry = new Phone_MessageDTO(c.getString(0),c.getString(1));
        }
        return entry;
    }
    static class DatabaseHelper extends SQLiteOpenHelper{

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "PHONE_MESSAGE.db";
        private static final String TABLE_PHONE_MESSAGE = "PHONE_MESSAGE";
        private static final String COL_UID = "_id";
        private static final String COL_MESSAGE = "message";
        private static final String COL_PHONE = "phone";

        private static final String CREATE_PHONE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_PHONE_MESSAGE +
                " ("+ COL_UID + "INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PHONE + " TEXT, " + COL_MESSAGE + " TEXT );";


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_PHONE_MESSAGE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONE_MESSAGE );
onCreate(db);
        }
    }
}
