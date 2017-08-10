package com.maxim.denisov.tander;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import data.TanderContract;
import data.TanderDbHelper;
import data.TanderContract.GuestEntry;

public class MainActivity extends AppCompatActivity {

    public TanderDbHelper mDbHelper;

    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDbHelper = new TanderDbHelper(this);
        List<Number> numbers = getNumbers();
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new NumberAdapter(this, numbers));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setttings:
                Intent settings = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(settings);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Number> getNumbers() {
        // Создадим и откроем для чтения базу данных
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Зададим условие для выборки - список столбцов
        String[] projection = {
                GuestEntry._ID,
                GuestEntry.COLUMN_COEFFICIENT};

        // Делаем запрос
        Cursor cursor = db.query(
                GuestEntry.TABLE_NAME,   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        List<Number> numbers = new ArrayList<>();

        try {

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex(GuestEntry._ID);
            int coefficientColumnIndex = cursor.getColumnIndex(GuestEntry.COLUMN_COEFFICIENT);

            // Проходим через все ряды
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor.getInt(idColumnIndex);
                int currentCoefficient = cursor.getInt(coefficientColumnIndex);
                // Выводим значения каждого столбца
                numbers.add(new Number(currentID, currentCoefficient));
            }
        } finally {

            // Всегда закрываем курсор после чтения
            cursor.close();
        }
        return numbers;
    }


    public void onClick(View view) {
        LinearLayout vwParentRow = (LinearLayout)view.getParent();

        TextView child = (TextView)vwParentRow.getChildAt(0);
        ProgressButton btnChild = (ProgressButton)vwParentRow.getChildAt(1);

        Intent currentButton = new Intent(getApplicationContext(),PropertyActivity.class);
        currentButton.putExtra("id", child.getText());
        currentButton.putExtra("k", btnChild.getRatio());
        startActivity(currentButton);
    }


}