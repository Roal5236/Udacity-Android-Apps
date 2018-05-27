package com.example.android.myinventory;

/**
 * Created by Rohaan on 10-Dec-17.
 */

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.android.myinventory.data.InventoryContract;
import com.example.android.myinventory.data.InventoryDbHelper;
import com.example.android.myinventory.data.InventoryItems;

public class EditorActivity extends AppCompatActivity {

    private static final String LOG_TAG = EditorActivity.class.getCanonicalName();
    private static final int PERMISSION = 1;
    private InventoryDbHelper dbHelper;
    EditText nameEditText;
    EditText priceEditText;
    EditText quantityEditText;
    EditText supplierNameEditText;
    EditText supplierPhoneEditText;
    EditText supplierEmailEditText;
    long currentItemId;
    ImageButton decreaseQuantity;
    ImageButton increaseQuantity;
    Button imageBtn;
    ImageView imageView;
    Uri actualUri;
    private static final int IMAGE_NO = 0;
    Boolean ItemInfoChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nameEditText = (EditText) findViewById(R.id.product_name_edit);
        priceEditText = (EditText) findViewById(R.id.price_edit);
        quantityEditText = (EditText) findViewById(R.id.quantity_edit);
        supplierNameEditText = (EditText) findViewById(R.id.supplier_name_edit);
        supplierPhoneEditText = (EditText) findViewById(R.id.supplier_phone_edit);
        supplierEmailEditText = (EditText) findViewById(R.id.supplier_email_edit);
        decreaseQuantity = (ImageButton) findViewById(R.id.decrease_quantity);
        increaseQuantity = (ImageButton) findViewById(R.id.increase_quantity);
        imageBtn = (Button) findViewById(R.id.select_image);
        imageView = (ImageView) findViewById(R.id.image_view);

        dbHelper = new InventoryDbHelper(this);
        currentItemId = getIntent().getLongExtra("itemId", 0);

