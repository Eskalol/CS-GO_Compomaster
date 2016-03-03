package compomaster.views.bracket;

import compomaster.Resources;
import compomaster.models.teams.Team;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Eska on 03.03.2016.
 */
public class MatchRectangle extends StackPane {
    private Team        team1;
    private Team        team2;
    private Rectangle   teamBg1;
    private Rectangle   teamBg2;
    private Rectangle   teamBgWins1;
    private Rectangle   teamBgWins2;
    private Text        teamName1;
    private Text        teamName2;
    private int         team1Wins;
    private int         team2Wins;
    private Text        team1WinAmount;
    private Text        team2WinAmount;
    private Font        font;

    /**
     * constructor
     *
     * @param team1 team 1
     * @param team2 team 2
     */
    public MatchRectangle(Team team1, Team team2) {
        this.team1  = team1;
        this.team2  = team2;
    }


    /**
     * constructor
     */
    public MatchRectangle(Font font) {
        this.team1  = null;
        this.team2  = null;
    }


    public void load() {
        //loads the font
        font        = Resources.getCsgoFont(18);

        //background for team 1
        teamBg1     = new Rectangle(170, 35);
        teamBg1.setFill(Color.web("fe721d"));
        teamBg1.setOpacity(0.7);
        teamBg1.setArcHeight(10);
        teamBg1.setArcWidth(10);
        //text for team 1
        teamName1   = new Text();
        teamName1.setFont(font);
        //background for team 1 amount of wins
        teamBgWins1 = new Rectangle(35, 35);
        teamBgWins1.setOpacity(0.7);
        teamBgWins1.setFill(Color.web("232323"));
        teamBgWins1.setTranslateX(102);
        teamBgWins1.setArcWidth(10);
        teamBgWins1.setArcHeight(10);
        //text for team 1 amount of wins
        team1WinAmount = new Text();
        team1WinAmount.setFill(Color.web("f8f8f8"));
        team1WinAmount.setTranslateX(101);
        team1WinAmount.setFont(font);

        //background for team 2
        teamBg2     = new Rectangle(170, 35);
        teamBg2.setTranslateY(35);
        teamBg2.setFill(Color.web("f8f8f8"));
        teamBg2.setOpacity(0.7);
        teamBg2.setArcHeight(10);
        teamBg2.setArcWidth(10);
        //text fir team 2
        teamName2   = new Text();
        teamName2.setTranslateY(35);
        teamName2.setFont(font);
        //backgroun for team 2 amount of wins
        teamBgWins2 = new Rectangle(35, 35);
        teamBgWins2.setOpacity(0.7);
        teamBgWins2.setFill(Color.web("232323"));
        teamBgWins2.setTranslateY(35);
        teamBgWins2.setTranslateX(102);
        teamBgWins2.setArcWidth(10);
        teamBgWins2.setArcHeight(10);
        //text for team 2 amount of wins
        team2WinAmount = new Text();
        team2WinAmount.setFill(Color.web("f8f8f8"));
        team2WinAmount.setTranslateX(101);
        team2WinAmount.setTranslateY(35);
        team2WinAmount.setFont(font);

        this.getChildren().addAll(teamBg1, teamBg2, teamName1, teamName2, teamBgWins1, teamBgWins2, team1WinAmount, team2WinAmount);
    }


    /**
     *
     */
    public void updateText() {
        String tName1 = (team1 == null) ? "n/a" : team1.getTeamName();
        String tName2 = (team2 == null) ? "n/a" : team2.getTeamName();

        teamName1.setText(tName1);
        teamName2.setText(tName2);

        team1WinAmount.setText(Integer.toString(team1Wins));
        team2WinAmount.setText(Integer.toString(team2Wins));


    }

    /**
     * get team 1
     *
     * @return team1
     */
    public Team getTeam1() {
        return team1;
    }


    /**
     * get team 2
     *
     * @return team2
     */
    public Team getTeam2() {
        return team2;
    }


    /**
     * set team1
     *
     * @param team
     */
    public void setTeam1(Team team) {
        this.team1 = team;
    }


    /**
     * set team2
     *
     * @param team
     */
    public void setTeam2(Team team) {
        this.team2 = team;
    }


    /**
     * get team 1 wins
     *
     * @return
     */
    public int getTeam1Wins() {
        return team1Wins;
    }


    /**
     * set team 1 wins
     *
     * @param team1Wins win coint for team 1
     */
    public void setTeam1Wins(int team1Wins) {
        this.team1Wins = team1Wins;
    }


    /**
     * get team 2 wins
     *
     * @return
     */
    public int getTeam2Wins() {
        return team2Wins;
    }


    /**
     * set team 2 wins
     * @param team2Wins
     */
    public void setTeam2Wins(int team2Wins) {
        this.team2Wins = team2Wins;
    }
}
