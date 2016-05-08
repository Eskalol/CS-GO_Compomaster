package no.eska.compomaster.bracket.views;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import no.eska.compomaster.Resources;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import no.eska.compomaster.bracket.models.Match;

/**
 * Created by Eska on 03.03.2016.
 */
public class MatchRectangle extends StackPane {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 70;

    private final int ARC = 10;
    private final double OPACITY = 1;

    private final int FONTSIZE = 18;


    private TeamInMatch team1;
    private TeamInMatch team2;

    private Font font;
    private Rectangle outerRect;

    public MatchRectangle(int x, int y) {
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        this.setAlignment(Pos.TOP_LEFT);
        font = Resources.getCsgoFont(FONTSIZE);
        this.setTranslateX(x);
        this.setTranslateY(y);
        load();
    }

    public void load() {
        loadHoverGlow();
        this.team1 = new TeamInMatch(Color.web("fe721d"), font, ARC, OPACITY, WIDTH, HEIGHT / 2);
        this.team2 = new TeamInMatch(Color.web("f8f8f8"), font, ARC, OPACITY, WIDTH, HEIGHT / 2);
        team2.setTranslateY(HEIGHT / 2);
        this.getChildren().addAll(team1, team2);
        loadMouseHover();
    }

    public void update(Match match) {
        if(match.getTeam1() != null) {
            this.setTeam1Name(match.getTeam1().getName());
            this.setTeam1Wins(match.getTeam1Wins());
        }
        if(match.getTeam2() != null) {
            this.setTeam2Name(match.getTeam2().getName());
            this.setTeam2Wins(match.getTeam2Wins());
        }

        //set winner
        if(match.getWinner() != null) {
            if(match.getWinner().equals("1")) {
                team1.setGlow(true);
                team2.setGlow(false);
            } else if(match.getWinner().equals("2")) {
                team2.setGlow(true);
                team1.setGlow(false);
            }
        } else {
            team2.setGlow(false);
            team1.setGlow(false);
        }
    }


    /**
     * sets glow if true
     * @param bool
     */
    public void setGlow(boolean bool) {
        outerRect.setOpacity(bool ? 1 : 0);
    }



    /**
     * set name of team 1
     * @param name name of team 1
     */
    public void setTeam1Name(String name) {
        team1.setTeamName(name);
    }

    /**
     * set name of team 2
     * @param name name of team 2
     */
    public void setTeam2Name(String name) {
        team2.setTeamName(name);
    }

    /**
     * sets win count for team 2, -1 == walkover
     * @param wins win count for team 2
     */
    public void setTeam1Wins(int wins) {
        team1.setWinCount(wins);
    }

    /**
     * sets win count for team 2, -1 == walkover
     * @param wins win count for team 2
     */
    public void setTeam2Wins(int wins) {
        team2.setWinCount(wins);
    }

    /**
     * loads Hover glow
     * TODO: fix transparent background of rectangle
     */
    public void loadHoverGlow() {
        DropShadow outerGlow = new DropShadow();
        outerGlow.setColor(Color.web("f8f8f8"));
        outerGlow.setOffsetX(0);
        outerGlow.setOffsetY(0);
        outerGlow.setRadius(20);

        outerRect = new Rectangle(200, 70);
        outerRect.setArcHeight(10);
        outerRect.setArcWidth(10);
        outerRect.setEffect(outerGlow);
        outerRect.setOpacity(0);
        this.getChildren().add(outerRect);

    }


    /**
     * loads mouse hover glow
     */
    private void loadMouseHover() {
        this.setOnMouseEntered(event -> {
            this.setTranslateY(this.getTranslateY() + 2);
        });
        this.setOnMouseExited(event -> {
            this.setTranslateY(this.getTranslateY() - 2);
        });
    }

}