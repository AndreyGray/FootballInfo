package andreyskakunenko.footballinfo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Competitions {

    /** Our model for fetching data from API */
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("filters")
    @Expose
    private Filters filters;
    @SerializedName("competitions")
    @Expose
    private List<Competition> competitions = null;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
    public class Area {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

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

    }
    public class Competition {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("area")
        @Expose
        private Area area;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("emblemUrl")
        @Expose
        private Object emblemUrl;
        @SerializedName("plan")
        @Expose
        private String plan;
        @SerializedName("currentSeason")
        @Expose
        private CurrentSeason currentSeason;
        @SerializedName("numberOfAvailableSeasons")
        @Expose
        private Integer numberOfAvailableSeasons;
        @SerializedName("lastUpdated")
        @Expose
        private String lastUpdated;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Area getArea() {
            return area;
        }

        public void setArea(Area area) {
            this.area = area;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getEmblemUrl() {
            return emblemUrl;
        }

        public void setEmblemUrl(Object emblemUrl) {
            this.emblemUrl = emblemUrl;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        public CurrentSeason getCurrentSeason() {
            return currentSeason;
        }

        public void setCurrentSeason(CurrentSeason currentSeason) {
            this.currentSeason = currentSeason;
        }

        public Integer getNumberOfAvailableSeasons() {
            return numberOfAvailableSeasons;
        }

        public void setNumberOfAvailableSeasons(Integer numberOfAvailableSeasons) {
            this.numberOfAvailableSeasons = numberOfAvailableSeasons;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

    }

    public class CurrentSeason {
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
        @SerializedName("winner")
        @Expose
        private Winner winner;

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

        public Winner getWinner() {
            return winner;
        }

        public void setWinner(Winner winner) {
            this.winner = winner;
        }

    }


    public class Filters {


    }

    public class Winner {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("shortName")
        @Expose
        private String shortName;
        @SerializedName("tla")
        @Expose
        private String tla;
        @SerializedName("crestUrl")
        @Expose
        private String crestUrl;

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

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getTla() {
            return tla;
        }

        public void setTla(String tla) {
            this.tla = tla;
        }

        public String getCrestUrl() {
            return crestUrl;
        }

        public void setCrestUrl(String crestUrl) {
            this.crestUrl = crestUrl;
        }

    }}
