package com.example.android.myinventory;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.myinventory.data.InventoryContract;

/**
 * Created by Rohaan on 10-Dec-17.
 */

public class InventoryCursorAdapter extends CursorAdapter {


    private final MainActivity activity;

    public InventoryCursorAdapter(MainActivity context, Cursor c) {
        super(context, c, 0);
        this.activity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.product_name);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        ImageView sale = (ImageView) view.findViewById(R.id.sale);
        ImageView image = (ImageView) view.findViewById(R.id.image_view);

        String name = cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(InventoryContract.COLUMN_QUANTITY));
        String price = cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_PRICE));

        image.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_IMAGE))));

        nameTextView.setText(name);
        quantityTextView.setText(String.valueOf(quantity));
        priceTextView.setText(price);

        final long id = cursor.getLong(cursor.getColumnIndex(InventoryContract._ID));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnViewItem(id);
            }
        });

        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnSale(id,
                        quantity);
            }
        });
    }
}
