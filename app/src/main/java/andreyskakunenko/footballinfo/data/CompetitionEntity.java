package andreyskakunenko.footballinfo.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "competition")
public class CompetitionEntity {
    @PrimaryKey
    public int seasonId;

    public String status;

    public String seasonName;

    public String seasonArea;

    public String startDate;

    public String endDate;









}
