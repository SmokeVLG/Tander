package data;

import android.provider.BaseColumns;

/**
 * Created by Максим on 05.08.2017.
 */

public final class TanderContract {

    private TanderContract() {
    };

    public static final class GuestEntry implements BaseColumns {
        public final static String TABLE_NAME = "tander";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_COEFFICIENT = "coefficient";


    }
}
