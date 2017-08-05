package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Максим on 05.08.2017.
 */

public class TanderDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = TanderDbHelper.class.getSimpleName();

    /**
     * Имя файла базы данных
     */
    private static final String DATABASE_NAME = "hotel.db";

    /**
     * Версия базы данных. При изменении схемы увеличить на единицу
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Конструктор {@link TanderDbHelper}.
     *
     * @param context Контекст приложения
     */
    public TanderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Вызывается при создании базы данных
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Строка для создания таблицы
        String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " + TanderContract.GuestEntry.TABLE_NAME + " ("
                + TanderContract.GuestEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TanderContract.GuestEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + TanderContract.GuestEntry.COLUMN_CITY + " TEXT NOT NULL, "
                + TanderContract.GuestEntry.COLUMN_GENDER + " INTEGER NOT NULL DEFAULT 3, "
                + TanderContract.GuestEntry.COLUMN_AGE + " INTEGER NOT NULL DEFAULT 0);";

        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    /**
     * Вызывается при обновлении схемы базы данных
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + TanderContract.GuestEntry.TABLE_NAME);
        // Создаём новую таблицу
        onCreate(db);
    }
}
