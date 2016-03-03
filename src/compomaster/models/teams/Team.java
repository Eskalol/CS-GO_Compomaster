package compomaster.models.teams;

/**
 * Created by Eska on 03.03.2016.
 */
public class Team {

    private String  teamName;
    /**
     * constructor
     *
     * @param teamName name of team
     */
    public Team(String teamName) {
        this.teamName = teamName;
    }


    /**
     * return tame of team
     * @return
     */
    public String getTeamName() {
        return teamName;
    }

}
