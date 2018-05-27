package com.example.android.myinventory.data;

import android.provider.BaseColumns;

/**
 * Created by Rohaan on 10-Dec-17.
 */
public class InventoryContract implements BaseColumns {

    public InventoryContract() {
        //Empty Constructor
    }
        public static final String TABLE_NAME = "stock";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
        public static final String COLUMN_IMAGE = "image";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                InventoryContract.TABLE_NAME + "(" +
                InventoryContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                InventoryContract.COLUMN_NAME + " TEXT NOT NULL," +
                InventoryContract.COLUMN_PRICE + " TEXT NOT NULL," +
                InventoryContract.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                InventoryContract.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                InventoryContract.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL," +
                InventoryContract.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL," +
                InventoryContract.COLUMN_IMAGE + " TEXT NOT NULL" + ");";
    
}

