package andreyskakunenko.footballinfo.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface StandingsDao {

    @Query("SELECT * FROM standing")
    Flowable<List<StandingsEntity>> getAll();

    @Insert
    void insert(StandingsEntity standing);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(StandingsEntity standing);
}
