package andreyskakunenko.footballinfo.data;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities={CompetitionEntity.class, TeamEntity.class, StandingsEntity.class},version = 1,exportSchema = false)

public abstract class AppRoomDatabase extends RoomDatabase {

    private static AppRoomDatabase sInstance;

    public abstract CompetitionsDao mCompetitionsDao();
    public abstract StandingsDao mStandingsDao();
    public abstract TeamDao mTeamDao();

    public static AppRoomDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.
                    databaseBuilder(context.getApplicationContext(),
                    AppRoomDatabase.class, "footballinfo")
                    //.allowMainThreadQueries()
                    .build();
        }

        return sInstance;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

}
