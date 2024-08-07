package com.nickolas.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
        private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "my_product";
    private static final String COLUMN_ID = "_id";
    private static final String PRODUCT_CODE = "product_code";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_PRICE = "product_price";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_CODE + " TEXT, " +
                PRODUCT_NAME + " TEXT, " +
                PRODUCT_PRICE + " INTEGER); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addProduct(String code, String name, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PRODUCT_CODE, code);
        cv.put(PRODUCT_NAME, name);
        cv.put(PRODUCT_PRICE, price);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String code, String name, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_CODE, code);
        cv.put(PRODUCT_NAME, name);
        cv.put(PRODUCT_PRICE, price);

        long result = db.update(TABLE_NAME, cv, "_id = ?", new String[]{row_id});
        if ( result == -1 ) {
            Toast.makeText(context, "Failed to update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully updated!.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