        if (currentItemId == 0) {
            setTitle(getString(R.string.editor_activity_add_new_item));
        } else {
            setTitle(getString(R.string.editor_activity_edit_item));
            addValuesToEditItem(currentItemId);
        }

        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecreaseQuantity();
                ItemInfoChange = true;
            }
        });

        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncreaseQuantity();
                ItemInfoChange = true;
            }
        });

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToOpenImageSelector();
                ItemInfoChange = true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!ItemInfoChange) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };
        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog( //Sends a dialogue to Discard or keep Changes
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void DecreaseQuantity() {
        String previousValueString = quantityEditText.getText().toString();
        int previousValue;
        if (previousValueString.isEmpty()) {
            return;
        } else if (previousValueString.equals("0")) {
            return;
        } else {
            previousValue = Integer.parseInt(previousValueString);
            quantityEditText.setText(String.valueOf(previousValue - 1));
        }
    }

    private void IncreaseQuantity() {
        String previousValueString = quantityEditText.getText().toString();
        int previousValue;
        if (previousValueString.isEmpty()) {
            previousValue = 0;
        } else {
            previousValue = Integer.parseInt(previousValueString);
        }
        quantityEditText.setText(String.valueOf(previousValue + 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {// Creates a option menu to chose from
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {//the option for the inflated menu
        super.onPrepareOptionsMenu(menu);
        if (currentItemId == 0) {
            MenuItem deleteOneItem = menu.findItem(R.id.action_delete_item);
            MenuItem deleteAllItems = menu.findItem(R.id.action_delete_all_data);
            MenuItem orderMenuItem = menu.findItem(R.id.action_order);
            deleteOneItem.setVisible(false);
            deleteAllItems.setVisible(false);
            orderMenuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:
                // save item to DataBase
                if (!addItemToDb()) {
                    return true;
                }
                finish();
                return true;

            case android.R.id.home:
                if (!ItemInfoChange) {
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };
                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;

            case R.id.action_order:
                // dialog with phone and email
                showOrderConfirmationDialog();
                return true;

            case R.id.action_delete_item:
                // delete one item
                showDeleteConfirmationDialog(currentItemId);
                return true;

            case R.id.action_delete_all_data:
                //delete all data
                showDeleteConfirmationDialog(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean addItemToDb() {//Check if all items have the correct data type
        boolean isAllOk = true;
        if (!checkIfValueSet(nameEditText, "name")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(priceEditText, "price")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(quantityEditText, "quantity")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(supplierNameEditText, "supplier name")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(supplierPhoneEditText, "supplier phone")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(supplierEmailEditText, "supplier email")) {
            isAllOk = false;
        }
        if (actualUri == null && currentItemId == 0) {
            isAllOk = false;
            imageBtn.setError("Missing image");
        }
        if (!isAllOk) {
            return false;
        }

        if (currentItemId == 0) {
            InventoryItems item = new InventoryItems(
                    nameEditText.getText().toString().trim(),
                    priceEditText.getText().toString().trim(),
                    Integer.parseInt(quantityEditText.getText().toString().trim()),
                    supplierNameEditText.getText().toString().trim(),
                    supplierPhoneEditText.getText().toString().trim(),
                    supplierEmailEditText.getText().toString().trim(),
                    actualUri.toString());
            dbHelper.insertItem(item);
        } else {
            int quantity = Integer.parseInt(quantityEditText.getText().toString().trim());
            dbHelper.updateItem(currentItemId, quantity);
        }
        return true;
    }

    private boolean checkIfValueSet(EditText text, String description) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError("Missing product " + description);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }

    private void addValuesToEditItem(long itemId) {//Adds Values to the Cursor
        Cursor cursor = dbHelper.readItem(itemId);
        cursor.moveToFirst();
        nameEditText.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_NAME)));
        priceEditText.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_PRICE)));
        quantityEditText.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_QUANTITY)));
        supplierNameEditText.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_SUPPLIER_NAME)));
        supplierPhoneEditText.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_SUPPLIER_PHONE)));
        supplierEmailEditText.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_SUPPLIER_EMAIL)));
        imageView.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(InventoryContract.COLUMN_IMAGE))));
        nameEditText.setEnabled(false);
        priceEditText.setEnabled(false);
        supplierNameEditText.setEnabled(false);
        supplierPhoneEditText.setEnabled(false);
        supplierEmailEditText.setEnabled(false);
        imageBtn.setEnabled(false);
    }

    private void showOrderConfirmationDialog() {//Opens Dialer
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.order_message);
        builder.setPositiveButton(R.string.phone, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // intent to phone
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + supplierPhoneEditText.getText().toString().trim()));
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.email, new DialogInterface.OnClickListener() {//Opens Email
            public void onClick(DialogInterface dialog, int id) {
                // intent to email
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:" + supplierEmailEditText.getText().toString().trim()));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Recurrent new order");
                String bodyMessage = "Please send us as soon as possible more " +
                        nameEditText.getText().toString().trim() +
                        "!!!";
                intent.putExtra(Intent.EXTRA_TEXT, bodyMessage);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int deleteAllRowsFromTable() {//Deletes all Items from Table
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.delete(InventoryContract.TABLE_NAME, null, null);
    }

    private int deleteOneItemFromTable(long itemId) {//Deletes one Items from Table
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String selection = InventoryContract._ID + "=?";
        String[] selectionArgs = { String.valueOf(itemId) };
        int rowsDeleted = database.delete(
                InventoryContract.TABLE_NAME, selection, selectionArgs);
        return rowsDeleted;
    }

    private void showDeleteConfirmationDialog(final long itemId) {//Shows an Alert Asking for Conformation For deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_message);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (itemId == 0) {
                    deleteAllRowsFromTable();
                } else {
                    deleteOneItemFromTable(itemId);
                }
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void tryToOpenImageSelector() {//Opens External Storage for retriving Image
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION);
            return;
        }
        openImageSelector();
    }

    private void openImageSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_NO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageSelector();
                    // permission was granted
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code READ_REQUEST_CODE.
        // If the request code seen here doesn't match, it's the response to some other intent,
        // and the below code shouldn't run at all.

        if (requestCode == IMAGE_NO && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.  Pull that uri using "resultData.getData()"

            if (resultData != null) {
                actualUri = resultData.getData();
                imageView.setImageURI(actualUri);
                imageView.invalidate();
            }
        }
    }
}
