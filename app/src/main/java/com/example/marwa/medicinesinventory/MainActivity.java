package com.example.marwa.medicinesinventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.marwa.medicinesinventory.data.MedicineContract.MedicineEntry;
import com.example.marwa.medicinesinventory.data.MedicineDbHelper;


public class MainActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * Database helper that will provide us access to the database
     */
    MedicineDbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        dbHelper = new MedicineDbHelper(this);

    }


    /**
     * Insert hardcoded Medicine data into the database.
     */
    private void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a ContentValues object
        ContentValues values = new ContentValues();

        values.put(MedicineEntry.COLUMN_NAME, "Congestal");
        values.put(MedicineEntry.COLUMN_PRICE, 20);
        values.put(MedicineEntry.COLUMN_QUANTITY, 5);
        values.put(MedicineEntry.COLUMN_SUPPLIER, "Karma");
        values.put(MedicineEntry.COLUMN_PHONE, "01004567893");

        // Insert data
        long newRowInsertedID = db.insert(MedicineEntry.TABLE_NAME, null, values);

        // If the data is added
        if (newRowInsertedID != -1) {
            Toast.makeText(this, getResources().getString(R.string.succeeded), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.failed), Toast.LENGTH_SHORT).show();
        }

        Log.d(LOG_TAG, String.valueOf(values));
    }


    /**
     * Read hardcoded Medicine data from the database.
     */
    private void ReadData() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        /// Define a projection that specifies the columns from the table.
        String[] projection = {
                MedicineEntry._ID,
                MedicineEntry.COLUMN_NAME,
                MedicineEntry.COLUMN_PRICE,
                MedicineEntry.COLUMN_QUANTITY,
                MedicineEntry.COLUMN_SUPPLIER,
                MedicineEntry.COLUMN_PHONE};


        // Perform a query on the medicine table
        Cursor cursor = db.query(
                MedicineEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                 // The sort order

        try {
            // Figure out the index of each column
            int name = cursor.getColumnIndex(MedicineEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(MedicineEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(MedicineEntry.COLUMN_QUANTITY);
            int supplier = cursor.getColumnIndex(MedicineEntry.COLUMN_SUPPLIER);
            int phone = cursor.getColumnIndex(MedicineEntry.COLUMN_PHONE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String currentName = cursor.getString(name);
                Double currentPrice = cursor.getDouble(price);
                int currentQuantity = cursor.getInt(quantity);
                String currentSupplier = cursor.getString(supplier);
                String currentPhone = cursor.getString(phone);

                // Log for debugging
                Log.d(LOG_TAG, " Name: " + currentName + " Price: " + currentPrice + " Quantity: " + currentQuantity
                        + " Supplier: " + currentSupplier + " Phone: " + currentPhone);

            }
        } finally {
            // Close the cursor.
            cursor.close();
            // Close the database.
            db.close();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.insert_dummy_data:
                // insert new medicine
                insertData();
                return true;
            // Respond to a click on the "Read dummy data" menu option
            case R.id.read_dummy_data:
                ReadData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}