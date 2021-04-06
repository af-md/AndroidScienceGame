package com.example.cellgame.model;

public class Player {
    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    String name;

    public Player(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public Player() {
    }

    String score;
}
