package com.example.profi.model;

import java.io.Serializable;

public class Result implements Serializable {
    private Participant winner;
    private Prize prize;

    public Result(Participant winner, Prize prize) {
        this.winner = winner;
        this.prize = prize;
    }

    public Result() {
    }

    public Participant getWinner() {
        return winner;
    }

    public void setWinner(Participant winner) {
        this.winner = winner;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}
