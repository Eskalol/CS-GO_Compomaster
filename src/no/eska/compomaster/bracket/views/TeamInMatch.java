package no.eska.compomaster.bracket.views;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class represents a team in a match
 * Created by Eska on 09.04.2016.
 */
public class TeamInMatch extends StackPane{
    private Text        teamName;
    private Text        winCount;

    private Rectangle   teamBg;
    private Rectangle   winCountBg;
    private Rectangle   bg;

    public TeamInMatch(Color c, Font font, int arc, double opacity, int width, int height) {
        this.setAlignment(Pos.TOP_LEFT);
        teamName    = new Text("TBA");
        winCount    = new Text("0");
        teamBg      = new Rectangle(width-height, height);
        winCountBg  = new Rectangle(height, height);
        bg          = new Rectangle(width, height);

        teamName.setFont(font);
        teamName.setTranslateX(10);
        teamName.setTranslateY(height/2-font.getSize()/2+5);//TODO: dynamic text center

        winCount.setFont(font);
        winCount.setFill(Color.web("f8f8f8"));
        winCount.setTranslateX(width-height/2-5);
        winCount.setTranslateY(height/2-font.getSize()/2+5);//TODO: dynamic text center

        bg.setOpacity(0);
        bg.setArcWidth(arc);
        bg.setArcHeight(arc);
        DropShadow glow = new DropShadow();
        glow.setColor(c);
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setRadius(20);
        bg.setEffect(glow);

        teamBg.setOpacity(opacity);
        teamBg.setArcWidth(arc);
        teamBg.setArcHeight(arc);
        teamBg.setFill(c);

        winCountBg.setOpacity(opacity);
        winCountBg.setArcWidth(arc);
        winCountBg.setArcHeight(arc);
        winCountBg.setTranslateX(width-height);
        winCountBg.setFill(Color.web("232323"));

        this.getChildren().addAll(bg, teamBg, winCountBg, teamName, winCount);
    }


    /**
     * set team name
     * @param name name of team
     */
    public void setTeamName(String name) {
        teamName.setText(name);
    }

    /**
     * set win count label
     * @param wins wins < 0 = walkover
     */
    public void setWinCount(int wins) {
        winCount.setText(wins > -1 ? wins + "" : "WO");
        winCount.setTranslateX(wins > -1 ? bg.getWidth()-bg.getHeight()/2-5 : bg.getWidth()-bg.getHeight()/2-15);
        //TODO: dynamic text center
    }

    /**
     * sets glow
     * @param g boolean value
     */
    public void setGlow(boolean g) {
        bg.setOpacity(g ? 0.5 : 0);
    }


}
