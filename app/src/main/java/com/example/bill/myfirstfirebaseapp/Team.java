package com.example.bill.myfirstfirebaseapp;

import java.util.ArrayList;

public class Team {
    private String name;
    private String record;
    private String player1;
    private String player2;
    private String player3;
    private String player4;
    private String player5;
    private int playerIndex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void addPlayer(String playerName) {
        switch (playerIndex) {
            case 1:
                player1 = playerName;
                break;
            case 2:
                player2 = playerName;
                break;
            case 3:
                player3 = playerName;
                break;
            case 4:
                player4 = playerName;
                break;
            default:
                player5 = playerName;
                break;
        }

        playerIndex++;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public String getPlayer4() {
        return player4;
    }

    public String getPlayer5() {
        return player5;
    }

    Team() {
        this.name = "";
        this.record = "";
        player1 = "";
        player2 = "";
        player3 = "";
        player4 = "";
        player5 = "";
        playerIndex = 1;
    }

    Team(String name, String record) {
        this.name = name;
        this.record = record;
    }
}
