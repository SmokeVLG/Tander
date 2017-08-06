package com.maxim.denisov.tander;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import data.TanderDbHelper;
import data.TanderContract.GuestEntry;

public class MainActivity extends AppCompatActivity {

    private TanderDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new TanderDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
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
            case R.id.action_insert_new_data:
                insertGuest();
                displayDatabaseInfo();
                return true;
            case R.id.action_delete_all_entries:
                deleteAll();
                displayDatabaseInfo();
                return true;

            case R.id.action_setttings:
                startActivity(new Intent(this, SettingsActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    /*
    *Обновить страницу
    */
    private void displayDatabaseInfo() {
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

        TextView displayTextView = (TextView) findViewById(R.id.text_view_info);

        try {
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " гостей.\n\n");
            displayTextView.append(GuestEntry._ID + " - " +
                    GuestEntry.COLUMN_COEFFICIENT + "\n");

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex(GuestEntry._ID);
            int ageColumnIndex = cursor.getColumnIndex(GuestEntry.COLUMN_COEFFICIENT);

            // Проходим через все ряды
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor.getInt(idColumnIndex);
                int currentAge = cursor.getInt(ageColumnIndex);
                // Выводим значения каждого столбца
                displayTextView.append(("\n" + currentID + " - " +
                        currentAge));
            }
        } finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
    }

    /**
     * Вспомогательный метод для вставки записи
     */
    private void insertGuest() {

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Создаем объект ContentValues, где имена столбцов ключи,
        // а информация о госте является значениями ключей
        ContentValues values = new ContentValues();
        int number = 1;
        values.put(GuestEntry.COLUMN_COEFFICIENT, 7);

        long newRowId = db.insert(GuestEntry.TABLE_NAME, null, values);

    }


    /**
     * Вспомогательный метод для удаления всех записей
     */
    private void deleteAll() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " +GuestEntry.TABLE_NAME+ " WHERE _id>0");
    }
}