package com.betvn.aptech88.Model;

import java.sql.Date;
import java.sql.Time;

public class Fixture {
    public String id;
    public String leagueId;
    public String date;
    public String time;
    public String away;
    public String home;
    public String status;
    public String inMatch;
    public String logo_home;
    public String logo_away;
    public String name_team_home;
    public String name_team_away;


//    public Fixture(String id, String leagueId, String date, String time, String away, String home, String status, String inMatch) {
//        this.id = id;
//        this.leagueId = leagueId;
//        this.date = date;
//        this.time = time;
//        this.away = away;
//        this.home = home;
//        this.status = status;
//        this.inMatch = inMatch;
//    }

    public Fixture(String id,String home, String away, String date, String time, String logo_home, String logo_away, String name_team_home, String name_team_away) {
        this.id = id;
        this.away = away;
        this.home = home;
        this.date = date;
        this.time = time;
        this.logo_home = logo_home;
        this.logo_away = logo_away;
        this.name_team_away = name_team_away;
        this.name_team_home = name_team_home;
    }

    public Fixture(String home, String away, String date, String time) {
        this.away = away;
        this.home = home;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAway() {
        return away;
    }

    public String getHome() {
        return home;
    }

    public String getStatus() {
        return status;
    }

    public String getInMatch() {
        return inMatch;
    }

    public String getLogo_home() {
        return logo_home;
    }

    public String getLogo_away() {
        return logo_away;
    }

    public String getName_team_home() {
        return name_team_home;
    }

    public String getName_team_away() {
        return name_team_away;
    }
}
