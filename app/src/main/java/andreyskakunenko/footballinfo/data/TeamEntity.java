package andreyskakunenko.footballinfo.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "squad")
public class TeamEntity {

    @PrimaryKey
    public int teamId;

    public String teamCountry;

    public String teamName;

    public String teamURL;

    public String teamEmail;

    public int teamFounded;

    public String playerName;

    public String playerPosition;

    public String playerRole;

    public String playerDateBirth;

    public String playerCountryBirth;

    public String playerNationality;
}
