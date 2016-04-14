package no.eska.compomaster.bracket.models;

/**
 * Created by Eska on 14.04.2016.
 */
public class Map {
    private String mapName;
    private int team1Wins;
    private int team2Wins;

    public Map(String mapName, int team1Wins, int team2Wins) {
        this.mapName = mapName;
        this.team1Wins = team1Wins;
        this.team2Wins = team2Wins;
    }
}
