package com.maxim.denisov.tander;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import data.TanderContract.GuestEntry;
import data.TanderDbHelper;


public class EditorActivity extends AppCompatActivity {


    private EditText mCityEditText;
    private EditText mAgeEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mCityEditText = (EditText) findViewById(R.id.edit_guest_city);
        mAgeEditText = (EditText) findViewById(R.id.edit_guest_age);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                insertGuest();
                // Закрываем активность
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Сохраняем введенные данные в базе данных.
     */
    private void insertGuest() {
        // Считываем данные из текстовых полей
        String city = mCityEditText.getText().toString().trim();
        String ageString = mAgeEditText.getText().toString().trim();
        int age = Integer.parseInt(ageString);

        TanderDbHelper mDbHelper = new TanderDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GuestEntry.COLUMN_COEFFICIENT, age);

        // Вставляем новый ряд в базу данных и запоминаем его идентификатор
        long newRowId = db.insert(GuestEntry.TABLE_NAME, null, values);

        // Выводим сообщение в успешном случае или при ошибке
        if (newRowId == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(this, "Ошибка при заведении гостя", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Гость заведён под номером: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}
