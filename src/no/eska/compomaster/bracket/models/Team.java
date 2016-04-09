package no.eska.compomaster.bracket.models;

/**
 * Created by Eska on 09.04.2016.
 */
public class Team {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    /**
     * gets name of team
     * @return name of team
     */
    public String getName() {
        return name;
    }
}
