package no.eska.compomaster.bracket.models;

import java.util.Objects;

/**
 * Created by Eska on 09.04.2016.
 */
public class Match {

    private String winner;

    private int team1Wins = 0;
    private int team2Wins = 0;

    private Team team1 = null;
    private Team team2 = null;

    private String matchLabel = "";
    private String winnerLabel = "";
    private String looserLabel = "";

    public Match(String matchLabel, String winnerLabel, String looserLabel, boolean looserBracket) {
        this.matchLabel = matchLabel;
        this.winnerLabel = winnerLabel;
        if( looserBracket )
            this.looserLabel = looserLabel;
    }


    public void insertTeam1(Team team1) {
        this.team1 = team1;
    }

    public void insertTeam2(Team team2) {
        this.team2 = team2;
    }

    public void setWinnerLabel(String WinnerLabel) {
        this.winnerLabel = WinnerLabel;
    }

    public void setLooserLabel(String looserLabel) {
        this.looserLabel = looserLabel;
    }

    public void setMatchLabel(String matchLabel){
        this.matchLabel = matchLabel;
    }

    public Team getTeam1() {
        return this.team1;
    }

    public Team getTeam2() {
        return this.team2;
    }

    public String getMatchLabel() {
        return this.matchLabel;
    }

    public String getWinnerLabel() {
        return this.winnerLabel;
    }

    public String getLooserLabel() {
        return this.looserLabel;
    }

    public int getTeam1Wins() {
        return this.team1Wins;
    }

    public int getTeam2Wins() {
        return this.team2Wins;
    }

    /**
     * return match winner
     * @return match winner number 1 or 2.
     */
    public String getWinner() {
        return this.winner;
    }

    /**
     * sets amount of wins
     * @param wins amount
     */
    public void setTeam1Wins(int wins) {
        this.team1Wins = wins;
        if(wins == -1)
            this.winner = "1";
    }

    /**
     * sets amount of wins
     * @param wins win amount
     */
    public void setTeam2Wins(int wins) {
        this.team2Wins = wins;
        if(wins == -1)
            this.winner = "2";
    }
}
