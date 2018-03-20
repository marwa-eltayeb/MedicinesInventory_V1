package com.example.marwa.medicinesinventory.data;

import android.provider.BaseColumns;

/**
 * Created by Marwa on 2/11/2018.
 */

public final class MedicineContract {
    
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private MedicineContract() {
    }

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single medicine.
     */
    public static abstract class MedicineEntry implements BaseColumns {

        /**
         * Name of database table for medicines
         */
        public static final String TABLE_NAME = "medicines";

        /**
         * Name of the medicine.
         * Type: TEXT
         */
        public static final String COLUMN_NAME = "name";

        /**
         * Price of the medicine.
         * Type: REAL
         */
        public static final String COLUMN_PRICE = "price";

        /**
         * Quantity of the medicine.
         * Type: INTEGER
         */
        public static final String COLUMN_QUANTITY = "quantity";
        /**
         * Supplier of the medicine.
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER = "supplier";

        /**
         * Phone of the supplier.
         * Type: TEXT
         */
        public static final String COLUMN_PHONE = "phone";
    }

}
