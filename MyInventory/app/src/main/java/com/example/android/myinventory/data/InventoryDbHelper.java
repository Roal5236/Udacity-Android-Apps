package com.example.android.myinventory.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rohaan on 10-Dec-17.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "inventory.db";
    public final static int DB_VERSION = 1;
    public final static String LOG_TAG = InventoryDbHelper.class.getCanonicalName();

    public InventoryDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InventoryContract.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem(InventoryItems item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.COLUMN_NAME, item.getProductName());
        values.put(InventoryContract.COLUMN_PRICE, item.getPrice());
        values.put(InventoryContract.COLUMN_QUANTITY, item.getQuantity());
        values.put(InventoryContract.COLUMN_SUPPLIER_NAME, item.getSupplierName());
        values.put(InventoryContract.COLUMN_SUPPLIER_PHONE, item.getSupplierPhone());
        values.put(InventoryContract.COLUMN_SUPPLIER_EMAIL, item.getSupplierEmail());
        values.put(InventoryContract.COLUMN_IMAGE, item.getImage());
        long id = db.insert(InventoryContract.TABLE_NAME, null, values);
    }

    public Cursor readItems() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventoryContract._ID,
                InventoryContract.COLUMN_NAME,
                InventoryContract.COLUMN_PRICE,
                InventoryContract.COLUMN_QUANTITY,
                InventoryContract.COLUMN_SUPPLIER_NAME,
                InventoryContract.COLUMN_SUPPLIER_PHONE,
                InventoryContract.COLUMN_SUPPLIER_EMAIL,
                InventoryContract.COLUMN_IMAGE
        };
        Cursor cursor = db.query(
                InventoryContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventoryContract._ID,
                InventoryContract.COLUMN_NAME,
                InventoryContract.COLUMN_PRICE,
                InventoryContract.COLUMN_QUANTITY,
                InventoryContract.COLUMN_SUPPLIER_NAME,
                InventoryContract.COLUMN_SUPPLIER_PHONE,
                InventoryContract.COLUMN_SUPPLIER_EMAIL,
                InventoryContract.COLUMN_IMAGE
        };
        String selection = InventoryContract._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };

        Cursor cursor = db.query(
                InventoryContract.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.COLUMN_QUANTITY, quantity);
        String selection = InventoryContract._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(currentItemId) };
        db.update(InventoryContract.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void sellOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity -1;
        }
        ContentValues values = new ContentValues();
        values.put(InventoryContract.COLUMN_QUANTITY, newQuantity);
        String selection = InventoryContract._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };
        db.update(InventoryContract.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
