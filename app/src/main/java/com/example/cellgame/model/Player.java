package com.example.cellgame.model;

public class Player {
    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    String name;

    public Player(String name, String score) {
        this.name = name;
        this.score = score;
    }

    String score;
}
