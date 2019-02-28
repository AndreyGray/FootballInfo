package andreyskakunenko.footballinfo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Team {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("area")
        @Expose
        private Area area;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("shortName")
        @Expose
        private String shortName;
        @SerializedName("tla")
        @Expose
        private String tla;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("founded")
        @Expose
        private Integer founded;
        @SerializedName("clubColors")
        @Expose
        private String clubColors;
        @SerializedName("venue")
        @Expose
        private Object venue;
        @SerializedName("squad")
        @Expose
        private List<Squad> squad = null;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getFounded() {
            return founded;
        }

        public void setFounded(Integer founded) {
            this.founded = founded;
        }

        public String getClubColors() {
            return clubColors;
        }

        public void setClubColors(String clubColors) {
            this.clubColors = clubColors;
        }

        public Object getVenue() {
            return venue;
        }

        public void setVenue(Object venue) {
            this.venue = venue;
        }

        public List<Squad> getSquad() {
            return squad;
        }

        public void setSquad(List<Squad> squad) {
            this.squad = squad;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
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

    public class Squad {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("position")
        @Expose
        private String position;
        @SerializedName("dateOfBirth")
        @Expose
        private String dateOfBirth;
        @SerializedName("countryOfBirth")
        @Expose
        private String countryOfBirth;
        @SerializedName("nationality")
        @Expose
        private String nationality;
        @SerializedName("role")
        @Expose
        private String role;

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

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getCountryOfBirth() {
            return countryOfBirth;
        }

        public void setCountryOfBirth(String countryOfBirth) {
            this.countryOfBirth = countryOfBirth;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }
}
