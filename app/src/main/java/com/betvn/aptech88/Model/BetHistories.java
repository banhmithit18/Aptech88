package com.betvn.aptech88.Model;

public class BetHistories {
    public String odd;
    public String bet_amount;
    public String result;

    public BetHistories(String odd, String bet_amount, String result) {
        this.odd = odd;
        this.bet_amount = bet_amount;
        this.result = result;
    }

    public String getOdd() {
        return odd;
    }

    public String getBet_amount() {
        return bet_amount;
    }

    public String getResult() {
        return result;
    }
}
