package com.maxim.denisov.tander;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import data.TanderContract;
import data.TanderDbHelper;

public class SettingsActivity extends AppCompatActivity {

    private TanderDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mDbHelper = new TanderDbHelper(this);
    }

    public void onClick3(View view) {

        EditText eTNumberRaw = (EditText) findViewById(R.id.eTNumberRaw);
        String numberRaw = String.valueOf(eTNumberRaw.getText());
        EditText eTK = (EditText) findViewById(R.id.eTK);
        String k = String.valueOf(eTK.getText());

        // Создадим и откроем для чтения базу данных
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String sql = "UPDATE " + TanderContract.GuestEntry.TABLE_NAME + " SET " + TanderContract.GuestEntry.COLUMN_COEFFICIENT + "=" + k + " WHERE " + TanderContract.GuestEntry._ID + " = " + numberRaw;
        db.execSQL(sql);

    }
}
