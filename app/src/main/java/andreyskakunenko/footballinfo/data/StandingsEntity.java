package andreyskakunenko.footballinfo.data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "standing")
public class StandingsEntity {

    @PrimaryKey
    public int teamId;

    public int currentMatchDay;

    public int teamPos;

    public String teamName;

    public int pg;

    public int w;

    public int d;

    public int l;

    public int f;

    public int a;

    public int gd;

    public int pts;
}
