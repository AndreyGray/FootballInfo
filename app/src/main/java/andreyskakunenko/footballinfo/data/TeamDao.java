package andreyskakunenko.footballinfo.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM squad")
    Flowable<List<TeamEntity>> getAll();

    @Insert
    void insert(TeamEntity team);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(TeamEntity team);}
