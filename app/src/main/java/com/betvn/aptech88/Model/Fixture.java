package com.betvn.aptech88.Model;

import java.sql.Date;
import java.sql.Time;

public class Fixture {
    public int id;
    public int leagueId;
    public Date date;
    public Time time;
    public int away;
    public int home;
    public Boolean status;
    public Boolean inMatch;

    public Fixture(int id, int leagueId, Date date, Time time, int away, int home, Boolean status, Boolean inMatch) {
        this.id = id;
        this.leagueId = leagueId;
        this.date = date;
        this.time = time;
        this.away = away;
        this.home = home;
        this.status = status;
        this.inMatch = inMatch;
    }

    public Fixture(int home, int away) {
        this.away = away;
        this.home = home;
    }

    public int getId() {
        return id;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getAway() {
        return away;
    }

    public int getHome() {
        return home;
    }

    public Boolean getStatus() {
        return status;
    }

    public Boolean getInMatch() {
        return inMatch;
    }
}
