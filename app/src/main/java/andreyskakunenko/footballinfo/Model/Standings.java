package andreyskakunenko.footballinfo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Standings {

    public int id;
    @SerializedName("season")
    @Expose
    private Season season;
    @SerializedName("filters")
    @Expose
    private Filters filters;
    @SerializedName("standings")
    @Expose
    private List<Standing> standings = null;

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public List<Standing> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing> standings) {
        this.standings = standings;
    }


    public class Filters {
    }

    public class Season {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("currentMatchday")
        @Expose
        private Integer currentMatchday;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Integer getCurrentMatchday() {
            return currentMatchday;
        }

        public void setCurrentMatchday(Integer currentMatchday) {
            this.currentMatchday = currentMatchday;
        }

    }

    public class Standing {
        @SerializedName("stage")
        @Expose
        private String stage;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("group")
        @Expose
        private Object group;
        @SerializedName("table")
        @Expose
        private List<Table> table = null;

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getGroup() {
            return group;
        }

        public void setGroup(Object group) {
            this.group = group;
        }

        public List<Table> getTable() {
            return table;
        }

        public void setTable(List<Table> table) {
            this.table = table;
        }

    }

    public class Table {

        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("team")
        @Expose
        private Team team;
        @SerializedName("playedGames")
        @Expose
        private Integer playedGames;
        @SerializedName("won")
        @Expose
        private Integer won;
        @SerializedName("draw")
        @Expose
        private Integer draw;
        @SerializedName("lost")
        @Expose
        private Integer lost;
        @SerializedName("points")
        @Expose
        private Integer points;
        @SerializedName("goalsFor")
        @Expose
        private Integer goalsFor;
        @SerializedName("goalsAgainst")
        @Expose
        private Integer goalsAgainst;
        @SerializedName("goalDifference")
        @Expose
        private Integer goalDifference;

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        public Integer getPlayedGames() {
            return playedGames;
        }

        public void setPlayedGames(Integer playedGames) {
            this.playedGames = playedGames;
        }

        public Integer getWon() {
            return won;
        }

        public void setWon(Integer won) {
            this.won = won;
        }

        public Integer getDraw() {
            return draw;
        }

        public void setDraw(Integer draw) {
            this.draw = draw;
        }

        public Integer getLost() {
            return lost;
        }

        public void setLost(Integer lost) {
            this.lost = lost;
        }

        public Integer getPoints() {
            return points;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        public Integer getGoalsFor() {
            return goalsFor;
        }

        public void setGoalsFor(Integer goalsFor) {
            this.goalsFor = goalsFor;
        }

        public Integer getGoalsAgainst() {
            return goalsAgainst;
        }

        public void setGoalsAgainst(Integer goalsAgainst) {
            this.goalsAgainst = goalsAgainst;
        }

        public Integer getGoalDifference() {
            return goalDifference;
        }

        public void setGoalDifference(Integer goalDifference) {
            this.goalDifference = goalDifference;
        }

    }

    public class Team {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("crestURI")
        @Expose
        private Object crestURI;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getCrestURI() {
            return crestURI;
        }

        public void setCrestURI(Object crestURI) {
            this.crestURI = crestURI;
        }

    }
}
