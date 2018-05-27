package com.example.android.myinventory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.android.myinventory.data.InventoryDbHelper;
import com.example.android.myinventory.data.InventoryItems;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getCanonicalName();
    InventoryDbHelper dbHelper;
    InventoryCursorAdapter adapter;
    int lastVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new InventoryDbHelper(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);//This is the FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);//This Takes us to the EditorActivity
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);
        View initialView = findViewById(R.id.initialView);
        listView.setEmptyView(initialView);//display the listView of all objects in the database

        Cursor cursor = dbHelper.readItems();//creats a cursor to read the data

        adapter = new InventoryCursorAdapter(this, cursor);
        listView.setAdapter(adapter);//sets the list adapder to the listView

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(dbHelper.readItems());//swaps cursor with another Cursor row
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, EditorActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellOneItem(id, quantity);
        adapter.swapCursor(dbHelper.readItems());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data for testing
                addDummyData();
                adapter.swapCursor(dbHelper.readItems());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Add data for demo purposes
     */
    private void addDummyData() {
        InventoryItems MHA1 = new InventoryItems(
                "My Hero Academia","500 RS",12,"Funimation", "+91 7550 123456", "Funimation.com",
                "android.resource://com.example.android.myinventory3/drawable/mha");
        dbHelper.insertItem(MHA1);

        InventoryItems Deathnote = new InventoryItems(
                "Death Note", "499 RS", 1, "AnimeNow", "+91 7550 654321", "animenow.org",
                "android.resource://com.example.android.myinventory3/drawable/death");
        dbHelper.insertItem(Deathnote);
    }
}
