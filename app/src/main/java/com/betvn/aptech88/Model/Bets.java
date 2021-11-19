package com.betvn.aptech88.Model;

public class Bets {
    public String id;
    public String name_bet;
    public String value;
    public String odd;

    public Bets(String name_bet) {
        this.name_bet = name_bet;
    }

    public Bets(String id,String name_bet, String value, String odd) {
        this.id = id;
        this.name_bet = name_bet;
        this.value = value;
        this.odd = odd;
    }

    public Bets(String value_home, String odd_home) {
    }

    public String getName_bet() {
        return name_bet;
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public String getOdd() {
        return odd;
    }
}
