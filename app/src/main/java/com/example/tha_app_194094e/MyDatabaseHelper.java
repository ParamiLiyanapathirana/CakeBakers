package com.example.tha_app_194094e;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.autofill.AutofillId;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME="THA_DB.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="Item_Infor";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_DESCRIPTION="description";
    private static final String COLUMN_PRICE="price";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+ TABLE_NAME +
                " (" +  COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, "+
                COLUMN_DESCRIPTION + " TEXT, "+
                COLUMN_PRICE + " FLOAT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void add(String name, String description, float price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DESCRIPTION,description);
        cv.put(COLUMN_PRICE,price);

        long result= db.insert(TABLE_NAME, null, cv);
        if (result==-1)
        {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAllData()
    {
        String query= "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null)
        {
            cursor=db.rawQuery(query,null);

        }
        return cursor;
    }

    void updatedata(String row_id, String name, String description, String price)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_PRICE,price);



        long result = DB.update(TABLE_NAME, contentValues, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }


    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{(row_id)});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
