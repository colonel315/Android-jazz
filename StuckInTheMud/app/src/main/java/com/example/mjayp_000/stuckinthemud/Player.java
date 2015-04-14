package com.example.mjayp_000.stuckinthemud;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void addToScore(int points) {
        this.score += points;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }
}
