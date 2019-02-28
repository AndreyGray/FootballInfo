package andreyskakunenko.footballinfo.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.Single;


@Dao
public interface CompetitionsDao {


    @Query("SELECT * FROM competition")
    Flowable<List<CompetitionEntity>> getAll();

    @Query("SELECT * FROM competition WHERE seasonId = :id")
    Single<CompetitionEntity> getById(int id);

    @Insert
    void insert(CompetitionEntity competitions);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(CompetitionEntity competitions);


}
